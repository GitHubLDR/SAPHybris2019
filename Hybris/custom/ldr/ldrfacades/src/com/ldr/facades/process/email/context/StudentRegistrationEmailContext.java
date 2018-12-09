/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.ldr.facades.process.email.context;

import de.hybris.platform.acceleratorservices.model.cms2.pages.EmailPageModel;
import de.hybris.platform.acceleratorservices.process.email.context.AbstractEmailContext;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.core.model.c2l.LanguageModel;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.dto.converter.Converter;

import com.ldr.core.model.StudentModel;
import com.ldr.core.model.StudentRegistrationProcessModel;
import com.ldr.facades.student.data.StudentData;


/**
 * Velocity context for a customer email.
 */
public class StudentRegistrationEmailContext extends AbstractEmailContext<StudentRegistrationProcessModel>
{
	private Converter<StudentModel, StudentData> studentConverter;
	private StudentData studentData;

	@Override
	public void init(final StudentRegistrationProcessModel businessProcessModel, final EmailPageModel emailPageModel)
	{
		// YTODO Auto-generated method stub
		super.init(businessProcessModel, emailPageModel);
		studentData = getStudentConverter().convert(businessProcessModel.getStudent());
		put(DISPLAY_NAME, businessProcessModel.getStudent().getName());
	}

	@Override
	protected CustomerModel getCustomer(final StudentRegistrationProcessModel businessProcessModel)
	{
		final CustomerModel customerModel = new CustomerModel();
		customerModel.setUid(businessProcessModel.getStudent().getId());
		customerModel.setName(businessProcessModel.getStudent().getName());
		return customerModel;
	}

	@Override
	protected BaseSiteModel getSite(final StudentRegistrationProcessModel businessProcessModel)
	{
		return businessProcessModel.getSite();
	}



	@Override
	protected LanguageModel getEmailLanguage(final StudentRegistrationProcessModel businessProcessModel)
	{
		return businessProcessModel.getLanguage();
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

	/**
	 * @return the studentData
	 */
	public StudentData getStudentData()
	{
		return studentData;
	}

	/**
	 * @param studentData
	 *           the studentData to set
	 */
	public void setStudentData(final StudentData studentData)
	{
		this.studentData = studentData;
	}

}
