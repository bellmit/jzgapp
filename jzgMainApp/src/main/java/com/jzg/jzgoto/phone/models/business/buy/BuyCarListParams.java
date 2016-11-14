package com.jzg.jzgoto.phone.models.business.buy;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;

public class BuyCarListParams extends BaseParamsModels implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String mUrl = HttpServiceHelper.BASE_URL+"/appv5/CarSource.ashx";

	//标记Tag使用，不作为请求参数
//	CarType 类型（0全部 1二手车 2新车）
//	CarSourceFrom 来自平台（1.精真估商家版  2.精真估  3.淘车  4.大搜车  7.看车网）
//	Seats 座位数
//	IsLoan 是否贷款 1有贷款 0无

	public String CarType = "0";//类型（0全部 1二手车 2新车）
	public String CarSourceFrom = "0";//来自平台（1.精真估商家版  2.精真估  3.淘车  4.大搜车  7.看车网）
	public String Seats = "0";//不限.0	7座以上.999
	public String IsLoan = "0";	//是否贷款 1有贷款 0无

	public int PageIndex = 1;
	public int PageSize = 10;

	public String CSUserType = "0";//车源类型 0 全部  1 个人  2商家
	public String ModelLevel = "0";//(SUV/MVP......)
	public String MakeID = "0";
	public String ModelID = "0";
	public String StyleID = "0";
	public String ProvID = "0";
	public String CityID = "0";//从列表中选择时传CityId
	public String CityName = "";//定位时，只穿CityName
	public String BeginSellPrice = "";//起始售价 单位元
	public String EndSellPrice = "";//结束售价 单位：元
	public String BeginCarAge = "";//起始车龄
	public String EndCarAge = "";//结束车龄
	public String BeginMileage = "";//起始行驶里程 单位：公里
	public String EndMileage = "";//结束行驶里程 单位：公里
	public String BeginCost = "";//月均使用成本 单位：元
	public String BsqSimpleValue = "0";//变速箱 0：全部 2：手动 3：自动
	public String EndCost = "";//月均使用成本 单位：元
	public String Sore = "2";//排序 1:发布时间升序 2:发布时间降序(默认) 3:行驶里程升序 4:行驶里程降序 5:售价升序 6:售价降序 7:车龄升序 8:车龄降序
	public String BeginPL ="";
	public String EndPL ="";
	public String keyword ="";
	public String Countries = "0";//国别 0全部，1中国，2美国，3日本，4韩国，5德国，6英国，7法国，999其他；
	//op=SearchCarSource

	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "SearchCarSource");
		map.put("PageIndex", String.valueOf(PageIndex));
		map.put("PageSize", String.valueOf(PageSize));

		map.put("CarType", CarType);
		map.put("CarSourceFrom", CarSourceFrom);
		map.put("Seats", Seats );
		map.put("IsLoan", IsLoan );

		map.put("CSUserType", CSUserType);
		map.put("ModelLevel", ModelLevel);
		map.put("MakeID", MakeID );
		map.put("ModelID", ModelID );
		map.put("StyleID", StyleID );
		map.put("CityID", CityID );
		map.put("ProvID", ProvID );
		map.put("CityName", CityName );
		map.put("BeginSellPrice", BeginSellPrice );
		map.put("EndSellPrice", EndSellPrice );
		map.put("BeginCarAge", BeginCarAge );
		map.put("EndCarAge", EndCarAge );
		map.put("BeginMileage", BeginMileage );
		map.put("EndMileage", EndMileage );
		map.put("BeginCost", BeginCost );
		map.put("EndCost", EndCost );
		map.put("BeginPL", BeginPL );
		map.put("EndPL", EndPL );
		map.put("keyword", keyword );
		map.put("BsqSimpleValue", BsqSimpleValue );
		map.put("Countries", Countries  );
		map.put("Sore", Sore );
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "SearchCarSource");
		params.put("PageIndex", String.valueOf(PageIndex));
		params.put("PageSize", String.valueOf(PageSize));

		params.put("CarType", CarType);
		params.put("CarSourceFrom", CarSourceFrom);
		params.put("Seats", Seats );
		params.put("IsLoan", IsLoan );

		params.put("CSUserType", CSUserType);
		params.put("ModelLevel", ModelLevel);
		params.put("MakeID", MakeID );
		params.put("ModelID", ModelID );
		params.put("StyleID", StyleID );
		params.put("CityID", CityID );
		params.put("ProvID", ProvID );
		params.put("CityName", CityName );
		params.put("BeginSellPrice", BeginSellPrice );
		params.put("EndSellPrice", EndSellPrice );
		params.put("BeginCarAge", BeginCarAge );
		params.put("EndCarAge", EndCarAge );
		params.put("BeginMileage", BeginMileage );
		params.put("EndMileage", EndMileage );
		params.put("BeginCost", BeginCost );
		params.put("EndCost", EndCost );
		params.put("BeginPL", BeginPL );
		params.put("EndPL", EndPL );
		params.put("keyword", keyword );
		params.put("BsqSimpleValue", BsqSimpleValue );
		params.put("Countries", Countries  );
		params.put("Sore", Sore );
		params.put("sign", MD5Utils.getMD5Sign(map) );
		return params;
	}

	@Override
	public String toParamsString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl() {
		// TODO Auto-generated method stub
		return mUrl;
	}

}
