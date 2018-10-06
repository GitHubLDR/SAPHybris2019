package com.hello.dao;

import java.sql.ResultSet;

import com.hello.data.dto.StudentData;

public interface StudentDao {

	public boolean setStudentInfo(StudentData studentData);
	public ResultSet getStudentDetails(String search);
}
