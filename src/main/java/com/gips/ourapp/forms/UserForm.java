package com.gips.ourapp.forms;

import lombok.Data;

/**
 *
 * フォーム(ユーザ登録画面)
 *
 * ユーザ登録画面のビューとサービスでやり取りするデータ
 *
 */
@Data
public class UserForm {

	// ユーザネーム
	private String userName;
	// パスワード
	private String password1;
	//パスワード(確認)
	private String password2;
	//正解数
	private int correctNum;

}
