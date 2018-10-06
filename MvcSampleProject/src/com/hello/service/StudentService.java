package com.hello.service;

import java.util.List;

import com.hello.data.dto.StudentData;

public interface StudentService 
{
	public boolean setStudentInfo(StudentData studentData);
	public List<StudentData> getStundetDetails(String search);
}
