/**
 *
 */
//DBを操作するためにJpaRepositoryを継承するRepositoryインターフェースを作る

package com.gips.ourapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.ourapp.entities.rankingEntity;
import com.gips.ourapp.repositories.RankingRepository;

@Service
public class rankingService {

	@Autowired

	//インスタンス化と結び付け
	RankingRepository RankingRepository;

	//10個のデータを取り込む
	public List<rankingEntity> findUserByUserNAME() {
		return RankingRepository.findUserByUserNAME();

	}
	//entityを呼び出す
	public void user(rankingEntity rankingEntity) {
		RankingRepository.save(rankingEntity);
	}

}