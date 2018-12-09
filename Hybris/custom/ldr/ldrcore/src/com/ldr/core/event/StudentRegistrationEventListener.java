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
package com.ldr.core.event;

import de.hybris.platform.acceleratorservices.site.AbstractAcceleratorSiteEventListener;
import de.hybris.platform.basecommerce.model.site.BaseSiteModel;
import de.hybris.platform.commerceservices.enums.SiteChannel;
import de.hybris.platform.processengine.BusinessProcessService;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.util.ServicesUtil;

import org.springframework.beans.factory.annotation.Required;

import com.ldr.core.model.StudentRegistrationProcessModel;


/**
 * Listener for customer registration events.
 */
public class StudentRegistrationEventListener extends AbstractAcceleratorSiteEventListener<StudentRegistrationEvent>
{

	private ModelService modelService;
	private BusinessProcessService businessProcessService;

	protected BusinessProcessService getBusinessProcessService()
	{
		return businessProcessService;
	}

	@Required
	public void setBusinessProcessService(final BusinessProcessService businessProcessService)
	{
		this.businessProcessService = businessProcessService;
	}

	/**
	 * @return the modelService
	 */
	protected ModelService getModelService()
	{
		return modelService;
	}

	/**
	 * @param modelService
	 *           the modelService to set
	 */
	@Required
	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}

	@Override
	protected void onSiteEvent(final StudentRegistrationEvent studentRegistrationEvent)
	{
		final StudentRegistrationProcessModel studentRegistrationProcessModel = (StudentRegistrationProcessModel) getBusinessProcessService()
				.createProcess("studentRegistrationEmailProcess-" + studentRegistrationEvent.getStudent().getId() + "-"
						+ System.currentTimeMillis(), "studentRegistrationEmailProcess");
		studentRegistrationProcessModel.setSite(studentRegistrationEvent.getSite());
		studentRegistrationProcessModel.setCustomer(studentRegistrationEvent.getCustomer());
		studentRegistrationProcessModel.setLanguage(studentRegistrationEvent.getLanguage());
		studentRegistrationProcessModel.setCurrency(studentRegistrationEvent.getCurrency());
		studentRegistrationProcessModel.setStore(studentRegistrationEvent.getBaseStore());
		studentRegistrationProcessModel.setStudent(studentRegistrationEvent.getStudent());
		getModelService().save(studentRegistrationProcessModel);
		getBusinessProcessService().startProcess(studentRegistrationProcessModel);
	}

	@Override
	protected SiteChannel getSiteChannelForEvent(final StudentRegistrationEvent event)
	{
		final BaseSiteModel site = event.getSite();
		ServicesUtil.validateParameterNotNullStandardMessage("event.order.site", site);
		return site.getChannel();
	}
}
