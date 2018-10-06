package com.hello.service;

import java.sql.ResultSet;
import java.util.ArrayList;
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
		ResultSet rs = getStudentDao().getStudentDetails(search);
		try {
			if (rs != null) {
				List<StudentData> listOfStudents = new ArrayList<StudentData>();
				while (rs.next()) {
					StudentData data = new StudentData();
					data.setFirstName(rs.getString("firstName"));
					data.setLastName(rs.getString("lastName"));
					data.setEmail(rs.getString("email"));
					System.out.println(rs.getString("firstName") +" ---->" + rs.getString("lastName") + "-------> "+ rs.getString("email"));
					listOfStudents.add(data);
				}
				return listOfStudents.isEmpty()? null : listOfStudents;
			}
		}catch (Exception e) {
			System.out.println("Error at iteration student data");
			e.printStackTrace();
		}
		return null;
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
