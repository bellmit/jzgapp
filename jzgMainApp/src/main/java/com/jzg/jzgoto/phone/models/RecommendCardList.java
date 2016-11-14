package com.jzg.jzgoto.phone.models;


import java.io.Serializable;
import java.util.List;

public class RecommendCardList implements Serializable {

		public int CardNum;
		public List<RecommendCardData> CardList;
		
		public RecommendCardList(){
		}

		public void setCardNum(int num){
			CardNum = num;
		}
		
		public int getCardNum(){
			return CardNum;
		}
		
		public void setCardList(List<RecommendCardData> cardList){
			CardList = cardList;
		}
		
		public List<RecommendCardData> getCardList(){
			return CardList;
		}
		
};
