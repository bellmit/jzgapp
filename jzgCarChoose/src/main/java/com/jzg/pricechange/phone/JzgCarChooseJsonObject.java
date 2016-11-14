package com.jzg.pricechange.phone;



import java.io.Serializable;

/**
 * ClassName:JsonObject <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-9-1 上午10:51:32 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public interface JzgCarChooseJsonObject {
	/**
	 * 解析热门推荐数据 parserHotCarList: <br/>
	 * 
	 * @author wang
	 * @param result
	 * @return
	 * @since JDK 1.6
	 
	public HotCarList parserHotCarList(String result);
	*/

	/**
	 * 解析品牌数据 parserJson: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	JzgCarChooseMakeList parserMakeList(String jsonData);

	/**
	 * 解析系列数据 parserModelList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 */
	JzgCarChooseModelList parserModelList(String jsonData);

	/**
	 * 解析车型数据 parserStyleList: <br/>
	 * 
	 * @author wang
	 * @param modelid
	 * @return
	 * @since JDK 1.6
	 */
	JzgCarChooseStyleList parserStyleList(String jsonData);

	/**
	 * 生成json数据 generateJson: <br/>
	 * 
	 * @author wang
	 * @param object
	 * @return
	 * @since JDK 1.6
	 */
	String generateJson(Serializable object);

	/**
	 * 解析省数据 parserProvinceList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	
	ProvinceList parserProvinceList(String jsonData);
 */
	/**
	 * 解析市数据 parserCityList: <br/>
	 * 
	 * @author wang
	 * @param jsonData
	 * @return
	 * @since JDK 1.6
	 
	CityList parserCityList(String jsonData);
*/
}
