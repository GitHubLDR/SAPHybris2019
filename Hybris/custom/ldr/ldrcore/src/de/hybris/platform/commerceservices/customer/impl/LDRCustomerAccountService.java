/**
 *
 */
package de.hybris.platform.commerceservices.customer.impl;

import de.hybris.platform.commerceservices.customer.CustomerAccountService;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.core.model.user.CustomerModel;

import java.util.Date;


/**
 * @author devreddy
 *
 */
public interface LDRCustomerAccountService extends CustomerAccountService
{
	/**
	 * Updates the current user with the given parameters
	 *
	 * @param customerModel
	 *           the customer to update
	 * @param titleCode
	 *           the code for the title to set
	 * @param name
	 *           the full name to set on the customer
	 * @param login
	 *           the UID to set on the customer
	 * @throws DuplicateUidException
	 *            if the email is not unique
	 */
	void updateProfile(CustomerModel customerModel, String titleCode, String name, String login, Date dateOfBirth,
			String mobileNumber) throws DuplicateUidException;
}
