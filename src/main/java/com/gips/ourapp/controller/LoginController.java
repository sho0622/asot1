package com.gips.ourapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.services.LoginService;
import com.gips.ourapp.services.SessionCheckService;

/**
 * コントローラ(ログイン画面)
 *
 * ログイン画面のビューとサービスを制御する
 *
 */
@Controller
public class LoginController {

	// セッション
	@Autowired
	SessionCheckService session;

	// 認証サービス
	@Autowired
	LoginService service;

	/**
	 * ログイン画面初期表示
	 * ルートにGETでアクセスされた際に呼び出されるメソッド
	 *
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@RequestMapping("/login")
	String init(Model model) {

		// セッションの情報を取得して、ユーザ名をモデルに追加するメソッドを呼び出す
		session.sessionCheck(model);

		//ログインユーザーがアクセスした場合、/ にリダイレクトする。

		String sessionService = session.sessionCheck(model);

		if (sessionService != null) {
			return "redirect:";
		}

		// ログイン画面のFormをインスタンス化し、Modelに追加する。
		UserForm form = new UserForm();
		model.addAttribute("userForm", form);

		// ログイン画面のViewを返却する。
		return "login";
	}

	/**
	 * 認証
	 * ルートにPOSTでアクセスされた際に呼び出されるメソッド
	 *
	 * @param form	ビューから受け取るフォーム
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@PostMapping("/loginUser")
	String loginUser(@ModelAttribute UserForm form, Model model) {

		// 認証サービスを呼び出す。
		// 結果が正常ではなかった場合にはログイン画面のViewを返却して処理を終了する。
		if (!service.login(form)) {
			model.addAttribute("form", form);
			return "login";
		}

		// インターフェース画面をリダイレクトする。
		return "redirect:";
	}

}
