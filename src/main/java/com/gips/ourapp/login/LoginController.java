package com.gips.ourapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.SessionBean;

/**
 * コントローラ(ログイン) test1
 *
 * ログインのビューとサービスを制御する
 *
 */
@Controller
public class LoginController {

	// セッションスコープBean
	@Autowired
	SessionBean sessionBean;

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
	@RequestMapping("/")
	String init(Model model) {

		// ログイン画面のFormをインスタンス化し、Modelに追加する。
		LoginForm form = new LoginForm();
		model.addAttribute("form", form);

		// ログイン画面のViewを返却する。
		return "login";
	}

	/**
	 * 認証
	 * ルートにPOSTでアクセスされた際に呼び出されるメソッド
	 *
	 * @param form		ビューから受け取るフォーム
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@PostMapping("/")
	String login(@ModelAttribute LoginForm form, Model model) {

		// 認証サービスを呼び出す。
		// 結果が正常ではなかった場合にはログイン画面のViewを返却して処理を終了する。
		if (!service.login(form.getUserId(), form.getPassword())) {
			model.addAttribute("form", form);
			return "login";
		}

		// /listをリダイレクトし、ユーザ一覧表示処理を呼び出す。
		return "redirect:list";
	}

	/**
	 * ユーザ一覧表示
	 *
	 * @return 呼出すビュー
	 */
	@RequestMapping("/list")
	String list() {

		// ユーザ一覧画面のViewを返却する。
		return "userList";
	}
}
