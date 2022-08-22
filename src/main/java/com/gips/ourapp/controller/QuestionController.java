package com.gips.ourapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gips.ourapp.forms.QuestionForm;
import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.services.QuestionService;
import com.gips.ourapp.services.SessionCheckService;
//import com.gips.ourapp.services.SessionCheckService;

@Controller
public class QuestionController {
	@Autowired
	private QuestionService service;
	@Autowired
	private SessionCheckService sessionCheck;

	// 採点でも使えるようにメンバ変数にListを設定
	List<QuestionForm> rList = new ArrayList<>();

	@RequestMapping("/questions")
	String questions(Model model) {

		sessionCheck.sessionCheck(model);

		// 問題をランダムで10件取得してList型でリターンされる
		List<QuestionForm> qList = service.setQuestion();
		// 採点結果にデータを渡すためメンバ変数に代入
		rList = qList;

		model.addAttribute("ListForms", rList);

		return "questions";
	}

	@PostMapping("/result")
	public String result(@RequestParam String answer1, String answer2, String answer3, String answer4, String answer5,
			String answer6, String answer7, String answer8, String answer9, String answer10, UserForm form,
			Model model) {

		// String debug = "";
		String scoreMsg = "";
		int score = 0;
		// 返り値の正解数をスコアに入れる。 for文はRequestParamでは使えないので１０問分記述する。
		score += service.checkAnswer(answer1, rList.get(0), model);
		score += service.checkAnswer(answer2, rList.get(1), model);
		score += service.checkAnswer(answer3, rList.get(2), model);
		score += service.checkAnswer(answer4, rList.get(3), model);
		score += service.checkAnswer(answer5, rList.get(4), model);
		score += service.checkAnswer(answer6, rList.get(5), model);
		score += service.checkAnswer(answer7, rList.get(6), model);
		score += service.checkAnswer(answer8, rList.get(7), model);
		score += service.checkAnswer(answer9, rList.get(8), model);
		score += service.checkAnswer(answer10, rList.get(9), model);

		scoreMsg = score + "問正解です。";
		// スコアを更新する処理
		service.checkScore(score, form, scoreMsg, model);

		// debug = "debug:" + rList;
		model.addAttribute("ListForms", rList);

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
