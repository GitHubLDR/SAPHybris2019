/**
 *
 */
package com.ldr.storefront.validator;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.ldr.storefront.forms.LDRRegistrationForm;


/**
 * @author devreddy
 *
 */
@Component("ldrRegistrationValidator")
public class LDRRegistrationValidator extends RegistrationValidator
{

	private static final Logger LOG = Logger.getLogger(LDRRegistrationValidator.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object object, final Errors errors)
	{

		final LDRRegistrationForm registerForm = (LDRRegistrationForm) object;
		final String titleCode = registerForm.getTitleCode();
		final String firstName = registerForm.getFirstName();
		final String lastName = registerForm.getLastName();
		final String email = registerForm.getEmail();
		final String pwd = registerForm.getPwd();
		final String checkPwd = registerForm.getCheckPwd();
		final String mobileNumber = registerForm.getMobileNumber();
		final String dateOfBirth = registerForm.getDateOfBirth();
		LOG.info("mobileNumber=" + mobileNumber + " == " + "dateOfBirth=" + dateOfBirth);
		validateTitleCode(errors, titleCode);
		validateName(errors, firstName, "firstName", "register.firstName.invalid");
		validateName(errors, lastName, "lastName", "register.lastName.invalid");

		if (StringUtils.length(firstName) + StringUtils.length(lastName) > 255)
		{
			errors.rejectValue("lastName", "register.name.invalid");
			errors.rejectValue("firstName", "register.name.invalid");
		}

		validateEmail(errors, email);
		validatePassword(errors, pwd);
		comparePasswords(errors, pwd, checkPwd);
		validateMobileNumber(mobileNumber, errors);
		validateDate(dateOfBirth, errors);
	}

	/**
	 * @param dateOfBirth
	 * @param errors
	 */
	private void validateDate(final String dateOfBirth, final Errors errors)
	{
		final Pattern pattern = Pattern.compile("^[0-9]{4}/(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])$");
		final Matcher matcher = pattern.matcher(dateOfBirth);
		if (StringUtils.isEmpty(dateOfBirth))
		{
			errors.rejectValue("dateOfBirth", "register.dateOfBirth.notEmpty");
		}
		else if (!matcher.matches())
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
		final Pattern pattern = Pattern.compile("[0-9]{10}");
		final Matcher matcher = pattern.matcher(mobileNumber);
		if (StringUtils.isEmpty(mobileNumber))
		{
			errors.rejectValue("mobileNumber", "register.mobileNumber.notEmpty");
		}
		else if (!matcher.matches())
		{
			errors.rejectValue("mobileNumber", "register.mobileNumber.invalid");
		}

	}
}
