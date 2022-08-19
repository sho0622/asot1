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

import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.services.RegisterService;
import com.gips.ourapp.services.SessionCheckService;

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

	//セッションサービス
	@Autowired
	SessionCheckService sessionService;

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

		//ログインユーザーがアクセスした場合、/ にリダイレクトする。
		String sService =sessionService.sessionCheck(model);

		if (sService != null) {
			return "redirect:";
		}


		// ユーザ登録画面のFormをインスタンス化し、Modelに追加する。
		UserForm form = new UserForm();
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
	public String register(@ModelAttribute UserForm form, Model model) {

		// DB登録
		boolean result = service.register(form);

		// フォームクラスをモデルに追加
		model.addAttribute("form", form);

		if (result) {
			//情報をsessionに保存する
			session.setAttribute("form", form);
		} else {
			return "redirect:register";
		}

		// インターフェース画面をリダイレクトする。
		return "redirect:";
	}

}
