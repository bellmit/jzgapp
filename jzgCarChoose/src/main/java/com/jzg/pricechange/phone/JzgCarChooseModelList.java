package com.jzg.pricechange.phone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * ClassName:ModelList <br/>
 * Function: 车系实体对象列表. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-13 上午11:33:32 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseModelList extends JzgCarChooseBaseObject implements Serializable
{
	private ArrayList<JzgCarChooseModelCategory> modeCategroy;
	private List<ModelItemMsg> mModelsList;
	private boolean success;
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public List<ModelItemMsg> getmModelsList() {
		return mModelsList;
	}

	public void setmModelsList(List<ModelItemMsg> mModelsList) {
		this.mModelsList = mModelsList;
	}

	public ArrayList<JzgCarChooseModelCategory> getModels()
	{
		return modeCategroy;
	}

	public void setModels(ArrayList<JzgCarChooseModelCategory> modeCategroy)
	{
		this.modeCategroy = modeCategroy;
	}
	
	public class ModelItemMsg implements Serializable{
		/**
		 * UID
		 */
		private static final long serialVersionUID = 1L;
		private int Id;
		private String Name;
		private String ManufacturerName;
		private String modelimgpath;
		/**
		 * 字体颜色
		 */
		private int fontColor;

		/**
		 * item点击颜色
		 */
		private int itemColor;
		
		public int getFontColor() {
			return fontColor;
		}
		public void setFontColor(int fontColor) {
			this.fontColor = fontColor;
		}
		public int getItemColor() {
			return itemColor;
		}
		public void setItemColor(int itemColor) {
			this.itemColor = itemColor;
		}
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public String getManufacturerName() {
			return ManufacturerName;
		}
		public void setManufacturerName(String manufacturerName) {
			ManufacturerName = manufacturerName;
		}
		public String getModelimgpath() {
			return modelimgpath;
		}
		public void setModelimgpath(String modelimgpath) {
			this.modelimgpath = modelimgpath;
		}
	}
}
