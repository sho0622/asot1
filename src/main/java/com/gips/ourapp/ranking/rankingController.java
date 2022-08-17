package com.gips.ourapp.ranking;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.entity.rankingEntity;

@Controller
public class rankingController {

	// サービスクラスのインスタンス化
	@Autowired
	private rankingService service;

	//listにアクセスしたときに呼ばれるメソッド
	@RequestMapping("/ranking")
	public String show(Model model) {

		// ビューに表示するレコードの取得
		List<rankingEntity> entities = service.findAllUser();

		// ビューに渡すフォームクラスのリストのインスタンス化
		ArrayList<rankingForm> rankingFormList = new ArrayList<>();

		// 取得したレコードを1件ずつフォームのリストに追加
		for (rankingEntity entity : entities) {
			//1件分のフォームクラスのインスタント化
			rankingForm outputForm = new rankingForm();


			// ユーザ名
			outputForm.setName(entity.getName());
			//正解点数
			outputForm.setScore(entity.getScore());

			// 1件分のフォームクラスをリストに追加

			rankingFormList.add(outputForm);

		}
		// フォームクラスのリストをモデルに追加してビューに渡す
		model.addAttribute("rankinglistForms", rankingFormList);
		
		//rankingListの画面遷移
		return "rankingList";
	}
}