package com.mtr.daoimpl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mtr.dao.UserDAO;
import com.mtr.mapper.MovieMapper;
import com.mtr.mapper.UserMapper;
import com.mtr.pojo.Movie;
import com.mtr.pojo.User;

public class UserDAOImpl implements UserDAO {

	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	public boolean register(User user) {
		
		if(user.getName()!=null && user.getName()!=null && user.getEmail()!=null && user.getPassword()!=null && user.getPhone()!=null)
		{
			String sql = "INSERT INTO USER (USER_NAME,EMAIL,PASSWORD,PHONE,GENRE) VALUES(?,?,?,?,?)";
			int count = jdbcTemplate.update(sql,user.getName(),user.getEmail(),user.getPassword(),user.getPhone(),user.getFavoriteGenere());
			if(count!=0)
			{
				return false;
			}
		}
		return true;
	}

	public User login(String email, String password) {
		User user=this.findUser(email);
		if(user==null)
		{
			return null;
		}
		if(email.equals(user.getEmail()) && password.equals(user.getPassword()))
		{
			return user;
		}
	return null;
	}

	private User findUser(String email) {
		String sql = "SELECT * FROM USER WHERE EMAIL=?";
		return jdbcTemplate.queryForObject(sql, new Object[] {email}, new UserMapper());
	}
	public List<Movie> getMoviesInTheatre(int id) {
		return null;
		
	}
}
