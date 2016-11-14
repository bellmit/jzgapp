package com.jzg.jzgoto.phone.models;


import java.io.Serializable;

public class BannerData implements Serializable {

		public int ID;
		public String ImageUrl;
		public String BannerUrl;
		public String BannerName;
		public String bannerDescription;
		public BannerData(){
		}

		public BannerData(String bannerImageUrl, String bannerWebUrl){
			this.ImageUrl = bannerImageUrl;
			this.BannerUrl = bannerWebUrl;
		}

		public BannerData(String title, String description, String bannerImageUrl, String bannerWebUrl){
			this.BannerName = title;
			this.bannerDescription = description;
			this.ImageUrl = bannerImageUrl;
			this.BannerUrl = bannerWebUrl;
		}

		public void setBannerId(int id){
		this.ID = id;
	}

		public int getBannerId(){
		return ID;
	}

		public void setTitle(String titlel){
			this.BannerName = titlel;
		}

		public String getTitle(){
			return BannerName;
		}

		public void setDescription(String description){
			this.bannerDescription = description;
		}

		public String getDescription(){
			return bannerDescription;
		}

		public void setImageUrl(String bannerImageUrl){
			this.ImageUrl = bannerImageUrl;
		}

		public String getImageUrl(){
			return ImageUrl;
		}

		public void setWebUrl(String webUrl){
			this.BannerUrl = webUrl;
		}

		public String getWebUrl(){
			return BannerUrl;
		}
};
