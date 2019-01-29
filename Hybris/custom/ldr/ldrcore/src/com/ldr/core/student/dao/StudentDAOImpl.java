/**
 *
 */
package com.ldr.core.student.dao;

import de.hybris.platform.core.model.enumeration.EnumerationValueModel;
import de.hybris.platform.servicelayer.internal.dao.DefaultGenericDao;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.ldr.core.model.StudentModel;


/**
 * @author devreddy
 *
 */
public class StudentDAOImpl extends DefaultGenericDao implements StudentDAO
{

	/**
	 * @param typecode
	 */
	public StudentDAOImpl(final String typecode)
	{
		super(typecode);
	}



	private FlexibleSearchService flexibleSearchService;

	private ModelService modelService;

	/*
	 *
	 * @see com.ldr.core.student.dao.StudentDAO#getAllStudentDetails()
	 */
	@Override
	public List<StudentModel> getAllStudentDetails()
	{
		final String query = "SELECT {pk} FROM {Student}";

		final SearchResult<StudentModel> searchResults = flexibleSearchService.search(query);

		for (final StudentModel studentModel : searchResults.getResult())
		{
			System.out.println("Student ID:: " + studentModel.getId());
			System.out.println("Student Name:: " + studentModel.getName());
			System.out.println("Student Name:: " + studentModel.getName());

		}

		return searchResults.getResult();
	}



	/**
	 * @return the flexibleSearchService
	 */
	@Override
	public FlexibleSearchService getFlexibleSearchService()
	{
		return flexibleSearchService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	@Override
	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	{
		this.flexibleSearchService = flexibleSearchService;
	}



	/*
	 * @see com.ldr.core.student.dao.StudentDAO#getStudentById(java.lang.String)
	 */
	@Override
	public StudentModel getStudentById(final String studentId)
	{
		final String query = "SELECT {pk} FROM {Student} WHERE {id}= ?id";

		final HashMap parameters = new HashMap();
		parameters.put("id", studentId);

		final SearchResult<StudentModel> searchResults = flexibleSearchService.search(query, parameters);

		if (CollectionUtils.isNotEmpty(searchResults.getResult()) && searchResults.getResult().size() > 0)
		{
			return searchResults.getResult().get(0);
		}
		return null;
	}



	/**
	 * @return the modelService
	 */
	public ModelService getModelService()
	{
		return modelService;
	}



	/**
	 * @param modelService
	 *           the modelService to set
	 */
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.core.student.dao.StudentDAO#doStudentRegistration(org.training.model.StudentModel)
	 */
	@Override
	public void doStudentRegistration(final StudentModel studentModel)
	{
		modelService.save(studentModel);

	}



	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.core.student.dao.StudentDAO#getStudentType()
	 */
	@Override
	public List<EnumerationValueModel> getStudentType()
	{

		final String query = "SELECT {pk} FROM {StudentType}";

		final SearchResult<EnumerationValueModel> searchResults = flexibleSearchService.search(query);

		return searchResults.getResult();
	}



	/*
	 * (non-Javadoc)
	 *
	 * @see com.ldr.core.student.dao.StudentDAO#getStudentByIdGenric(java.lang.String)
	 */
	@Override
	public StudentModel getStudentByIdGenric(final String studentId)
	{
		// YTODO Auto-generated method stub
		final List<StudentModel> studentModel = find(Collections.singletonMap(StudentModel.ID, (Object) studentId));

		if (CollectionUtils.isNotEmpty(studentModel))
		{
			return studentModel.get(0);
		}
		return null;
	}

}
