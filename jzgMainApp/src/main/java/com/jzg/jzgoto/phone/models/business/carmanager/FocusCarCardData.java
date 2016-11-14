package com.jzg.jzgoto.phone.models.business.carmanager;


import java.io.Serializable;


public class FocusCarCardData implements Serializable {

        public int CardID;
		public String Pic;
		public String Url;
		public String Name;
		public String Content;
		public String ParentID;
		
		public FocusCarCardData(){
		}


		public void setCardId(int id){
			this.CardID = id;
		}

		public int getCardId(){
			return CardID;
		}

		public void setTitle(String titlel){
			this.Name = titlel;
		}

		public String getTitle(){
			return Name;
		}

		public void setDescription(String description){
			this.Content = description;
		}

		public String getDescription(){
			return Content;
		}

		public void setImageUrl(String bannerImageUrl){
			this.Pic = bannerImageUrl;
		}

		public String getImageUrl(){
			return Pic;
		}

		public void setWebUrl(String webUrl){
			this.Url = webUrl;
		}

		public String getWebUrl(){
			return Url;
		}

};
