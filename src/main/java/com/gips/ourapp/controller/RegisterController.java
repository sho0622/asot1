/**
 *
 */
package com.gips.ourapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.forms.RegisterForm;
import com.gips.ourapp.services.RegisterService;

/**
 * コントローラ(ユーザ登録画面)
 *
 * ユーザ登録画面のビューとサービスを制御する
 *
 */
@Controller
public class RegisterController {

	// セッション
	@Autowired
	HttpSession session;
	// 認証サービス
	@Autowired
	RegisterService service;

	/**
	 * ユーザ登録画面初期表示
	 * ルートにGETでアクセスされた際に呼び出されるメソッド
	 *
	 * @param model	モデル
	 * @return 呼出すビュー
	 */
	@RequestMapping("/register")
	String init(Model model) {

		// ユーザ登録画面のFormをインスタンス化し、Modelに追加する。
		RegisterForm form = new RegisterForm();
		model.addAttribute("registerForm", form);

		// ログイン画面のViewを返却する。
		return "register";
	}

	/**
	 * 登録
	 * ルートにPOSTでアクセスされた際に呼び出されるメソッド
	 *
	 * @param form	ビューから受け取るフォーム
	 * @param model	モデル
	 * @return 呼出すビュー
	 */

	@PostMapping("/registerUser")
	public String register(@ModelAttribute RegisterForm form, Model model) {

		// DB登録
		service.register(form);

		// フォームクラスをモデルに追加
		model.addAttribute("form", form);

		//情報をsessionに保存する
		session.setAttribute("form", form);

		// インターフェース画面をリダイレクトする。
		return "redirect:";
	}

}
