/**
 *
 */
package com.gips.ourapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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
	 * ユーザ名とパスワードによる登録処理のメソッド
	 *
	 * @param userName	ユーザ名
	 * @param password1 パスワード
	 * @param password2 パスワード(確認)
	 *
	 * @return 登録の成否
	 */

	// DB登録
	public boolean register(UserForm form, Model model) {

		String msg = "";

		//引数の入力チェックを行う
		//１つでもエラーがあった場合には、処理を異常終了する

		//未入力の場合
		if (form.getUserName().equals("") || form.getPassword1().equals("") || form.getPassword2().equals("")) {
			msg = "空欄があります";
			model.addAttribute("msg", msg);
			return false;
		}

		//半角英数字以外の文字が入力された場合
		if (!(form.getUserName().matches("^[0-9a-zA-Z]+$")) || !(form.getPassword1().matches("^[0-9a-zA-Z]+$"))
				|| !(form.getPassword2().matches("^[0-9a-zA-Z]+$"))) {
			msg = "半角英数字で入力してください";
			model.addAttribute("msg", msg);
			return false;
		}

		//パスワードとパスワード(確認)が不一致の場合
		if (!(form.getPassword1().equals(form.getPassword2()))) {
			msg = "パスワードが一致していません";
			model.addAttribute("msg", msg);
			return false;
		}

		//10文字以上の場合
		if (form.getUserName().length() > 10 || form.getPassword1().length() > 10
				|| form.getPassword2().length() > 10) {
			msg = "10文字以内で入力してください";
			model.addAttribute("msg", msg);
			return false;
		}

		//4文字以下の場合(パスワードとパスワード(確認))
		if (form.getPassword1().length() < 4 || form.getPassword2().length() < 4) {
			msg = "パスワードは4文字以上で入力してください";
			model.addAttribute("msg", msg);
			return false;
		}

		//ユーザマスタからレコードを検索する
		RegisterEntity registerEntity = repository.findUserByUserName(form.getUserName());

		//引数のユーザ名がすでに登録されているレコードと一致した場合
		if (registerEntity != null) {
			msg = "ユーザ名はすでに使用されています";
			model.addAttribute("msg", msg);
			return false;
		}

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
