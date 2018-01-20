package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.Addons;

public class AddonsMapper implements org.springframework.jdbc.core.RowMapper<Addons> {

	public Addons mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Addons addon= new Addons();
		
		addon.setItemName(rs.getString("ITEM_NAME"));
		addon.setCost(rs.getDouble("COST"));
		return addon;
	}

}
