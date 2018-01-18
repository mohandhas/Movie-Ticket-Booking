package com.mtr.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mtr.pojo.Theatre;

public class TheatreMapper implements org.springframework.jdbc.core.RowMapper<Theatre> {

	public Theatre mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		System.out.println(rs);
		Theatre theatre= new Theatre();
		theatre.setId(rs.getInt("THEATRE_ID"));
		theatre.setName(rs.getString("THEATRE_NAME"));
		theatre.setLatitude(rs.getDouble("LATITUDE"));
		theatre.setLongitude(rs.getDouble("LONGITUDE"));
		theatre.setNumberOfScreen(rs.getInt("NO_OF_SCREENS"));
		return theatre;
	}

}
