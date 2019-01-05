/**
 *
 */
package com.ldr.facades.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.commerceservices.customer.impl.LDRCustomerAccountService;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;


/**
 * @author devreddy
 *
 */
public class LDRCustomerFacadeImpl extends DefaultCustomerFacade
{

	private LDRCustomerAccountService ldrCustomerAccountService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#register(de.hybris.platform.commercefacades
	 * .user.data.RegisterData)
	 */
	@Override
	public void register(final RegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		final TitleModel title = getUserService().getTitleForCode(registerData.getTitleCode());
		newCustomer.setTitle(title);
		setUidForRegister(registerData, newCustomer);
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		newCustomer.setDateOfBirth(registerData.getDateOfBirth());
		newCustomer.setMobileNumber(registerData.getMobileNumber());
		getLdrCustomerAccountService().register(newCustomer, registerData.getPassword());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#updateProfile(de.hybris.platform.
	 * commercefacades.user.data.CustomerData)
	 */
	@Override
	public void updateProfile(final CustomerData customerData) throws DuplicateUidException
	{
		validateDataBeforeUpdate(customerData);

		final String name = getCustomerNameStrategy().getName(customerData.getFirstName(), customerData.getLastName());
		final CustomerModel customer = getCurrentSessionCustomer();
		customer.setOriginalUid(customerData.getDisplayUid());
		getLdrCustomerAccountService().updateProfile(customer, customerData.getTitleCode(), name, customerData.getUid(),
				customerData.getDateOfBirth(), customerData.getMobileNumber());
	}

	/**
	 * @return the ldrCustomerAccountService
	 */
	public LDRCustomerAccountService getLdrCustomerAccountService()
	{
		return ldrCustomerAccountService;
	}

	/**
	 * @param ldrCustomerAccountService
	 *           the ldrCustomerAccountService to set
	 */
	public void setLdrCustomerAccountService(final LDRCustomerAccountService ldrCustomerAccountService)
	{
		this.ldrCustomerAccountService = ldrCustomerAccountService;
	}

}
