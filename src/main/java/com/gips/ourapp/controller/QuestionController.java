package com.gips.ourapp.controller;

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

	@PostMapping("/result")
	public String result(AnswerForm aform, UserForm uform,Model model) {

		// String debug = "";
		String scoreMsg = "";
		int score = 0;
		// 宣言して セッションの情報を取得
		List<QuestionForm> rList = new ArrayList<>();
		rList = (List<QuestionForm>) session.getAttribute("sessionForm");

		// String answer[] = aform.getAnswer();

		// 返り値の正解数をscoreに入れる。 配列ではリクエストは処理できないのでfor文は使えない
		score += service.checkAnswer(aform.getAnswer1(), rList.get(0), model);
		score += service.checkAnswer(aform.getAnswer2(), rList.get(1), model);
		score += service.checkAnswer(aform.getAnswer3(), rList.get(2), model);
		score += service.checkAnswer(aform.getAnswer4(), rList.get(3), model);
		score += service.checkAnswer(aform.getAnswer5(), rList.get(4), model);
		score += service.checkAnswer(aform.getAnswer6(), rList.get(5), model);
		score += service.checkAnswer(aform.getAnswer7(), rList.get(6), model);
		score += service.checkAnswer(aform.getAnswer8(), rList.get(7), model);
		score += service.checkAnswer(aform.getAnswer9(), rList.get(8), model);
		score += service.checkAnswer(aform.getAnswer10(), rList.get(9), model);

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
