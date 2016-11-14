/**
 * @Title: MD5Utils.java
 * @Package com.jzg.jzgoto.phone.tools
 * @Description: TODO
 * Copyright: Copyright (c) 2011 
 * Company:�����������Ϣ�������޹�˾
 * 
 * @author Comsys-wangyd
 * @date 2015-5-11 ����4:18:37
 * @version V1.0
 */

package com.jzg.pricechange.phone;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: MD5Utils
 * @Description: 精真估客户端请求加密工具类1、加密方式全部小写 2、参数顺序按字母大小排序a-z 3、所有请求都已post方式
 * @author Comsys-wangyd
 * @date 2015-5-11 下午4:18:37
 * 
 */

public class JzgCarChooseMD5Utils
{

	private static final String PRIVATE_KEY = "2CB3147B-D93C-964B-47AE-EEE448C84E3C".toLowerCase();

	/**
	 * 
	 * getMD5Sign
	 * 
	 * @Title: getMD5Sign
	 * @Description: 获取MD5加密后的sign字符串
	 * @param @param params 需要传递的参数Map集合，无参状态下可以为null
	 * @return String
	 * @throws
	 */
	public static String getMD5Sign(Map<String, Object> params)
	{
		if (params == null || params.size() <= 0)
			return MD5(PRIVATE_KEY);

		StringBuffer signValue = new StringBuffer();
		List<Map.Entry<String, Object>> infos = sortTreeMap(params);
		for (int i = 0; i < infos.size(); i++)
		{
			String value = infos.get(i).toString();
			signValue.append(value);
		}
		signValue.append(PRIVATE_KEY);
		// System.out.println("signValue.toString().toLowerCase() is "
		// + signValue.toString().toLowerCase());

		return MD5(signValue.toString().toLowerCase());
	}

	/**
	 * 
	 * sortTreeMap
	 * 
	 * @Title: sortTreeMap
	 * @Description: 对参数列表进行排序（按照key的字母大小）
	 * @param @param params
	 * @param @return
	 * @return Map<String,Object>
	 * @throws
	 */
	private static List<Map.Entry<String, Object>> sortTreeMap(
			Map<String, Object> params)
	{
		List<Map.Entry<String, Object>> entrys = new ArrayList<Map.Entry<String, Object>>(
				params.entrySet());

		// 排序
		Collections.sort(entrys, new Comparator<Map.Entry<String, Object>>()
		{
			public int compare(Map.Entry<String, Object> o1,
					Map.Entry<String, Object> o2)
			{
				return (o1.getKey()).toLowerCase().compareTo(
						o2.getKey().toLowerCase());
			}
		});

		// for (Map.Entry<String, Object> entry : infos)
		// {
		// System.out.println(entry.getKey() + ":" + entry.getValue());
		// }

		return entrys;
	}

	/**
	 * 
	 * MD5
	 * 
	 * @Title: MD5
	 * @Description: MD5加密方法
	 * @param @param s 需要加密的字符串
	 * @param @return
	 * @return String
	 * @throws
	 */
	private final static String MD5(String s)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try
		{
			byte[] strTemp = s.getBytes("UTF-8");
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++)
			{
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str).toUpperCase();
		} catch (Exception e)
		{
			return null;
		}
	}

}
