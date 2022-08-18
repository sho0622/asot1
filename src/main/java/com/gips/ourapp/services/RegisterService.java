/**
 *
 */
package com.gips.ourapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.ourapp.entities.RegisterEntity;
import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.repositories.RegisterRepository;

/**
 *
 * サービス(ユーザ登録)
 *
 * 登録のビジネスロジックを実装する
 *
 */
@Service
public class RegisterService {

	// リポジトリインターフェース
	@Autowired
	RegisterRepository repository;

	/**
	 * 登録
	 * ユーザネームとパスワードによる登録処理のメソッド
	 *
	 * @param userName	ユーザネーム
	 * @param password1 パスワード
	 * @param password2 パスワード(確認)
	 *
	 * @return 登録の成否
	 */

	// DB登録
	public boolean register(UserForm form) {

		//ユーザマスタからレコードを検索する
		RegisterEntity registerEntity = repository.findUserByUserName(form.getUserName());

		//引数の入力チェックを行う
		//１つでもエラーがあった場合には、処理を異常終了する

		//未入力の場合
		if (form.getUserName().equals("") || form.getPassword1().equals("") || form.getPassword2().equals("")) {
			return false;
		} else

		//半角英数字以外の文字が入力された場合
		if (!form.getUserName().matches("[0-9a-zA-Z]") || !form.getPassword1().matches("[0-9a-zA-Z]")
				|| !form.getPassword2().matches("[0-9a-zA-Z]")) {
			return false;
		} else

		//パスワードとパスワード(確認)が不一致の場合
		if (!form.getPassword1().equals(form.getPassword2())) {
			return false;
		} else

		//10文字以上の場合
		if (form.getUserName().length() >= 10 || form.getPassword1().length() >= 10
				|| form.getPassword2().length() >= 10) {
			return false;
		} else

		//4文字以下の場合(パスワードとパスワード(確認))
		if (form.getPassword1().length() <= 4 || form.getPassword2().length() <= 4) {
			return false;
		} else

		//引数のユーザネームがすでに登録されているレコードと一致した場合
		if (registerEntity != null) {
			return false;
		} else {

			//エンティティクラスをインスタンス化する
			RegisterEntity entity = new RegisterEntity();

			// 引数で受け取ったフォームクラスの値をエンティティクラスに設定
			// ユーザ名
			entity.setUserName(form.getUserName());
			// パスワード
			entity.setPassword(form.getPassword1());

			// JPAのsaveメソッドを呼び出してDB登録
			repository.save(entity);

			// 処理を正常終了する
			return true;
		}

	}
}
