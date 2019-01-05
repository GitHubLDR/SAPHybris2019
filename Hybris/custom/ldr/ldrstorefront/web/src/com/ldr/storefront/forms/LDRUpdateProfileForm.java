/**
 *
 */
package com.ldr.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.UpdateProfileForm;


/**
 * @author devreddy
 *
 */
public class LDRUpdateProfileForm extends UpdateProfileForm
{
	private String dateOfBirth;

	private String mobileNumber;

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

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 * @param mobileNumber the mobileNumber to set
	 */
	public void setMobileNumber(String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}
}
