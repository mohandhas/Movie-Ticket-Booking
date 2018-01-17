package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.User;


public class UserMapper implements org.springframework.jdbc.core.RowMapper<User> {

	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user= new User();
		
		user.setEmail(rs.getString("USER_ID"));
		user.setName(rs.getString("USER_NAME"));
		user.setEmail(rs.getString("EMAIL"));
		user.setPassword(rs.getString("PASSWORD"));
		user.setPhone(rs.getString("PHONE"));
		user.setFavoriteGenere(rs.getString("GENRE"));
		return user;
	}

}
