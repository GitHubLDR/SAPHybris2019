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
public class StudentReversePopulator implements Populator<StudentData, StudentModel>
{

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.converters.Populator#populate(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void populate(final StudentData source, final StudentModel target) throws ConversionException
	{
		target.setId(source.getId());
		target.setName(source.getName());
	}

}
