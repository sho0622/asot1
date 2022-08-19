package com.gips.ourapp.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.ourapp.entities.RegisterEntity;
import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.repositories.RegisterRepository;

/**
 * サービス(ログイン)
 *
 * ログインのビジネスロジックを実装する
 *
 */
@Service
public class LoginService {

	// セッション
	@Autowired
	HttpSession session;
	// リポジトリインターフェース
	@Autowired
	RegisterRepository repository;

	/**
	 * 認証
	 * ユーザネームとパスワードによる認証処理のメソッド
	 *
	 * @param userName	ユーザネーム
	 * @param password	パスワード
	 * @return ログインの成否
	 */
	public boolean login(UserForm form) {

		//ユーザマスタからレコードを検索する
		RegisterEntity entity = repository.findUserByUserName(form.getUserName());

		// 引数の入力チェックを行う
		// １つでもエラーがあった場合には、処理を異常終了する

		// 未入力の場合
		if (form.getUserName().equals("") || form.getPassword1().equals("")) {
			return false;
		}

		//半角英数字以外の文字が入力された場合
		if (!(form.getUserName().matches("^[0-9a-zA-Z]+$")) || !(form.getPassword1().matches("^[0-9a-zA-Z]+$"))) {
			return false;
		}

		//10桁以上の場合
		if (form.getUserName().length() >= 10 || form.getPassword1().length() >= 10) {
			return false;
		}

		//4桁以下の場合(パスワード)
		if (form.getPassword1().length() <= 4) {
			return false;
		}

		//レコードが１件も取得できなかった場合
		if (entity == null) {
			return false;
		}
		// パスワードが一致しない場合
		if (!entity.getPassword().equals(form.getPassword1())) {
			return false;
		}

		//正常に処理された場合は、取得したレコードをsessionに保存する
		session.setAttribute("form", form);

		// 処理を正常終了する
		return true;
	}

}
