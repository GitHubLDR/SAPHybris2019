/**
 *
 */
package com.ldr.core.student.service;

import de.hybris.platform.core.model.enumeration.EnumerationValueModel;

import java.util.List;

import com.ldr.core.model.StudentModel;


/**
 * @author devreddy
 *
 */
public interface StudentService
{
	public List<StudentModel> getAllStudentDetails();

	public StudentModel getStudentById(String studentId);

	public void doStudentRegistration(StudentModel studentModel);

}
