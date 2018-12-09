/**
 *
 */
package com.ldr.facades.student;

import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.ldr.core.model.StudentModel;
import com.ldr.core.student.service.StudentService;
import com.ldr.facades.student.data.StudentData;


/**
 * @author devreddy
 *
 */
public class DefaultStudentFacade implements StudentFacade
{

	private StudentService studentService;

	private Converter<StudentModel, StudentData> studentConverter;

	private Converter<StudentData, StudentModel> studentReverseConverter;

	/*
	 *
	 * @see com.ldr.facades.student.StudentFacade#getAllStudentDetails()
	 */
	@Override
	public List<StudentData> getAllStudentDetails()
	{
		return studentConverter.convertAll(studentService.getAllStudentDetails());
	}

	/*
	 *
	 * @see com.ldr.facades.student.StudentFacade#getStudentById(java.lang.String)
	 */
	@Override
	public StudentData getStudentById(final String studentId)
	{
		return studentConverter.convert(studentService.getStudentById(studentId));
	}

	/**
	 * @return the studentService
	 */
	public StudentService getStudentService()
	{
		return studentService;
	}

	/**
	 * @param studentService
	 *           the studentService to set
	 */
	public void setStudentService(final StudentService studentService)
	{
		this.studentService = studentService;
	}

	/**
	 * @return the studentConverter
	 */
	public Converter<StudentModel, StudentData> getStudentConverter()
	{
		return studentConverter;
	}

	/**
	 * @param studentConverter
	 *           the studentConverter to set
	 */
	public void setStudentConverter(final Converter<StudentModel, StudentData> studentConverter)
	{
		this.studentConverter = studentConverter;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.facades.student.StudentFacade#doStudentRegistration(com.ldr.facades.student.data.StudentData)
	 */
	@Override
	public void doStudentRegistration(final StudentData studentData)
	{
		studentService.doStudentRegistration(studentReverseConverter.convert(studentData));
	}

	/**
	 * @return the studentReverseConverter
	 */
	public Converter<StudentData, StudentModel> getStudentReverseConverter()
	{
		return studentReverseConverter;
	}

	/**
	 * @param studentReverseConverter
	 *           the studentReverseConverter to set
	 */
	public void setStudentReverseConverter(final Converter<StudentData, StudentModel> studentReverseConverter)
	{
		this.studentReverseConverter = studentReverseConverter;
	}

	

}
