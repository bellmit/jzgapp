package com.jzg.jzgoto.phone.models;


import java.io.Serializable;

public class RecommendCardData implements Serializable {

		public int CardID;
		public String ImageUrl;
		public String Url;
		public String Name;
		public String CardDescription;
		public int ParentID;
		public String tile;

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

	public RecommendCardData(){
		}


		public void setCardId(int id){
			this.ParentID = id;
		}

		public int getCardId(){
			return ParentID;
		}

		public void setTitle(String titlel){
			this.Name = titlel;
		}

		public String getTitle(){
			return Name;
		}

		public void setDescription(String description){
			this.CardDescription = description;
		}

		public String getDescription(){
			return CardDescription;
		}

		public void setImageUrl(String bannerImageUrl){
			this.ImageUrl = bannerImageUrl;
		}

		public String getImageUrl(){
			return ImageUrl;
		}

		public void setWebUrl(String webUrl){
			this.Url = webUrl;
		}

		public String getWebUrl(){
			return Url;
		}
};
