/**
 *
 */
package com.ldr.storefront.student;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ldr.storefront.student.forms.StudentRegistrationForm;


/**
 * @author devreddy
 *
 */
@Component(value = "studentValidator")
public class StudentValidator implements Validator
{

	public static final Pattern EMAIL_REGEX = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b");

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return StudentRegistrationForm.class.equals(aClass);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object object, final Errors errors)
	{
		final StudentRegistrationForm studentRegistrationForm = (StudentRegistrationForm) object;
		validateName(errors, studentRegistrationForm.getFirstName(), "firstName", "register.firstName.invalid");
		validateName(errors, studentRegistrationForm.getLastName(), "lastName", "register.lastName.invalid");
		validateEmail(errors, studentRegistrationForm.getEmail());
	}


	protected void validateName(final Errors errors, final String name, final String propertyName, final String property)
	{
		if (StringUtils.isBlank(name))
		{
			errors.rejectValue(propertyName, property);
		}
		else if (StringUtils.length(name) > 255)
		{
			errors.rejectValue(propertyName, property);
		}
	}

	protected void validateEmail(final Errors errors, final String email)
	{
		if (StringUtils.isEmpty(email) && StringUtils.isBlank(email))
		{
			errors.rejectValue("email", "register.email.invalid");
		}
		else if (StringUtils.length(email) > 255 || !validateEmailAddress(email))
		{
			errors.rejectValue("email", "register.email.invalid");
		}
	}


	public boolean validateEmailAddress(final String email)
	{
		final Matcher matcher = EMAIL_REGEX.matcher(email);
		return matcher.matches();
	}

}
