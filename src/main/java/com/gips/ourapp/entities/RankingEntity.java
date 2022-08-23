package com.gips.ourapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//ビューとコントローラで登録したユーザのデータを取るためのフォームクラス

@Entity
@Table(name = "m_user")
@Data

public class RankingEntity {

	@Id
	//ユーザー名前
	@Column(name = "user_name")
	private String name;

	//ユーザーパスワード
	@Column(name = "password")
	private String password;

	//正解点数
	@Column(name = "correct_num")
	private int score;

}
