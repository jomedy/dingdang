package com.dingdang.category.model;

public class Product
{
	private int pid;
	
	private String name;  //名称
	
	private String imgUrl; //图片
	
	private String price; //每份的单价
	
	private String spec; //规格 : 几斤装或几个装
	
	private String desc; //详情 : 链接网页的图片
	
	private int cid;  // 品类ID， foreign key :  categoryId
	
	private int lid; // 园区ID， foreign key : locationId

	public int getPid()
	{
		return pid;
	}

	public void setPid(int pid)
	{
		this.pid = pid;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getImgUrl()
	{
		return imgUrl;
	}

	public void setImgUrl(String imgUrl)
	{
		this.imgUrl = imgUrl;
	}

	public String getPrice()
	{
		return price;
	}

	public void setPrice(String price)
	{
		this.price = price;
	}

	public String getSpec()
	{
		return spec;
	}

	public void setSpec(String spec)
	{
		this.spec = spec;
	}

	public String getDesc()
	{
		return desc;
	}

	public void setDesc(String desc)
	{
		this.desc = desc;
	}

	public int getCid()
	{
		return cid;
	}

	public void setCid(int cid)
	{
		this.cid = cid;
	}

	public int getLid()
	{
		return lid;
	}

	public void setLid(int lid)
	{
		this.lid = lid;
	}
}
