package com.jzg.jzgoto.phone.models.business.login;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.jzg.jzgoto.phone.models.common.BaseResultModels;
/**
 * @Description: 登录返回结果参数
 * @Package com.jzg.jzgoto.phone.models.business.login LoginResultModels.java
 * @author gf
 * @date 2015-12-23 上午9:43:47
 */
public class LoginResultModels extends BaseResultModels {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	
	private String pushID;
	private String pushStatus;
	private String isFirstLogin;
	
	private PersonalInfo GetPersonalInfo;
	@Override
	public void setResult(Object obj) {
		Log.i("gf", "setResult:" + (String)obj);
		Gson gson = new Gson();
		LoginResultModels models = gson.fromJson((String)obj, LoginResultModels.class);
		setMsg(models.getMsg());
		setStatus(models.getStatus());
		
		setPushID(models.getPushID());
		setPushStatus(models.getPushStatus());
		setGetPersonalInfo(models.getGetPersonalInfo());
		setIsFirstLogin(models.getIsFirstLogin());
		/*
		if(GetPersonalInfo != null){
			GetPersonalInfo.setPushID(pushID);
			GetPersonalInfo.setPushStatus(pushStatus);
		}
		*/
	}

	@Override
	public String toResultString() {
		return null;
	}
	
	public String getIsFirstLogin() {
		return isFirstLogin;
	}

