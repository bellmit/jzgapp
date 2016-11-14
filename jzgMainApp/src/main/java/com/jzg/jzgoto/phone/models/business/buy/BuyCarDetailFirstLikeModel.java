package com.jzg.jzgoto.phone.models.business.buy;


import java.io.Serializable;

/**
 * Created by WY on 2016/9/27.
 * Function :
 */
public class BuyCarDetailFirstLikeModel implements Serializable{
    private static final long serialVersionUID = 1L;
    private String MakeId;
    private String ModelId;
    private String ModelName;
    private String ModelPic;
    private String MakeName;
    private String MakeModelName;

    public String getMakeId() {
        return MakeId;
    }

    public void setMakeId(String MakeId) {
        this.MakeId = MakeId;
    }

    public String getModelId() {
        return ModelId;
    }

    public void setModelId(String ModelId) {
        this.ModelId = ModelId;
    }

    public String getModelName() {
        return ModelName;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    public String getModelPic() {
        return ModelPic;
    }

    public void setModelPic(String ModelPic) {
        this.ModelPic = ModelPic;
    }

    public String getMakeName() {
        return MakeName;
    }

    public void setMakeName(String makeName) {
        MakeName = makeName;
    }

    public String getMakeModelName() {
        return MakeModelName;
    }

    public void setMakeModelName(String makeModelName) {
        MakeModelName = makeModelName;
    }

    @Override
    public String toString() {
        return "BuyCarDetailFirstLikeModel{" +
                "MakeId='" + MakeId + '\'' +
                ", ModelId='" + ModelId + '\'' +
                ", ModelName='" + ModelName + '\'' +
                ", ModelPic='" + ModelPic + '\'' +
                ", MakeName='" + MakeName + '\'' +
                ", MakeModelName='" + MakeModelName + '\'' +
                '}';
    }
}
