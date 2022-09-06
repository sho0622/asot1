package com.gips.ourapp.controller;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.forms.AnswerForm;
import com.gips.ourapp.forms.QuestionForm;
import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.services.QuestionService;
import com.gips.ourapp.services.SessionCheckService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService service;
	@Autowired
	private SessionCheckService sessionCheck;
	@Autowired
	HttpSession session;

	@RequestMapping("/questions")
	String questions(Model model) {
		// ヘッダのログインを維持するための共通処理
		sessionCheck.sessionCheck(model);

		// 問題をランダムで10件取得してList型でリターンされる
		List<QuestionForm> qList = service.makeQuestion();

		//取得したレコードをセッションに保存する
		session.setAttribute("sessionForm", qList);

		model.addAttribute("ListForms", qList);

		return "questions";
	}

	@SuppressWarnings("unchecked")
	@PostMapping("/result")
	public String result(AnswerForm aform, UserForm uform, Model model)
			throws ReflectiveOperationException, SecurityException {

		// String debug = "";
		String scoreMsg = "";
		int score = 0;
		// 宣言して セッションの情報を取得
		List<QuestionForm> rList = new ArrayList<>();
		rList = (List<QuestionForm>) session.getAttribute("sessionForm");

		// リフレクションで動的に処理

		for (int i = 1; i <= rList.size(); i++) {
			StringBuilder sb = new StringBuilder();
			//	メソッドを定義 i=1 のとき、callMethod は "getAnswer1"
			String callMethod = sb.append("getAnswer").append(String.format("%d", i)).toString();
			Method method = aform.getClass().getMethod(callMethod);
			// メソッドを文字列にする
			String answer = String.valueOf(method.invoke(aform));
			// サービスを呼び出し、引数にメソッドを使用
			score += service.checkAnswer(answer, rList.get(i - 1), model);

			// 消して初期化(初期化しないとanswer1anser2() と追記されて表示される)
			callMethod += sb.delete(0, sb.length());

			// 第一引数はタイムリーフで使う文字列, for文で処理できないのでここでmodelを処理することにした
			model.addAttribute("answer" + i, answer);

		}

		scoreMsg = score + "問正解です。";
		// スコアを更新する処理
		scoreMsg += service.checkScore(score, uform, model);

		model.addAttribute("scoreMsg", scoreMsg);
		model.addAttribute("ListForms", rList);
		// 不正操作させないため出題のセッションを消す
		session.removeAttribute("sessionForm");
		return "result";
	}

	// サーバエラー時の処理
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {
		// 例外クラスのメッセージをModelに登録
		model.addAttribute("error", "内部サーバーエラー（DB）：ExceptionHandler");

		// 例外クラスのメッセジをModelに登録
		model.addAttribute("message", "サーバエラーが発生しました。時間をおいてからアクセスして下さい");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}
}
