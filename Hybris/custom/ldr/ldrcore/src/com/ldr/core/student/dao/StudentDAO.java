/**
 *
 */
package com.ldr.core.student.dao;

import de.hybris.platform.core.model.enumeration.EnumerationValueModel;

import java.util.List;

import com.ldr.core.model.StudentModel;


/**
 * @author devreddy
 *
 */
public interface StudentDAO
{
	public List<StudentModel> getAllStudentDetails();

	public StudentModel getStudentById(String studentId);

	public StudentModel getStudentByIdGenric(String studentId);

	public void doStudentRegistration(StudentModel studentModel);

	public List<EnumerationValueModel> getStudentType();

}
