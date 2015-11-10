package com.dingdang.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.dingdang.user.model.User;

@Component("userDao")
public class UserDao
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	/**
	 * @param tele
	 * @param password
	 * @return
	 */
	@Transactional
	public int saveUser(String tele, String password)
	{
		return jdbcTemplate.update(
				"insert into user(tele,password) values(?,?)",
				new Object[] { tele, password }, new int[] {
						java.sql.Types.VARCHAR, java.sql.Types.VARCHAR });
	}

	/**
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("all")
	public List<User> getUserByPage(int pageNo, int pageSize)
	{
		int start = (pageNo - 1) * pageSize;
		
		List<User> rows = jdbcTemplate.query("SELECT * FROM user order by tele limit ?, ?", new Object[] {start, pageSize}, 
				new RowMapper()
				{
					public Object mapRow(ResultSet rs, int row) throws SQLException
					{
						User p = new User();  
						p.setId(rs.getInt("id"));
						p.setUsername(rs.getString("tele"));
						p.setPassword(rs.getString("password"));
						 
						return p;
					} 
				});
		return rows;
	}

	/**
	 * 
	 * @param tele
	 * @return
	 */
	public int isExistUserByTele(String tele)
	{
		int count = (Integer) jdbcTemplate
				.queryForObject(
						"SELECT count(*) FROM user WHERE tele = ?",
						new Object[] { tele }, Integer.class);

		return count;
	}

	/**
	 * @param username
	 * @param password
	 * @return
	 */
	public int checkUser(String tele, String password)
	{
		int count = (Integer) jdbcTemplate
				.queryForObject(
						"SELECT count(*) FROM user WHERE tele = ? and password = ?",
						new Object[] { tele, password }, Integer.class);

		return count;
	}
	
	
	/**
	 * 修改密码
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public int modifyUser(String tele, String password)
	{
		return jdbcTemplate.update(
				"update user set password = ? where tele = ?",
				new Object[] { password, tele }, new int[] {
						java.sql.Types.VARCHAR, java.sql.Types.VARCHAR });
	}
}
