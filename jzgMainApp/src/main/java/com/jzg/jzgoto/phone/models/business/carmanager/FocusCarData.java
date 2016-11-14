package com.jzg.jzgoto.phone.models.business.carmanager;


import com.jzg.jzgoto.phone.models.RecommendCardData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class FocusCarData implements Serializable {

    public int MyCareCarId;
    public String MakeId;
    public String ModelId;
    public String ModelName;
    public String ModelPic;
    public String MakeName;
    public String MakeModelName;
    public String MkMdName;
    public List<FocusCarCardData> InfoList;
    public List<RecommendCardData> cardDataList;

    public FocusCarData() {
    }

    public int getFocusCarId() {
        return MyCareCarId;
    }

    public String getMakeId() {
        return MakeId;
    }
	
	public String getModelId() {
        return ModelId;
    }

    public String getMakeModelName() {
        return MakeModelName;
    }

    public String getMkMdName() {
        return MkMdName;
    }

    public String getMakeName() {
        return MakeName;
    }

    public String getModelName() {
        return ModelName;
    }

    public String getImageUrl() {
        return ModelPic;
    }

    public List<RecommendCardData> getCardDataList(){
        if (cardDataList == null){
            cardDataList = new ArrayList<RecommendCardData>();
            if (InfoList != null && InfoList.size() > 0){
                RecommendCardData recommendCardData = null;
                FocusCarCardData focusCarCardData = null;
                for (int i = 0; i < InfoList.size(); i++){
                    focusCarCardData = InfoList.get(i);
                    recommendCardData = new RecommendCardData();
                    recommendCardData.setCardId(focusCarCardData.getCardId());
                    recommendCardData.setTitle(focusCarCardData.getTitle());
                    recommendCardData.setDescription(focusCarCardData.getDescription());
                    recommendCardData.setImageUrl(focusCarCardData.getImageUrl());
                    recommendCardData.setWebUrl(focusCarCardData.getWebUrl());
                    cardDataList.add(recommendCardData);
                }
            }
        }
        return cardDataList;
    }

};
