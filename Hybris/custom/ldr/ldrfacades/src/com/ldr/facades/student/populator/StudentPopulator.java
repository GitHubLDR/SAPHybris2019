/**
 *
 */
package com.ldr.facades.student.populator;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;

import com.ldr.core.model.StudentModel;
import com.ldr.facades.student.data.StudentData;


/**
 * @author devreddy
 *
 */
public class StudentPopulator implements Populator<StudentModel, StudentData>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final StudentModel source, final StudentData target) throws ConversionException
	{
		if (null != source)
		{
			target.setId(source.getId());
			target.setName(source.getName());
		}
		else
		{
			System.out.println("No Student Data/object retunred");
		}

	}

}
