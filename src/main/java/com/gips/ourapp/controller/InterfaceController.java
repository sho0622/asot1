package com.gips.ourapp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.services.SessionCheckService;

/**
 * コントローラ(インターフェース)
 *
 * 各ビューに遷移する
 *
 */
@Controller
public class InterfaceController {

	// セッション
	@Autowired
	HttpSession session;

	// サービス
	@Autowired
	SessionCheckService service;

	/**
	 *インターフェース画面初期表示
	 *GETでアクセスされた際に呼び出されるメソッド
	 *
	 * @param model モデル
	 * @return 呼び出すビュー
	 */
	@RequestMapping("/")
	String home(Model model) {

		// セッションの情報を取得して、ユーザ名をモデルに追加するメソッドを呼び出す
		service.sessionCheck(model);

		// ビューを返却する
		return "interface";
	}

	/**
	 * インターフェース画面表示
	 * ログアウトボタン押下でアクセスされた際に呼び出されるメソッド
	 *
	 * @return 呼び出すビュー
	 */
	@RequestMapping("/logout")
	String logOut() {

		// セッションの情報を削除
		session.invalidate();

		// ビューを返却する
		return "redirect:/";
	}
}