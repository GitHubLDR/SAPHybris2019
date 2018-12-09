package com.ldr.storefront.student;
/*
 * [y] hybris Platform

 *
 * Copyright (c) 2000-2018 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */


import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ldr.facades.student.StudentFacade;
import com.ldr.facades.student.data.StudentData;
import com.ldr.storefront.student.forms.StudentRegistrationForm;


/**
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController extends AbstractPageController
{
	private static final String STUDENT_REGISTRATION_PAGE = "studentRegistrationPage";
	private static final String FORM_GLOBAL_ERROR = "form.global.error";

	@Resource
	private StudentFacade studentFacade;

	@Resource(name = "studentValidator")
	private StudentValidator studentValidator;



	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllStudentDetails(final Model model)
	{

		final List<StudentData> students = studentFacade.getAllStudentDetails();

		model.addAttribute("students", students);

		return "pages/account/studentDetailsPage";

	}


	@RequestMapping(value = "/{studentId}", method = RequestMethod.GET)
	public String getStudentDetails(@PathVariable final String studentId, final Model model)
	{

		final StudentData studentData = studentFacade.getStudentById(studentId);

		if (null != studentData)
		{
			System.out.println("Student ID:: " + studentData.getId());
			System.out.println("Student Name:: " + studentData.getName());
			System.out.println("Student Name:: " + studentData.getName());


			model.addAttribute("student", studentData);
		}

		return "pages/account/studentDetailsPage";

	}


	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String setStudenFormObj(final Model model) throws CMSItemNotFoundException
	{
		final StudentRegistrationForm studentRegistrationForm = new StudentRegistrationForm();

		model.addAttribute("studentregistrationform", studentRegistrationForm);
		model.addAttribute("action", "/student/register");
		model.addAttribute("actionNameKey", "Register");

		storeCmsPageInModel(model, getContentPageForLabelOrId(STUDENT_REGISTRATION_PAGE));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(STUDENT_REGISTRATION_PAGE));
		return getViewForPage(model);
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String getStudentRegistrationDetails(
			@ModelAttribute("studentregistrationform") final StudentRegistrationForm studentRegistrationForm, final Model model,
			final BindingResult bindingResult, final RedirectAttributes redirectModel) throws CMSItemNotFoundException
	{
		studentValidator.validate(studentRegistrationForm, bindingResult);

		if (bindingResult.hasErrors())
		{
			model.addAttribute("studentregistrationform", studentRegistrationForm);
			model.addAttribute("action", "/student/register");
			model.addAttribute("actionNameKey", "Register");
			GlobalMessages.addErrorMessage(model, FORM_GLOBAL_ERROR);
			storeCmsPageInModel(model, getContentPageForLabelOrId(STUDENT_REGISTRATION_PAGE));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(STUDENT_REGISTRATION_PAGE));
			return getViewForPage(model);
		}



		final StudentData studentData = new StudentData();

		studentData.setName(studentRegistrationForm.getFirstName() + " " + studentRegistrationForm.getLastName());
		studentData.setId(studentRegistrationForm.getEmail());
		studentData.setEmail(studentRegistrationForm.getEmail());

		studentFacade.doStudentRegistration(studentData);

		GlobalMessages.addFlashMessage(redirectModel, GlobalMessages.CONF_MESSAGES_HOLDER,
				"registration.confirmation.message.title");
		return REDIRECT_PREFIX + "/";
	}






}
