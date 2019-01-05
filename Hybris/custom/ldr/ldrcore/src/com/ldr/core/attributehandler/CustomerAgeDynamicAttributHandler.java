/**
 *
 */
package com.ldr.core.attributehandler;

import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;

import java.util.Calendar;
import java.util.Date;


/**
 * @author devreddy
 *
 */
public class CustomerAgeDynamicAttributHandler implements DynamicAttributeHandler<Integer, CustomerModel>
{
	public static int age = 0;

	@Override
	public Integer get(final CustomerModel model)
	{
		try
		{
			final Date date = model.getDateOfBirth();
			final Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			final int year = cal.get(Calendar.YEAR);
			final int year1 = Calendar.getInstance().get(Calendar.YEAR);
			age = year1 - year;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}

		return Integer.valueOf(age);
	}


	@Override
	public void set(final CustomerModel arg0, final Integer val)
	{
		if (val != null)
		{
			throw new UnsupportedOperationException();
		}

	}



}
