/**
 * Project Name:JZGPingGuShi
 * File Name:SignUtils.java
 * Package Name:com.gc.jzgpinggushi.uitls
 * Date:2014-9-1上午10:40:27
 * Copyright (c) 2014, wangyd523@gmail.com All Rights Reserved.
 *
 */

package com.jzg.jzgoto.phone.tools;

import com.lidroid.xutils.util.LogUtils;

/**
 * ClassName:SignUtils <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:40:27 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class SignUtils
{
	private static final String privateNum = "2CB3147B-D93C-964B-47AE-EEE448C84E3C";

	public static String signForHotCarList()
	{
		return signForPost();
	}

	public static String signForPost()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?");
		buffer.append(privateNum);
		System.out.println("signForMakeList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForModelList(String makeid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?MakeId=");
		buffer.append(makeid);
		buffer.append(privateNum);
		System.out.println("signForModelList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForStylelList(String modelid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?ModelId=");
		buffer.append(modelid);
		buffer.append(privateNum);
		System.out
				.println("signForStylelList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForQueryCarList(int styleId, int pageCount,
			String orderItem, String orderType, int pgsid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?StyleId=");
		buffer.append(styleId);
		buffer.append("&PageCount=");
		buffer.append(pageCount);
		buffer.append("&OrderItem=");
		buffer.append(orderItem);
		buffer.append("&OrderType=");
		buffer.append(orderType);
		buffer.append("&pgsid=");
		buffer.append(pgsid);
		buffer.append(privateNum);

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForLogin(String username, String password)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?username=");
		buffer.append(username);
		buffer.append("&password=");
		buffer.append(password);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForChangePwd(String username, String password,
			String oldPassword)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?username=");
		buffer.append(username);
		buffer.append("&password=");
		buffer.append(password);
		buffer.append("&oldPassword=");
		buffer.append(oldPassword);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForOfferList(int pgsid, int pageCount)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?pgsid=");
		buffer.append(pgsid);
		buffer.append("&PageCount=");
		buffer.append(pageCount);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForOfferSuccessList(int pgsid, int pageCount)
	{
		return signForOfferList(pgsid, pageCount);
	}

	public static String signForNewCarList(int pageCount, int pgsid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?PageCount=");
		buffer.append(pageCount);
		buffer.append("&pgsid=");
		buffer.append(pgsid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForQueryByCarsourceid(int carsourceid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?carsourceid=");
		buffer.append(carsourceid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForUploadPrice(int pgsid, String myBidPrice,
			int carsourceid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?pgsid=");
		buffer.append(pgsid);
		buffer.append("&myBidPrice=");
		buffer.append(myBidPrice);
		buffer.append("&carsourceid=");
		buffer.append(carsourceid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForCityList(String procinceid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?ProvId=");
		buffer.append(procinceid);
		buffer.append(privateNum);
		System.out.println("signForCityList  String is :" + buffer.toString());

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signRegisterNum(String username, String password)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?mobile=");
		buffer.append(username);
		buffer.append("&password=");
		buffer.append(password);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signRegisterUser(String username, String password)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?username=");
		buffer.append(username);
		buffer.append("&password=");
		buffer.append(password);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	

	public static String signCardetail(int mCurStyleid)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?sid=");
		buffer.append(mCurStyleid);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signRegUserInfo(String username, String name,
			String sex, String alias, String corporation, String provinceId,
			String cityId)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?username=");
		buffer.append(username);

		buffer.append("&name=");
		buffer.append(name);

		buffer.append("&sex=");
		buffer.append(sex);

		buffer.append("&alias=");
		buffer.append(alias);

		buffer.append("&corporation=");
		buffer.append(corporation);

		buffer.append("&provinceId=");
		buffer.append(provinceId);

		buffer.append("&cityId=");
		buffer.append(cityId);

		buffer.append(privateNum);

		return MD5Utils.MD5(buffer.toString());
	}

	public static String signAddTopicsIssue(int moduleId, String username)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?topicsId=");
		buffer.append(moduleId);

		buffer.append("&username=");
		buffer.append(username);

		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForReplyTopic(String floor, String parentId,
			String content, String username, String TopicsIssueId)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?floor=");
		buffer.append(floor);

		buffer.append("&parentId=");
		buffer.append(parentId);

		buffer.append("&content=");
		buffer.append(content);

		buffer.append("&username=");
		buffer.append(username);

		buffer.append("&TopicsIssueId=");
		buffer.append(TopicsIssueId);

		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}

	public static String signForSendCarAndroid(String username, String styleId,
			String regDate, String cityId, String carColorId,
			String sellPriceb2b, String sellPriceb2c, String name,
			String mileage, String phoneNum, String description, String picIds)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?username=");
		buffer.append(username);

		buffer.append("&styleId=");
		buffer.append(styleId);

		buffer.append("&regDate=");
		buffer.append(regDate);

		buffer.append("&cityId=");
		buffer.append(cityId);

		buffer.append("&bodyColor=");
		buffer.append(carColorId);

		buffer.append("&sellPriceb2b=");
		buffer.append(sellPriceb2b);

		buffer.append("&sellPriceb2c=");
		buffer.append(sellPriceb2c);

		buffer.append("&name=");
		buffer.append(name);

		buffer.append("&mileage=");
		buffer.append(mileage);

		buffer.append("&mobile=");
		buffer.append(phoneNum);

		buffer.append("&description=");
		buffer.append(description);

		buffer.append("&picIds=");
		buffer.append(picIds);

		buffer.append(privateNum);

		LogUtils.i("realease car sign is " + buffer.toString());
		return MD5Utils.MD5(buffer.toString());

	}
	/**
	 * 获取资讯列表
	 * @param mType
	 * @param createTime
	 * @return
	 */
	public static String signForInfoList( String mType ,String createTime)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?operation=");
		buffer.append(mType);
		buffer.append("&createTime=");
		buffer.append(createTime);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}
	
	/**
	 * 获取资讯评论列表
	 */
	public static String signForInfoCommentList( String infoId ,String mType ,String createTime)
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("?infoId=");
		buffer.append(infoId);
		buffer.append("?operation=");
		buffer.append(mType);
		buffer.append("&createTime=");
		buffer.append(createTime);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}
	/**
	 * 资讯评论提交
	 * @param infoId
	 * @param content
	 * @param userName
	 * @return
	 */
	public static String signForReportComments(String infoId,String refId,String content,
			String userName){
		StringBuffer buffer = new StringBuffer();
		buffer.append("?infoId=");
		buffer.append(infoId);
		buffer.append("&content=");
		buffer.append(content);
		buffer.append("&username=");
		buffer.append(userName);
		buffer.append("&refId=");
		buffer.append(refId);
		buffer.append(privateNum);
		return MD5Utils.MD5(buffer.toString());
	}
	
}
