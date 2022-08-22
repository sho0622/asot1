package com.gips.ourapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gips.ourapp.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, String> {
	// ランダムに１０件取得
	@Query(value = "select * from m_question order by random() limit 10;", nativeQuery = true)
	List<QuestionEntity> findQuestion();
}
