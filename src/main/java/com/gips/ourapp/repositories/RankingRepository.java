package com.gips.ourapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gips.ourapp.entities.RankingEntity;


public interface RankingRepository extends JpaRepository<RankingEntity, String> {





//ユーザテーブルから指定されたユーザNAMEのレコードを取得するメソッド
	@Query(value="select * from m_user ORDER BY correct_num DESC LIMIT 10", nativeQuery = true)

	List<RankingEntity> findUserByUserNAME();
}
