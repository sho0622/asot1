/**
 *
 */
package com.gips.ourapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
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

		//ログインユーザがアクセスした場合、/ にリダイレクトする
		String sService =sessionService.sessionCheck(model);

		if (sService != null) {
			return "redirect:";
		}


		// ユーザ登録画面のフォームをインスタンス化し、モデルに追加する
		UserForm form = new UserForm();
		model.addAttribute("userForm", form);

		// ログイン画面のビューを返却する
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
		boolean result = service.register(form, model);

		if (!result) {
			// 結果が正常ではなかった場合にはユーザ登録画面のビューを返却して処理を終了する
			model.addAttribute("form", form);
			return "register";
		}

		//情報をセッションに保存する
		session.setAttribute("form", form);
		// インターフェース画面をリダイレクトする
		return "redirect:";
	}

	// サーバエラー時の処理
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

		// 例外クラスのメッセージをModelに登録
		model.addAttribute("message", "サーバエラーが発生しました。時間をおいてからアクセスして下さい");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

}
