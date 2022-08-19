package com.gips.ourapp.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.gips.ourapp.forms.UserForm;

@Service
public class SessionCheckService {

	// セッション
	@Autowired
	HttpSession session;

	public String sessionCheck(Model model) {

		// インスタンス化
		UserForm sessionForm = new UserForm();

		String name ="";

		// セッションの情報を取得
		sessionForm = (UserForm) session.getAttribute("form");
		// 情報があれば、ユーザ名をモデルに追加
		if (sessionForm != null) {
			name = "ID：" + sessionForm.getUserName();
			model.addAttribute("userName", name);
		}

		return name;
	}
}