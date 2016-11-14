package com.jzg.jzgoto.phone.models.business.valuation.sell;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.global.HttpServiceHelper;
import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * 请求历史成交记录参数
 * @author wangying
 * @date 2016年6月14日
 * @className
 */
public class RequestHistoryDealParams extends BaseParamsModels implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//	http://192.168.6.48:9001/APPV3/AppraiseReport.ashx?
//		op=HistoryData&styleId=10000&pageindex=1&cityId=201&type=2
	private String mUrl = HttpServiceHelper.BASE_URL+"/APPV3/AppraiseReport.ashx";
	public String styleId;
	public String pageindex;
	public String pagesize;
	public String cityId;
	/**
	 * 1-买车
	 * 2-卖车
	 */
	public String type;		
	@Override
	public Map<String, String> getParams() {
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("op", "HistoryData");
		map.put("styleId", styleId);
		map.put("pageindex", pageindex);
		map.put("pagesize", "5");
		map.put("cityId", cityId);
		map.put("type", type);
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("op", "HistoryData");
		params.put("styleId", styleId);
		params.put("pageindex", pageindex );
		params.put("pagesize", "5" );
		params.put("cityId", cityId);
		params.put("type", type);
		
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
