package com.jzg.pricechange.phone;

import java.io.Serializable;

/**
 * ClassName:BaseObject <br/>
 * Function: 服务器返回对象基类. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2014-6-12 下午12:22:05 <br/>
 * 
 * @author 汪渝栋
 * @version
 * @since JDK 1.6
 * @see
 */
public class JzgCarChooseBaseObject implements Serializable
{
	private int status;

	private String msg;
	
	private int flag;
	
	private int carsourceid;

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public int getCarsourceid()
	{
		return carsourceid;
	}

	public void setCarsourceid(int carsourceid)
	{
		this.carsourceid = carsourceid;
	}

	@Override
	public String toString() {
		return "BaseObject [status=" + status + ", msg=" + msg + ", flag="
				+ flag + ", carsourceid=" + carsourceid + "]";
	}

}
