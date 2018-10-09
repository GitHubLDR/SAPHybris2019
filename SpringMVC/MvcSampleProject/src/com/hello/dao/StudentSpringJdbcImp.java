package com.hello.dao;

import java.sql.ResultSet;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.hello.data.dto.StudentData;

public class StudentSpringJdbcImp implements StudentDao{

	@Resource
	private JdbcTemplate jdbcTemplate; 
	
	
	public boolean setStudentInfo(StudentData studentData) {
		String query = "INSERT INTO STUDENT (firstName, lastName, email) VALUES (?, ?, ?)";
		Object[] inputs = new Object[] {studentData.getFirstName(), studentData.getLastName(), studentData.getEmail()};
		int result = jdbcTemplate.update(query, inputs);  
		System.out.println(result);
		return result >= 1;
	}

	public Object getStudentDetails(String search) {
		
		String selectTableSQL = "SELECT firstName, lastName, email from STUDENT WHERE firstName Like '%"+ search +"%' OR lastName Like '%"+ search +"%' OR email Like '%"+ search +"%'";
		//String selectTableSQL = "SELECT firstName, lastName, email from STUDENT WHERE firstName Like %? OR lastName Like %? OR email Like %?";

		//Object[] inputs = new Object[] {search, search, search};
		return jdbcTemplate.queryForList(selectTableSQL);
	}

}
