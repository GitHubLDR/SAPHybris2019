package com.hello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hello.dao.StudentDao;
import com.hello.data.dto.StudentData;

public class StudentServiceImpl implements StudentService
{

	private StudentDao studentDao;
	
	
	
	@Override
	public boolean setStudentInfo(StudentData studentData) {
		return getStudentDao().setStudentInfo(studentData);
	}

	@Override
	public List<StudentData> getStundetDetails(String search){
		return (List<StudentData>) studentDao.getStudentDetails(search);
	}
	
	/**
	 * @return the studentDao
	 */
	public StudentDao getStudentDao() {
		return studentDao;
	}

	/**
	 * @param studentDao the studentDao to set
	 */
	@Required
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
}
