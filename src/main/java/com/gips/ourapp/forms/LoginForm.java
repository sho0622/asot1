package com.gips.ourapp.forms;

import lombok.Data;

/**
 * フォーム(ログイン画面)
 *
 * ログイン画面のビューとサービスでやり取りするデータ
 *
 */
@Data
public class LoginForm {

	// ユーザネーム
	private String userName;
	// パスワード
	private String password;

}
