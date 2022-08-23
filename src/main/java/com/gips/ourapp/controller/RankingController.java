package com.gips.ourapp.controller;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gips.ourapp.forms.RankingForm;
import com.gips.ourapp.services.RankingService;
import com.gips.ourapp.services.SessionCheckService;

@Controller
public class RankingController {
	//セッション
	@Autowired
	SessionCheckService session;
	@Autowired
	// サービスクラスのインスタンス化
	private RankingService service;

	//listにアクセスしたときに呼ばれるメソッド
	@RequestMapping("/ranking")
	public String show(Model model) {

		session.sessionCheck(model);

		// ビューに渡すフォームクラスのリストのインスタンス化
		ArrayList<RankingForm> rankFormList = service.setRank();


			// フォームクラスのリストをモデルに追加してビューに渡す
			model.addAttribute("rankinglistForms", rankFormList);

	//rankingListの画面遷移
	return"rankingList";
}
}