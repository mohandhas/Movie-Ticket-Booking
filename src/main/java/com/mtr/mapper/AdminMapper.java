package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.Admin;


public class AdminMapper implements org.springframework.jdbc.core.RowMapper<Admin> {

	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Admin admin= new Admin();
		
		admin.setId(rs.getString("ADMIN_ID"));
		admin.setPassword(rs.getString("PASSWORD"));
		return admin;
	}

}
