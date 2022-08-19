package com.gips.ourapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.entities.rankingEntity;
import com.gips.ourapp.forms.rankingForm;
import com.gips.ourapp.services.rankingService;

@Controller
public class rankingController {

	// サービスクラスのインスタンス化
	@Autowired
	private rankingService service;

	//listにアクセスしたときに呼ばれるメソッド
	@RequestMapping("/ranking")
	public String show(Model model) {
		//順位をつけるため、変数を宣言する
        int i = 0;
         int j =0;
         int k =0;


		// ビューに表示するレコードの取得
		List<rankingEntity> entities = service.findUserByUserNAME();

		// ビューに渡すフォームクラスのリストのインスタンス化
		ArrayList<rankingForm> rankingFormList = new ArrayList<>();

		// 取得したレコードを1件ずつフォームのリストに追加

		for (rankingEntity entity : entities) {
			//1件分のフォームクラスのインスタント化
			rankingForm outputForm = new rankingForm();

			//点数が同じの場合
			if( k ==entity.getScore()) {
				j=j+1;
				//前のユーザーNameと同じの順位を取得する
				outputForm.setRank(i+1-j);
				i++;

				//順位が違う場合
			}else {
				outputForm.setRank(i+1);
				//Kで点数を表示
				k =entity.getScore();
				j=0;
				i++;
			}

			// ユーザ名
			outputForm.setName(entity.getName());
			//正解点数
			outputForm.setScore(entity.getScore());


			// 1件分のフォームクラスをリストに追加
			rankingFormList.add(outputForm);


			// フォームクラスのリストをモデルに追加してビューに渡す
			model.addAttribute("rankinglistForms", rankingFormList);
		}
		//rankingListの画面遷移
		return "rankingList";
	}
}


