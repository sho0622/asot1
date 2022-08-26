package com.gips.ourapp.controller;

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

	// セッションサービス
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

		//ログインユーザがアクセスした場合、/ にリダイレクトする
		String sessionService = session.sessionCheck(model);
		if (sessionService != null) {
			return "redirect:";
		}

		// ログイン画面のフォームをインスタンス化し、モデルに追加する
		UserForm form = new UserForm();
		model.addAttribute("userForm", form);

		// ログイン画面のビューを返却する
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

		// 認証サービスを呼び出す
		// 結果が正常ではなかった場合にはログイン画面のビューを返却して処理を終了する
		if (!service.login(form, model)) {
			model.addAttribute("form", form);
			return "login";
		}

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
