/**
 *
 */
package com.ldr.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;


/**
 * @author devreddy
 *
 */
public class LDRRegistrationForm extends RegisterForm
{
	private String dateOfBirth;

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth()
	{
		return dateOfBirth;
	}

	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth)
	{
		this.dateOfBirth = dateOfBirth;
	}

}
