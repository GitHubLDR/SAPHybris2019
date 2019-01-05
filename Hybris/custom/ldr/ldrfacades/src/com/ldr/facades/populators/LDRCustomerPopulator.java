/**
 *
 */
package com.ldr.facades.populators;

import de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator;
import de.hybris.platform.commercefacades.user.data.CustomerData;
import de.hybris.platform.core.model.user.CustomerModel;


/**
 * @author devreddy
 *
 */
public class LDRCustomerPopulator extends CustomerPopulator
{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.user.converters.populator.CustomerPopulator#populate(de.hybris.platform.core.
	 * model.user.CustomerModel, de.hybris.platform.commercefacades.user.data.CustomerData)
	 */
	@Override
	public void populate(final CustomerModel source, final CustomerData target)
	{
		// YTODO Auto-generated method stub
		super.populate(source, target);
		target.setAge(source.getAge());
		target.setDateOfBirth(source.getDateOfBirth());
		target.setMobileNumber(source.getMobileNumber());
	}
}
