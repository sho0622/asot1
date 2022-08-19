package com.gips.ourapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gips.ourapp.entities.QuestionEntity;
import com.gips.ourapp.forms.QuestionForm;
import com.gips.ourapp.repositories.QuestionRepository;
@Service
public class QuestionService {
	@Autowired
	QuestionRepository questionRepository;


	// ランダムに１０件取得するSQL
	public List<QuestionEntity> findQuestion() {
		return questionRepository.findQuestion();
	}
	// ユーザー情報を取得
	//	public UserEntity findUser(String user_name) {
	//		return questionRepository.findUser(user_name);
	//	}

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
		model.addAttribute(answer, answer);
		return score;
	}

	public void setScore() {

	}
}