	public void setIsFirstLogin(String isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public String getPushID() {
		return pushID;
	}

	public void setPushID(String pushID) {
		this.pushID = pushID;
	}

	public String getPushStatus() {
		return pushStatus;
	}

	public void setPushStatus(String pushStatus) {
		this.pushStatus = pushStatus;
	}

	public PersonalInfo getGetPersonalInfo() {
		return GetPersonalInfo;
	}

	public void setGetPersonalInfo(PersonalInfo getPersonalInfo) {
		GetPersonalInfo = getPersonalInfo;
	}

	public class PersonalInfo{
		
		private String pushID;
		private String pushStatus;
		
		private String Id;
		private String LoginName;
		private String LoginPwd;
		private String TrueName;
		
		private String Gendor;
		private String Age;
		private String DrivingYear;
		private String ProvinceId;
		private String ProvinceName;
		private String CityName;
		
		private String CityId;
		private String Mobile;
		private String ValidateMobile;
		private String Email;
		private String MakeId;
		
		private String ModelId;
		private String StyleId;
		private String ModelLevel1;
		private String ModelLevel2;
		private String ModelLevel3;
		
		private String AvatorPicPath;
		private String BasicAppraiseFlag;
		private String MediumAppraiseFlag;
		private String PublishCarFlag;
		private String CreateTime;
		private String LastModifyTime;
		private String Status;
		private String OtherAccountId;
		
		private String OtherSourceType;
		private String RegisteredSource;
		private String UserNumber;

		private String CarButlerId;

		public String getCarButlerId(){return CarButlerId;}
		public String getPushID() {
			return pushID;
		}
		public void setPushID(String pushID) {
			this.pushID = pushID;
		}
		public String getPushStatus() {
			return pushStatus;
		}
		public void setPushStatus(String pushStatus) {
			this.pushStatus = pushStatus;
		}
		public String getId() {
			return Id;
		}
		public void setId(String id) {
			Id = id;
		}
		public String getLoginName() {
			return LoginName;
		}
		public void setLoginName(String loginName) {
			LoginName = loginName;
		}
		public String getLoginPwd() {
			return LoginPwd;
		}
		public void setLoginPwd(String loginPwd) {
			LoginPwd = loginPwd;
		}
		public String getTrueName() {
			if(TextUtils.isEmpty(TrueName)||"null".equals(TrueName)){
				return "";
			}
			return TrueName;
		}
		public void setTrueName(String trueName) {
			TrueName = trueName;
		}
		public String getGendor() {
			return Gendor;
		}
		public void setGendor(String gendor) {
			Gendor = gendor;
		}
		public String getAge() {
			return Age;
		}
		public void setAge(String age) {
			Age = age;
		}
		public String getDrivingYear() {
			return DrivingYear;
		}
		public void setDrivingYear(String drivingYear) {
			DrivingYear = drivingYear;
		}
		public String getProvinceId() {
			return ProvinceId;
		}
		public void setProvinceId(String provinceId) {
			ProvinceId = provinceId;
		}
		public String getProvinceName() {
			return ProvinceName;
		}
		public void setProvinceName(String provinceName) {
			ProvinceName = provinceName;
		}
		public String getCityName() {
			return CityName;
		}
		public void setCityName(String cityName) {
			CityName = cityName;
		}
		public String getCityId() {
			return CityId;
		}
		public void setCityId(String cityId) {
			CityId = cityId;
		}
		public String getMobile() {
			return Mobile;
		}
		public void setMobile(String mobile) {
			Mobile = mobile;
		}
		public String getValidateMobile() {
			return ValidateMobile;
		}
		public void setValidateMobile(String validateMobile) {
			ValidateMobile = validateMobile;
		}
		public String getEmail() {
			return Email;
		}
		public void setEmail(String email) {
			Email = email;
		}
		public String getMakeId() {
			return MakeId;
		}
		public void setMakeId(String makeId) {
			MakeId = makeId;
		}
		public String getModelId() {
			return ModelId;
		}
		public void setModelId(String modelId) {
			ModelId = modelId;
		}
		public String getStyleId() {
			return StyleId;
		}
		public void setStyleId(String styleId) {
			StyleId = styleId;
		}
		public String getModelLevel1() {
			return ModelLevel1;
		}
		public void setModelLevel1(String modelLevel1) {
			ModelLevel1 = modelLevel1;
		}
		public String getModelLevel2() {
			return ModelLevel2;
		}
		public void setModelLevel2(String modelLevel2) {
			ModelLevel2 = modelLevel2;
		}
		public String getModelLevel3() {
			return ModelLevel3;
		}
		public void setModelLevel3(String modelLevel3) {
			ModelLevel3 = modelLevel3;
		}
		public String getAvatorPicPath() {
			return AvatorPicPath;
		}
		public void setAvatorPicPath(String avatorPicPath) {
			AvatorPicPath = avatorPicPath;
		}
		public String getBasicAppraiseFlag() {
			return BasicAppraiseFlag;
		}
		public void setBasicAppraiseFlag(String basicAppraiseFlag) {
			BasicAppraiseFlag = basicAppraiseFlag;
		}
		public String getMediumAppraiseFlag() {
			return MediumAppraiseFlag;
		}
		public void setMediumAppraiseFlag(String mediumAppraiseFlag) {
			MediumAppraiseFlag = mediumAppraiseFlag;
		}
		public String getPublishCarFlag() {
			return PublishCarFlag;
		}
		public void setPublishCarFlag(String publishCarFlag) {
			PublishCarFlag = publishCarFlag;
		}
		public String getCreateTime() {
			return CreateTime;
		}
		public void setCreateTime(String createTime) {
			CreateTime = createTime;
		}
		public String getLastModifyTime() {
			return LastModifyTime;
		}
		public void setLastModifyTime(String lastModifyTime) {
			LastModifyTime = lastModifyTime;
		}
		public String getStatus() {
			return Status;
		}
		public void setStatus(String status) {
			Status = status;
		}
		public String getOtherAccountId() {
			return OtherAccountId;
		}
		public void setOtherAccountId(String otherAccountId) {
			OtherAccountId = otherAccountId;
		}
		public String getOtherSourceType() {
			return OtherSourceType;
		}
		public void setOtherSourceType(String otherSourceType) {
			OtherSourceType = otherSourceType;
		}
		public String getRegisteredSource() {
			return RegisteredSource;
		}
		public void setRegisteredSource(String registeredSource) {
			RegisteredSource = registeredSource;
		}
		public String getUserNumber() {
			return UserNumber;
		}
		public void setUserNumber(String userNumber) {
			UserNumber = userNumber;
		}
	}
}
