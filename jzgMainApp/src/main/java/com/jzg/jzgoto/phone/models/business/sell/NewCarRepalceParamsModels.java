package com.jzg.jzgoto.phone.models.business.sell;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 置换申请--从新车列表过来
 * @Package com.jzg.jzgoto.phone.models.business.sell NewCarRepalceParamsModels.java
 * @author gf
 * @date 2015-12-24 下午4:27:40
 */
public class NewCarRepalceParamsModels extends BaseParamsModels implements Serializable{
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;

	//	http://10.58.0.68/APPNew/OldChangeNew.ashx?
//	op=SqZhList&MyMakeID=9&MyModelID=3999&MyStyleID=113850&
//	MyProvinceID=2&MyCItyID=201&MyMileage=1&MyFirstRegistrationTime=2015-1&NewStyleID=116070,113850
//	private final String url = "http://10.58.0.68/APPNew/OldChangeNew.ashx";
	private final String url = BASE_URL + "OldChangeNew.ashx";
	
	private String MyMakeID;
	private String MyModelID;
	private String MyStyleID;
	private String MyProvinceID;
	
	private String MyCItyID;
	private String MyMileage;
	private String MyFirstRegistrationTime;
	private String NewStyleID;
	
	private String MyCityName;
	private String MyProvinceName;
	
	private String NewCityId;
	private String NewCityName;
	private String NewProvinceId;
	private String NewProvinceName;
	
	private String RecordId = "";
	private String RecordType = "";
	
	private String level = "2";
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "SqZhList");
		
		params.put("MyMakeID", MyMakeID);
		params.put("MyModelID", MyModelID);
		params.put("MyStyleID", MyStyleID);
		params.put("MyProvinceName", MyProvinceName);
		
		params.put("MyCItyName", MyCityName);
		params.put("MyMileage", MyMileage);
		params.put("MyFirstRegistrationTime", MyFirstRegistrationTime);
		params.put("NewStyleID", NewStyleID);
		
		params.put("RecordId", RecordId);
		params.put("RecordType", RecordType);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "SqZhList");
		
		md.put("MyMakeID", MyMakeID);
		md.put("MyModelID", MyModelID);
		md.put("MyStyleID", MyStyleID);
		md.put("MyProvinceName", MyProvinceName);
		
		md.put("MyCItyName", MyCityName);
		md.put("MyMileage", MyMileage);
		md.put("MyFirstRegistrationTime", MyFirstRegistrationTime);
		md.put("NewStyleID", NewStyleID);
		
		md.put("RecordId", RecordId);
		md.put("RecordType", RecordType);
		params.put("sign", MD5Utils.getMD5Sign(md));
		return params;
	}

	@Override
	public String toParamsString() {
		return null;
	}

	@Override
	public String getUrl() {
		return url;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getRecordId() {
		return RecordId;
	}
	public void setRecordId(String recordId) {
		RecordId = recordId;
	}
	public String getRecordType() {
		return RecordType;
	}
	public void setRecordType(String recordType) {
		RecordType = recordType;
	}

	public String getNewProvinceName() {
		return NewProvinceName;
	}

	public void setNewProvinceName(String newProvinceName) {
		NewProvinceName = newProvinceName;
	}

	public String getNewCityName() {
		return NewCityName;
	}

	public void setNewCityName(String newCityName) {
		NewCityName = newCityName;
	}

	public String getMyCityName() {
		return MyCityName;
	}

	public void setMyCityName(String myCityName) {
		MyCityName = myCityName;
	}

	public String getMyProvinceName() {
		return MyProvinceName;
	}

	public void setMyProvinceName(String myProvinceName) {
		MyProvinceName = myProvinceName;
	}

	public String getNewCityId() {
		return NewCityId;
	}

	public void setNewCityId(String newCityId) {
		NewCityId = newCityId;
	}

	public String getNewProvinceId() {
		return NewProvinceId;
	}

	public void setNewProvinceId(String newProvinceId) {
		NewProvinceId = newProvinceId;
	}

	public String getMyMakeID() {
		return MyMakeID;
	}

	public void setMyMakeID(String myMakeID) {
		MyMakeID = myMakeID;
	}

	public String getMyModelID() {
		return MyModelID;
	}

	public void setMyModelID(String myModelID) {
		MyModelID = myModelID;
	}

	public String getMyStyleID() {
		return MyStyleID;
	}

	public void setMyStyleID(String myStyleID) {
		MyStyleID = myStyleID;
	}

	public String getMyProvinceID() {
		return MyProvinceID;
	}

	public void setMyProvinceID(String myProvinceID) {
		MyProvinceID = myProvinceID;
	}

	public String getMyCItyID() {
		return MyCItyID;
	}

	public void setMyCItyID(String myCItyID) {
		MyCItyID = myCItyID;
	}

	public String getMyMileage() {
		return MyMileage;
	}

	public void setMyMileage(String myMileage) {
		MyMileage = myMileage;
	}

	public String getMyFirstRegistrationTime() {
		return MyFirstRegistrationTime;
	}

	public void setMyFirstRegistrationTime(String myFirstRegistrationTime) {
		MyFirstRegistrationTime = myFirstRegistrationTime;
	}

	public String getNewStyleID() {
		return NewStyleID;
	}

	public void setNewStyleID(String newStyleID) {
		NewStyleID = newStyleID;
	}
}
