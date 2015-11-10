package com.dingdang.category.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dingdang.category.model.Category;

@Component("categoryDao")
public class CategoryDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	/**
	 * 获得品类的信息
	 * 
	 * @param categoryId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("all")
	public List<Category> getCategoryByPage(int categoryId, int pageNo, int pageSize)
	{
		int start = (pageNo - 1) * pageSize;

		List<Category> rows = jdbcTemplate.query(
				"SELECT * FROM category where categoryId = ? order by id limit ?, ?",
				new Object[] { categoryId, start, pageSize }, new RowMapper()
				{
					public Object mapRow(ResultSet rs, int row)
							throws SQLException
					{
						Category category = new Category();
						category.setCategoryId(rs.getInt("categoryId"));
						category.setCategoryName(rs.getString("categoryName"));
						category.setImgUrl(rs.getString("imgUrl"));

						return category;
					}
				});
		return rows;
	}
	
	
	/**
	 * 存储品类信息
	 * @param categoryName
	 * @param imgUrl
	 * @return
	 */
	@Transactional
	public int saveCategory(String categoryName, String imgUrl)
	{
		
		return jdbcTemplate.update(
				"insert into category(categoryName,imgUrl) values(?,?)",
				new Object[] { categoryName, imgUrl },
					new int[] {
						java.sql.Types.VARCHAR, java.sql.Types.VARCHAR, 
					});
	}
}
