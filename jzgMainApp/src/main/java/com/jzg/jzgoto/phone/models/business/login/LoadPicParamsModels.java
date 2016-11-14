package com.jzg.jzgoto.phone.models.business.login;

import java.util.HashMap;
import java.util.Map;

import com.jzg.jzgoto.phone.models.common.BaseParamsModels;
import com.jzg.jzgoto.phone.tools.MD5Utils;
/**
 * @Description: 上传图片
 * @Package com.jzg.jzgoto.phone.models.business.login LoadPicParamsModels.java
 * @author gf
 * @date 2015-12-23 下午7:20:17
 */
public class LoadPicParamsModels extends BaseParamsModels {
	
//	http://10.58.0.64:8003/APP/CarsourcePicture.ashx 
	
	private final String url = BASE_HTTP + "/APP/CarsourcePicture.ashx";
//	private final String url = "http://10.58.0.64:8003/APP/CarsourcePicture.ashx";
//	private final String url = BASE_URL + "CarsourcePicture.ashx";
	
	private String CarId;
	private String filePath;
	private String fileName;
	private String Position;
	private String file;
	
	@Override
	public Map<String, String> getParams() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("op", "Addpicture");
		params.put("CarId", CarId);
		params.put("filePath", filePath);
		params.put("fileName", fileName);
		params.put("Position", Position);
		params.put("file", file);
		Map<String, Object> md = new HashMap<String,Object>();
		md.put("op", "Addpicture");
		md.put("CarId", CarId);
		md.put("filePath", filePath);
		md.put("fileName", fileName);
		md.put("Position", Position);
		md.put("file", file);
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
	public String getCarId() {
		return CarId;
	}
	public void setCarId(String carId) {
		CarId = carId;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPosition() {
		return Position;
	}
	public void setPosition(String position) {
		Position = position;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
}
