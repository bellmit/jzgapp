package com.jzg.jzgoto.phone.models.business.sell;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 卖车请求参数
 * @Package com.jzg.jzgoto.phone.models.business.sell SellCarParamsModels.java
 * @author gf
 * @date 2015-12-23 下午5:21:49
 */
public class SellCarParamsModels extends BaseParamsModels {
	private final String url = BASE_URL + "SendCar.ashx";
	/**
	 * 用户ID
	 */
	public String uid;
	/**
	 * 车型ID
	 */
	public String styleid;
	/**
	 * 上牌时间
	 */
	public String regdate;
	/**
	 * 行驶公里
	 */
	public String mileage;
	/**
	 * 省ID
	 */
	public String proveid;
	/**
	 * 城市ID
	 */
	public String cityid;
	/**
	 * 来源   3：安卓， 4：IOS
	 */
	public String sourcetype;
	/**
	 * 车身颜色
	 */
	public String color;
	/**
	 * 销售价格
	 */
	public String sellprice;
	/**
	 * 过户次数
	 */
	public String transfernum;
	/**
	 * 保养情况
	 */
	public String maintenance;
	/**
	 * 使用性质
	 */
	public String nature;
	/**
	 * 车牌号
	 */
	public String licensenumber;
	/**
	 * 是否包含过户费
	 */
	public String transercost;
	/**
	 * 车辆描述
	 */
	public String description;
	/**
	 * 图片数量
	 */
	public String picturecount;
	/**
	 * 图片保存ID
	 */
	public String pictureid;
	/**
	 * 联系人电话
	 */
	public String mobile;
	/**
	 * 联系人
	 */
	public String personer;
	/**
	 * 看车地点
	 */
	public String lookaddress;
	/**
	 * 保险
	 */
	public String insuranceTime;
	/**
	 * 年检
	 */
	public String yearinspectiontime;
	/**
	 * 品牌电商
	 */
	public String Tripartite = "";
	
	public String provname;
	public String cityname;
	/**
	 * 验证码
	 */
	public String validCodes;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "SendCarSourceNew");
		
		params.put("uid", uid);
		params.put("styleid", styleid);
		params.put("regdate", regdate);
		params.put("mileage", mileage);
//		params.put("proveid", proveid);
		params.put("provname", provname);
		
//		params.put("cityid", cityid);
		params.put("cityname", cityname);
		params.put("sourcetype", sourcetype);
		params.put("color", color);
		params.put("sellprice", sellprice);
		
		params.put("transfernum", transfernum);
		params.put("maintenance", maintenance);
		params.put("nature", nature);
		params.put("licensenumber", licensenumber);
		
		params.put("transercost", transercost);
		params.put("description", description);
		params.put("picturecount", picturecount);
		params.put("pictureid", pictureid);
		
		params.put("mobile", mobile);
		params.put("personer", personer);
		params.put("insuranceTime", insuranceTime);
		params.put("yearinspectiontime", yearinspectiontime);
		params.put("Tripartite", Tripartite);
		params.put("lookaddress", lookaddress);
		params.put("ValidCodes", validCodes);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "SendCarSourceNew");
		md.put("uid", uid);
		md.put("styleid", styleid);
		md.put("regdate", regdate);
		md.put("mileage", mileage);
//		md.put("proveid", proveid);
		md.put("provname", provname);
		
//		params.put("cityid", cityid);
		md.put("cityname", cityname);
		md.put("sourcetype", sourcetype);
		md.put("color", color);
		md.put("sellprice", sellprice);
		
		md.put("transfernum", transfernum);
		md.put("maintenance", maintenance);
		md.put("nature", nature);
		md.put("licensenumber", licensenumber);
		
		md.put("transercost", transercost);
		md.put("description", description);
		md.put("picturecount", picturecount);
		md.put("pictureid", pictureid);
		
		md.put("mobile", mobile);
		md.put("personer", personer);
		md.put("insuranceTime", insuranceTime);
		md.put("yearinspectiontime", yearinspectiontime);
		md.put("Tripartite", Tripartite);
		md.put("lookaddress", lookaddress);
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

}
