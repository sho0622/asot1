/**
 *
 */
package com.gips.ourapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 *
 * Entityクラス
 *
 * コントローラとDBで項目のやりとりに使う
 *
 */
@Entity
@Table(name = "m_user")
@Data
public class RegisterEntity {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "user_id")
	private int userId;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "correct_num")
	private int correctNum;

}
