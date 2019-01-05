/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.ldr.storefront.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractLoginPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.acceleratorstorefrontcommons.forms.ConsentForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.GuestForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.LoginForm;
import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.commercefacades.user.data.RegisterData;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ldr.storefront.controllers.ControllerConstants;
import com.ldr.storefront.forms.LDRRegistrationForm;
import com.ldr.storefront.validator.LDRRegistrationValidator;


/**
 * Login Controller. Handles login and register for the account flow.
 */
@Controller
@RequestMapping(value = "/login")
public class LoginPageController extends AbstractLoginPageController
{
	private static final Logger LOG = Logger.getLogger(LoginPageController.class);

	private static final String FORM_GLOBAL_ERROR = "form.global.error";

	private HttpSessionRequestCache httpSessionRequestCache;

	@Resource
	private LDRRegistrationValidator ldrRegistrationValidator;

	/**
	 * @return the ldrRegistrationValidator
	 */
	public LDRRegistrationValidator getLdrRegistrationValidator()
	{
		return ldrRegistrationValidator;
	}

	/**
	 * @param ldrRegistrationValidator
	 *           the ldrRegistrationValidator to set
	 */
	public void setLdrRegistrationValidator(final LDRRegistrationValidator ldrRegistrationValidator)
	{
		this.ldrRegistrationValidator = ldrRegistrationValidator;
	}

	@Override
	protected String getView()
	{
		return ControllerConstants.Views.Pages.Account.AccountLoginPage;
	}

	@Override
	protected String getSuccessRedirect(final HttpServletRequest request, final HttpServletResponse response)
	{
		if (httpSessionRequestCache.getRequest(request, response) != null)
		{
			return httpSessionRequestCache.getRequest(request, response).getRedirectUrl();
		}
		return "/";
	}

	@Override
	protected AbstractPageModel getCmsPage() throws CMSItemNotFoundException
	{
		return getContentPageForLabelOrId("login");
	}


	@Resource(name = "httpSessionRequestCache")
	public void setHttpSessionRequestCache(final HttpSessionRequestCache accHttpSessionRequestCache)
	{
		this.httpSessionRequestCache = accHttpSessionRequestCache;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String doLogin(@RequestHeader(value = "referer", required = false) final String referer,
			@RequestParam(value = "error", defaultValue = "false") final boolean loginError, final Model model,
			final HttpServletRequest request, final HttpServletResponse response, final HttpSession session)
			throws CMSItemNotFoundException
	{
		if (!loginError)
		{
			storeReferer(referer, request, response);
		}
		model.addAttribute("ldrRegistrationForm", new LDRRegistrationForm());
		return getDefaultLoginPage(loginError, session, model);
	}

	protected void storeReferer(final String referer, final HttpServletRequest request, final HttpServletResponse response)
	{
		if (StringUtils.isNotBlank(referer) && !StringUtils.endsWith(referer, "/login")
				&& StringUtils.contains(referer, request.getServerName()))
		{
			httpSessionRequestCache.saveRequest(request, response);
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String doRegister(@RequestHeader(value = "referer", required = false) final String referer,
			final LDRRegistrationForm form, final BindingResult bindingResult, final Model model, final HttpServletRequest request,
			final HttpServletResponse response, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		getLdrRegistrationValidator().validate(form, bindingResult);
		return processRegisterUserRequest(referer, form, bindingResult, model, request, response, redirectModel);
	}


	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractRegisterPageController#
	 * processRegisterUserRequest(java.lang.String, de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm,
	 * org.springframework.validation.BindingResult, org.springframework.ui.Model, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	 */
	@Override
	protected String processRegisterUserRequest(final String referer, final RegisterForm form, final BindingResult bindingResult,
			final Model model, final HttpServletRequest request, final HttpServletResponse response,
			final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{

		if (bindingResult.hasErrors())
		{
			model.addAttribute("ldrRegistrationForm", form);
			model.addAttribute(new LoginForm());
			model.addAttribute(new GuestForm());
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			return handleRegistrationError(model);
		}

		final RegisterData data = new RegisterData();
		final LDRRegistrationForm ldrRegistrationForm = (LDRRegistrationForm) form;
		data.setFirstName(ldrRegistrationForm.getFirstName());
		data.setLastName(ldrRegistrationForm.getLastName());
		data.setLogin(ldrRegistrationForm.getEmail());
		data.setPassword(ldrRegistrationForm.getPwd());
		data.setTitleCode(ldrRegistrationForm.getTitleCode());
		data.setMobileNumber(ldrRegistrationForm.getMobileNumber());
		try
		{
			final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/mm/dd");
			data.setDateOfBirth(simpleDateFormat.parse(ldrRegistrationForm.getDateOfBirth()));
		}
		catch (final ParseException e1)
		{
			e1.printStackTrace();
		}
		try
		{
			getCustomerFacade().register(data);
			getAutoLoginStrategy().login(form.getEmail().toLowerCase(), form.getPwd(), request, response);
			final ConsentForm consentForm = form.getConsentForm();
			if (consentForm != null && consentForm.getConsentGiven())
			{
				getConsentFacade().giveConsent(consentForm.getConsentTemplateId(), consentForm.getConsentTemplateVersion());
			}
			GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
					"registration.confirmation.message.title");
		}
		catch (final DuplicateUidException e)
		{
			LOG.warn("registration failed: ", e);
			model.addAttribute(form);
			model.addAttribute(new LoginForm());
			model.addAttribute(new GuestForm());
			bindingResult.rejectValue("email", "registration.error.account.exists.title");
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			return handleRegistrationError(model);
		}

		return REDIRECT_PREFIX + getSuccessRedirect(request, response);


	}
}
