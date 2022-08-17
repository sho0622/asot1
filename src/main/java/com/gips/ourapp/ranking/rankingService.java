/**
 *
 */
//DBを操作するためにJpaRepositoryを継承するRepositoryインターフェースを作る

package com.gips.ourapp.ranking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.ourapp.entity.rankingEntity;


@Service
public class rankingService {


		@Autowired

		//インスタンス化と結び付け
		RankingRepository RankingRepository;

		public List<rankingEntity> findAllUser() {
			return RankingRepository.findAllUser();
		}

		public void user(rankingEntity rankingEntity) {
			RankingRepository.save(rankingEntity);


		}


	}

