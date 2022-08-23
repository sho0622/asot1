//DBを操作するためにJpaRepositoryを継承するRepositoryインターフェースを作る
package com.gips.ourapp.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gips.ourapp.entities.RankingEntity;
import com.gips.ourapp.forms.RankingForm;
import com.gips.ourapp.repositories.RankingRepository;

@Service
public class RankingService {
	@Autowired
	//インスタンス化と結び付け
	RankingRepository RankingRepository;

	//10個のデータを取り込む
	public List<RankingEntity> findUserByUserNAME() {

		return RankingRepository.findUserByUserNAME();

	}

	public ArrayList<RankingForm> setRank() {
		//順位をつけるため、変数を宣言する
		int i = 0;
		int j = 0;
		int k = 0;

    	   List<RankingEntity> entities =findUserByUserNAME();
    	   ArrayList<RankingForm> rankFormList = new ArrayList<>();

    	   for (RankingEntity entity : entities) {

   			//1件分のフォームクラスのインスタント化
   			RankingForm outputForm = new RankingForm();
   			//点数が同じの場合
   			if (k!=0 && k == entity.getScore()) {
   				j = j + 1;
   				//前のユーザーNameと同じの順位を取得する
   				outputForm.setRank(i + 1 - j);
   				i++;
   				//順位が違う場合
   			} else {
   				outputForm.setRank(i + 1);
   				//Kで点数を表示
   				k = entity.getScore();
   				j = 0;
   				i++;
   			}// ユーザ名
			outputForm.setName(entity.getName());
			//正解点数
			outputForm.setScore(entity.getScore());
			// 1件分のフォームクラスをリストに追加
			rankFormList.add(outputForm);
    	   }

       return rankFormList;
	}





}
