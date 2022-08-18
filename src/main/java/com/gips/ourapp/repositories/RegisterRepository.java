/**
 *
 */
package com.gips.ourapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gips.ourapp.entities.RegisterEntity;

/**
 *
 * JpaRepositoryを継承したRepositoryインターフェース
 *
 * DBを操作する
 *
 */
public interface RegisterRepository extends JpaRepository<RegisterEntity, String> {

	// ユーザテーブルから指定されたユーザネームのレコードを取得するメソッド
	@Query(value = "select * from m_user where user_name = ?1", nativeQuery = true)
	RegisterEntity findUserByUserName(String userName);

}
