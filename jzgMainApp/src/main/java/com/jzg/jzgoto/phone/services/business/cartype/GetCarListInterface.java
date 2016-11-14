package com.jzg.jzgoto.phone.services.business.cartype;

import com.jzg.jzgoto.phone.models.business.car.CarMakeResultModels;
import com.jzg.jzgoto.phone.models.business.car.CarModelResultModels;
import com.jzg.jzgoto.phone.models.business.car.CarStyleResultModels;

/**
 * @Description: 获取车源列表
 * @Package com.jzg.jzgoto.phone.services.business.cartype GetCarListInterface.java
 * 1 新车，0旧车
 * @author gf
 * @date 2016-6-16 下午5:44:32
 */
public interface GetCarListInterface {
	/**
	 * 插入品牌 
	 * @param models
	 * @return
	 */
	public boolean insertCarBrand(CarMakeResultModels models);
	/**
	 * 增加品牌
	 * @param models
	 * @return
	 */
	public boolean addCarBrand(CarMakeResultModels models);
	/**
	 * 更新品牌
	 * @param models
	 * @return
	 */
	public boolean updateCarBrand(CarMakeResultModels models);
	/**
	 * 删除品牌
	 * @param makeId
	 * @return
	 */
	public boolean delCarBrand(String makeId);
	/**
	 * 查询品牌列表
	 * 1 新车，0旧车
	 * @return
	 */
	public CarMakeResultModels queryCarBrand(final String isNewCar);
	/**
	 * 获取品牌更新时间
	 * @return
	 */
	public String getCarBrandLastUpData();
	/**
	 * 插入车系 
	 * @param models
	 * @return
	 */
	public boolean insertCarModel(CarModelResultModels models);
	/**
	 * 增加车系
	 * @param models
	 * @return
	 */
	public boolean addCarModel(CarModelResultModels models);
	/**
	 * 更新车系
	 * @param models
	 * @return
	 */
	public boolean updateCarModel(CarModelResultModels models);
	/**
	 * 删除车系
	 * @param modelId
	 * @return
	 */
	public boolean delCarModel(String modelId);
	
	/**
	 * 查询车系列表
	 * 1 新车，0旧车
	 * @param makeId
	 * @param isNewCar
	 * @return
	 */
	public CarModelResultModels queryCarModel(final String makeId,final String isNewCar);
	/**
	 * 获取车系最后更新时间
	 * @return
	 */
	public String getCarModelLastUpData();
	/**
	 * 插入车型 
	 * @param models
	 * @return
	 */
	public boolean insertCarStyle(CarStyleResultModels models);
	/**
	 * 增加车型
	 * @param models
	 * @return
	 */
	public boolean addCarStyle(CarStyleResultModels models);
	/**
	 * 更新车型
	 * @param models
	 * @return
	 */
	public boolean updateCarStyle(CarStyleResultModels models);
	/**
	 * 删除车型
	 * @param styleId
	 * @return
	 */
	public boolean delCarStyle(String styleId);
	/**
	 * 查询车型列表
	 * 1 新车，0旧车
	 * @param modelId
	 * @param isNewCar
	 * @return
	 */
	public CarStyleResultModels queryCarStyle(final String modelId,final String isNewCar);
	/**
	 * 获取车型最后更新时间
	 * @return
	 */
	public String getCarStyleLastUpData();
	/**
	 * 插入估值品牌
	 * @param models
	 * @return
	 */
	public boolean insertCarBrandValua(CarMakeResultModels models);
	/**
	 * 增加估值品牌
	 * @param models
	 * @return
	 */
	public boolean addCarBrandValua(CarMakeResultModels models);
	/**
	 * 更新估值品牌
	 * @param models
	 * @return
	 */
	public boolean updateCarBrandValua(CarMakeResultModels models);
	/**
	 * 删除估值品牌
	 * @param makeId
	 * @return
	 */
	public boolean delCarBrandValua(String makeId);
	/**
	 * 查询估值品牌列表
	 * @return
	 */
	public CarMakeResultModels queryCarBrandValua();
	/**
	 * 获取估值品牌最后更新时间
	 * @return
	 */
	public String getCarBrandValuaLastUpData();
	/**
	 * 插入估值车系
	 * @param models
	 * @return
	 */
	public boolean insertCarModelValua(CarModelResultModels models);
	/**
	 * 增加估值车系
	 * @param models
	 * @return
	 */
	public boolean addCarModelValua(CarModelResultModels models);
	/**
	 * 更新估值车系
	 * @param models
	 * @return
	 */
	public boolean updateCarModelValua(CarModelResultModels models);
	/**
	 * 删除车系
	 * @param modelId
	 * @return
	 */
	public boolean delCarModelValua(String modelId);
	/**
	 * 查询估值车系列表
	 * @return
	 */
	public CarModelResultModels queryCarModelValua(final String makeId);
	/**
	 * 获取估值车系最后更新时间
	 * @return
	 */
	public String getCarModelValuaLastUpData();
	/**
	 * 插入估值车型
	 * @param models
	 * @return
	 */
	public boolean insertCarStyleValua(CarStyleResultModels models);
	/**
	 * 增加估值车型
	 * @param models
	 * @return
	 */
	public boolean addCarStyleValua(CarStyleResultModels models);
	/**
	 * 更新估值车型
	 * @param models
	 * @return
	 */
	public boolean updateCarStyleValua(CarStyleResultModels models);
	/**
	 * 删除估值车型
	 * @param styleId
	 * @return
	 */
	public boolean delCarStyleValua(String styleId);
	/**
	 * 查询估值车型列表
	 * @return
	 */
	public CarStyleResultModels queryCarStyleValua(final String modelId);
	/**
	 * 过去估值车型最后更新时间
	 * @return
	 */
	public String getCarStyleValuaLastUpData();
}
