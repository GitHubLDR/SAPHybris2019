/**
 *
 */
package com.ldr.facades.student;

import java.util.List;

import com.ldr.facades.student.data.StudentData;


/**
 * @author devreddy
 *
 */
public interface StudentFacade
{
	public List<StudentData> getAllStudentDetails();

	public StudentData getStudentById(String studentId);


	public void doStudentRegistration(StudentData studentData);

	

}
