package com.hello.dao;

import java.util.ArrayList;
import java.util.List;

import com.hello.data.dto.StudentData;

public class StudentDaoImpl implements StudentDao
{
	public static List<StudentData> listOfStudents = new ArrayList<>();
	
	@Override
	public boolean setStudentInfo(StudentData studentData) {
		try{
		System.out.println("**************Data inserted successfully*****************");
		System.out.println("First Name : " + studentData.getFirstName() +"\n Last Name : " + studentData.getLastName() +"\n Email : "+ studentData.getEmail());
		
		//Adding data in list object and reusing at select (get student object)
		listOfStudents.add(studentData);
		System.out.println("*******************************");
		return true;
		}catch (Exception e) {
			System.out.println("************** Error occured while data insert *****************");
			return false;
		}
	}

	@Override
	public Object getStudentDetails(String search) {
		List<StudentData> searchMatchSudents = new ArrayList<>();
		for( StudentData student : listOfStudents) 
		{
			if(student.getFirstName().contains(search) || student.getLastName().contains(search) || student.getEmail().contains(search)){
				searchMatchSudents.add(student);
			}
		}
		return searchMatchSudents;
	}

}
