package com.gips.ourapp.login;

import lombok.Data;

/**
 * フォーム(ログイン画面)
 *
 * ログイン画面のビューとサービスでやり取りするデータ
 *
 */
@Data
public class LoginForm {

	// ユーザID
	private String userId;
	// パスワード
	private String password;

}
