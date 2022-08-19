package com.gips.ourapp.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gips.ourapp.entities.QuestionEntity;
import com.gips.ourapp.entities.RegisterEntity;
import com.gips.ourapp.forms.QuestionForm;
import com.gips.ourapp.forms.UserForm;
import com.gips.ourapp.repositories.QuestionRepository;
import com.gips.ourapp.repositories.RegisterRepository;


@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;
	@Autowired
	RegisterRepository registerRepository;
	@Autowired
	HttpSession session;
	//@Autowired
	//private SessionCheckService sessionCheck;


	// ランダムに１０件取得するSQL
	public List<QuestionEntity> findQuestion() {
		return questionRepository.findQuestion();
	}

	// ランダムに１０件取得して、Listでリターンする
	public List<QuestionForm> setQuestion() {

		// 可変長配列にランダムで10問のカラムを代入
		List<QuestionEntity> qInfos = findQuestion();

		// 空の可変長配列を用意
		List<QuestionForm> qList = new ArrayList<>();

		// 拡張forでユーザーを１問ずつ取り出す
		for (QuestionEntity qInfo : qInfos) {
			QuestionForm qform = new QuestionForm();
			// userInfoのgetterはUserEntitiyからのもの
			qform.setQuestion_id(qInfo.getId());
			qform.setQuestion(qInfo.getQuestion());
			qform.setCorrect(qInfo.getCorrect());
			qform.setChoice1(qInfo.getChoice1());
			qform.setChoice2(qInfo.getChoice2());
			qform.setChoice3(qInfo.getChoice3());
			qform.setChoice4(qInfo.getChoice4());
			qList.add(qform);
		}
		return qList;
	}

	// リクエストされた正誤をチェック
	public int setAnswer(String answer, QuestionForm rList, Model model) {
		int score = 0;
		QuestionForm rInfo = rList;
		rInfo.setAnswer(answer);
		if (rInfo.getCorrect().equals(answer)) {
			String msg = "正解〇";
			rInfo.setRight(msg);
			score += 1;
		} else {
			String msg = "不正解× 正解は";
			msg += rInfo.getCorrect();
			rInfo.setWrong(msg);
		}

		// 第一引数はタイムリーフで使う文字列
		model.addAttribute(answer, answer);
		// 正解数を返す
		return score;
	}

	// 正解数をDBに保存
	public void setScore(int score, UserForm form, String scoreMsg, Model model) {


		// セッションを取得
		//sessionCheck.sessionCheck(model);
		UserForm sform = (UserForm) session.getAttribute("form");
		// セッションにログイン情報があればリストを表示
		if (null != sform) {
			RegisterEntity userInfo = registerRepository.findUserByUserName(sform.getUserName());

			// 最高得点を上回ったら更新
			if (userInfo.getCorrectNum() < score) {
				userInfo.setCorrectNum(score);
				registerRepository.save(userInfo);
				scoreMsg += "　最高得点を更新しました。";
			}
		}

		model.addAttribute("scoreMsg", scoreMsg);

		// native queryのupdate文だとエラーが出た。
		// String username = userInfo.getUser_name();
		// userRepository.updateScore(score, username);// スコア記録

	}
}
