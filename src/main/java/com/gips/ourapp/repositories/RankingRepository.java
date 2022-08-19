package com.gips.ourapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gips.ourapp.entities.rankingEntity;


public interface RankingRepository extends JpaRepository<rankingEntity, String> {


//テールを全体取得するメソッド
@Query(value = "select * from m_user", nativeQuery = true)

//取得結果はrankingEntityクラスのListで返却

List<rankingEntity> findAllUser();


//ユーザテーブルから指定されたユーザNAMEのレコードを取得するメソッド
	@Query(value="select * from m_user ORDER BY correct_num DESC LIMIT 10", nativeQuery = true)

	List<rankingEntity> findUserByUserNAME();
}