package com.dingdang.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dingdang.category.dao.CategoryDao;
import com.dingdang.category.model.Category;

/**
 * 品类
 * 
 * @author dangqi
 *
 */
@Component("categoryService")
public class CategoryService
{
	@Autowired
	private CategoryDao categoryDao;

	public CategoryDao getCategoryDao()
	{
		return categoryDao;
	}

	public void setCategoryDao(CategoryDao categoryDao)
	{
		this.categoryDao = categoryDao;
	}
	
	/**
	 * 获得品类信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List<Category> getCategory(int categoryId, int pageNo, int pageSize)
	{
		List<Category> list = categoryDao.getCategoryByPage(categoryId, pageNo, pageSize);
		
		return list;
	}

	
	/**
	 * 存储品类信息
	 * @param categoryName
	 * @param imgUrl
	 * @return
	 */
	public int saveCategory(String categoryName, String imgUrl)
	{
		if(imgUrl.contains("http://"))
		{
			imgUrl = imgUrl.replace("http://", "");
		}
		else if(imgUrl.contains("https://"))
		{
			imgUrl = imgUrl.replace("https://", "");
		}
		return categoryDao.saveCategory(categoryName, imgUrl);
	}
}
