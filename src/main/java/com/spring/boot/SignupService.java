package com.spring.boot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

//new SignupService()
@Service
public class SignupService {
	
	//From where it is coming ?????
	//Step-1 pom.xml  - >>	<artifactId>spring-boot-starter-jdbc</artifactId>
	//step-2 application.properties -
	//spring.datasource.url=jdbc:mysql://localhost:3306/batch100_db
	@Autowired
	private DataSource dataSource;
	
	public List<SignupDTO> findAll() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String fecthData = "select username, password , email,gender from signups_tbl";
		List<SignupDTO> signupDTOs=jdbcTemplate.query(fecthData, new BeanPropertyRowMapper(SignupDTO.class));
		return signupDTOs;
	}

	public void save(SignupDTO signupDTO) {
		// jdbcTemplate says I will make your jdbc programming super duper easy
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "insert into signups_tbl(username,password, email,gender) values(?,?,?,?)";
		Object[] data = { signupDTO.getUsername(), signupDTO.getPassword(), signupDTO.getEmail(),
				signupDTO.getGender() };
		jdbcTemplate.update(sql, data);
	}

}
