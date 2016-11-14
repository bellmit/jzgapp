package com.jzg.jzgoto.phone.models.business.sell;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 新车列表
 * @Package com.jzg.jzgoto.phone.models.business.sell NewCarListParamsModels.java
 * @author gf
 * @date 2015-12-24 下午4:49:58
 */
public class NewCarListParamsModels extends BaseParamsModels implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1L;
	//http://10.58.0.68/APPNew/OldChangeNew.ashx?
//	op=ChangeNewCarList&PageIndex=1&NewMakeID=9&NewMsrpRange=0-0&NewLevel=0&NewTypeID=0&NewProductType=0
	private final String url = BASE_URL + "OldChangeNew.ashx";
	/**
	 * 页码
	 */
	public String PageIndex;
	/**
	 * 品牌
	 */
	public String NewMakeID;
	/**
	 * 0-0 价格
	 */
	public String NewMsrpRange = "0-0";
	/**
	 * 车型
	 */
	public String NewLevel = "0";
	/**
	 * 变速箱
	 */
	public String NewTypeID = "0";
	/**
	 * 国别
	 */
	public String NewProductType = "0";
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "ChangeNewCarList");
		
		params.put("PageIndex", PageIndex);
		params.put("NewMakeID", NewMakeID);
		params.put("NewMsrpRange", NewMsrpRange);
		params.put("NewLevel", NewLevel);
		
		params.put("NewTypeID", NewTypeID);
		params.put("NewProductType", NewProductType);
		
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "ChangeNewCarList");
		
		md.put("PageIndex", PageIndex);
		md.put("NewMakeID", NewMakeID);
		md.put("NewMsrpRange", NewMsrpRange);
		md.put("NewLevel", NewLevel);
		
		md.put("NewTypeID", NewTypeID);
		md.put("NewProductType", NewProductType);
		
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

}
