/**
 *
 */
package com.ldr.storefront.validator;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ProfileValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.ldr.storefront.forms.LDRUpdateProfileForm;


/**
 * @author devreddy
 *
 */
@Component("ldrProfileValidator")
public class LDRProfileValidator extends ProfileValidator
{
	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.acceleratorstorefrontcommons.forms.validation.ProfileValidator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object object, final Errors errors)
	{
		final LDRUpdateProfileForm profileForm = (LDRUpdateProfileForm) object;
		final String title = profileForm.getTitleCode();
		final String firstName = profileForm.getFirstName();
		final String lastName = profileForm.getLastName();
		final String mobileNumber = profileForm.getMobileNumber();
		final String dateOfBirth = profileForm.getDateOfBirth();

		if (StringUtils.isEmpty(title))
		{
			errors.rejectValue("titleCode", "profile.title.invalid");
		}
		else if (StringUtils.length(title) > 255)
		{
			errors.rejectValue("titleCode", "profile.title.invalid");
		}

		if (StringUtils.isBlank(firstName))
		{
			errors.rejectValue("firstName", "profile.firstName.invalid");
		}
		else if (StringUtils.length(firstName) > 255)
		{
			errors.rejectValue("firstName", "profile.firstName.invalid");
		}

		if (StringUtils.isBlank(lastName))
		{
			errors.rejectValue("lastName", "profile.lastName.invalid");
		}
		else if (StringUtils.length(lastName) > 255)
		{
			errors.rejectValue("lastName", "profile.lastName.invalid");
		}

		validateMobileNumber(mobileNumber, errors);
		validateDate(dateOfBirth, errors);
	}

	/**
	 * @param dateOfBirth
	 * @param errors
	 */
	private void validateDate(final String dateOfBirth, final Errors errors)
	{
		if (StringUtils.isEmpty(dateOfBirth))
		{
			errors.rejectValue("dateOfBirth", "register.dateOfBirth.notEmpty");
		}
		final Pattern pattern = Pattern.compile("^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$");
		final Matcher matcher = pattern.matcher(dateOfBirth);
		if (!matcher.matches())
		{
			errors.rejectValue("dateOfBirth", "register.dateOfBirth.invalid");
		}

	}

	/**
	 * @param mobileNumber
	 * @param errors
	 */
	private void validateMobileNumber(final String mobileNumber, final Errors errors)
	{
		if (StringUtils.isEmpty(mobileNumber))
		{
			errors.rejectValue("mobileNumber", "register.mobileNumber.notEmpty");
		}
		final Pattern pattern = Pattern.compile("[0-9]{10}");
		final Matcher matcher = pattern.matcher(mobileNumber);
		if (!matcher.matches())
		{
			errors.rejectValue("mobileNumber", "register.mobileNumber.invalid");
		}

	}
}
