/**
 *
 */
package de.hybris.platform.commerceservices.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;

import org.apache.commons.lang.StringUtils;


/**
 * @author devreddy
 *
 */
public class LDRCustomerAccountServiceImpl extends DefaultCustomerAccountService implements LDRCustomerAccountService
{

	/*
	 *
	 * @see
	 * de.hybris.platform.commerceservices.customer.impl.LDRCustomerAccountService#updateProfile(de.hybris.platform.core.
	 * model.user.CustomerModel, java.lang.String, java.lang.String, java.lang.String, java.util.Date, java.lang.String)
	 */
	@Override
	public void updateProfile(final CustomerModel customerModel, final String titleCode, final String name, final String login,
			final Date dateOfBirth, final String mobileNumber) throws DuplicateUidException
	{

		validateParameterNotNullStandardMessage("customerModel", customerModel);

		customerModel.setUid(login);
		customerModel.setName(name);
		customerModel.setDateOfBirth(dateOfBirth);
		customerModel.setMobileNumber(mobileNumber);
		if (StringUtils.isNotBlank(titleCode))
		{
			customerModel.setTitle(getUserService().getTitleForCode(titleCode));
		}
		else
		{
			customerModel.setTitle(null);
		}
		internalSaveCustomer(customerModel);


	}

}
