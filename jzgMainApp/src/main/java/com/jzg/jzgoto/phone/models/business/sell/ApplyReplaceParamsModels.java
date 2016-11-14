package com.jzg.jzgoto.phone.models.business.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 申请置换--最后一步
 * @Package com.jzg.jzgoto.phone.models.business.sell ApplyReplaceParamsModels.java
 * @author gf
 * @date 2015-12-25 下午4:05:13
 */
public class ApplyReplaceParamsModels extends BaseParamsModels {
	
	private final String url = BASE_URL + "OldChangeNew.ashx";
	
	private String UserId;
	
	private String mobile;
	private String name;
	private String myproid;
	private String myproname;
	private String myctid;
	private String myctname;
	private String oldmkid;
	
	private String oldmlid;
	private String oldstid;
	private String hdMilage;
	private String hdYear;
	private String hdMonth;
	private String smes;
	
	private String newproname;
	private String newctname;
	
	private String validCodes;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "TjSqSaveNew");
		
		params.put("UserId", UserId);
		params.put("mobile", mobile);
		params.put("name", name);
		params.put("myproid", myproid);
		params.put("myproname", myproname);
		
		params.put("myctid", myctid);
		params.put("myctname", myctname);
		params.put("oldmkid", oldmkid);
		params.put("oldmlid", oldmlid);
		
		params.put("oldstid", oldstid);
		params.put("hdMilage", hdMilage);
		params.put("hdYear", hdYear);
		params.put("hdMonth", hdMonth);
		params.put("smes", smes);
		params.put("newproname", newproname);
		params.put("newctname", newctname);
		params.put("ValidCodes", validCodes);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "TjSqSaveNew");
		
		md.put("UserId", UserId);
		md.put("mobile", mobile);
		md.put("name", name);
		md.put("myproid", myproid);
		md.put("myproname", myproname);
		
		md.put("myctid", myctid);
		md.put("myctname", myctname);
		md.put("oldmkid", oldmkid);
		md.put("oldmlid", oldmlid);
		
		md.put("oldstid", oldstid);
		md.put("hdMilage", hdMilage);
		md.put("hdYear", hdYear);
		md.put("hdMonth", hdMonth);
		md.put("smes", smes);
		md.put("newproname", newproname);
		md.put("newctname", newctname);
		md.put("ValidCodes", validCodes);
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
	
	public String getValidCodes() {
		return validCodes;
	}
	public void setValidCodes(String validCodes) {
		this.validCodes = validCodes;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getNewproname() {
		return newproname;
	}
	public void setNewproname(String newproname) {
		this.newproname = newproname;
	}
	public String getNewctname() {
		return newctname;
	}
	public void setNewctname(String newctname) {
		this.newctname = newctname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMyproid() {
		return myproid;
	}
	public void setMyproid(String myproid) {
		this.myproid = myproid;
	}
	public String getMyproname() {
		return myproname;
	}
	public void setMyproname(String myproname) {
		this.myproname = myproname;
	}
	public String getMyctid() {
		return myctid;
	}
	public void setMyctid(String myctid) {
		this.myctid = myctid;
	}
	public String getMyctname() {
		return myctname;
	}
	public void setMyctname(String myctname) {
		this.myctname = myctname;
	}
	public String getOldmkid() {
		return oldmkid;
	}
	public void setOldmkid(String oldmkid) {
		this.oldmkid = oldmkid;
	}
	public String getOldmlid() {
		return oldmlid;
	}
	public void setOldmlid(String oldmlid) {
		this.oldmlid = oldmlid;
	}
	public String getOldstid() {
		return oldstid;
	}
	public void setOldstid(String oldstid) {
		this.oldstid = oldstid;
	}
	public String getHdMilage() {
		return hdMilage;
	}
	public void setHdMilage(String hdMilage) {
		this.hdMilage = hdMilage;
	}
	public String getHdYear() {
		return hdYear;
	}
	public void setHdYear(String hdYear) {
		this.hdYear = hdYear;
	}
	public String getHdMonth() {
		return hdMonth;
	}
	public void setHdMonth(String hdMonth) {
		this.hdMonth = hdMonth;
	}
	public String getSmes() {
		return smes;
	}
	public void setSmes(String smes) {
		this.smes = smes;
	}
}
