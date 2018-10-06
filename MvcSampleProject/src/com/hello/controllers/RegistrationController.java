package com.hello.controllers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hello.data.dto.StudentData;
import com.hello.forms.RegistrationForm;
import com.hello.service.StudentService;

@Controller
public class RegistrationController {

	@Resource(name = "studentService")
	private StudentService studentService;

	@RequestMapping(value = "/register-form", method = RequestMethod.GET)
	public String getRegistrationForm(ModelMap model) {
		RegistrationForm registrationForm = new RegistrationForm();
		model.addAttribute("registrationForm", registrationForm);
		return "student/registration";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String createRegistrationForm(RegistrationForm registrationForm, ModelMap model) 
	{
		if (!StringUtils.isEmpty(registrationForm.getFirstName())
				&& !StringUtils.isEmpty(registrationForm.getLastName())) {
			System.out.println(registrationForm.getFirstName() + "  " + registrationForm.getLastName());
			StudentData studentData = new StudentData();
			studentData.setFirstName(registrationForm.getFirstName());
			studentData.setLastName(registrationForm.getLastName());
			studentData.setEmail(registrationForm.getEmail());
			boolean result = this.studentService.setStudentInfo(studentData);
			model.addAttribute("msg", result);
		} else {
			System.out.println("Empty first name and last Name");
		}
		return "student/registrationSuccess";
	}

	@RequestMapping(value = "/search-page", method = RequestMethod.GET)
	public String searchPage(ModelMap model) 
	{
		
		return  "student/searchResults";
	}
	@RequestMapping(value = "/get-student-details", method = RequestMethod.GET)
	public String getStudentDetails(@RequestParam("search") String search, ModelMap model) 
	{
		if (!StringUtils.isEmpty(search) && search.trim().length() > 0) {
			List<StudentData> listOfStudents = studentService.getStundetDetails(search);
			if(!CollectionUtils.isEmpty(listOfStudents)){
				model.addAttribute("isSuccess", true);
				model.addAttribute("listOfStudents", listOfStudents);
				model.addAttribute("isSearchResultPage", true);
			}else {
				this.setMsg(model, search);
			}
		} else {
			this.setMsg(model, search);
		}
		return "student/searchResults";
	}
	
	public void setMsg(ModelMap model, String search){
		model.addAttribute("isSearchResultPage", true);
		model.addAttribute("isSuccess", false);
		model.addAttribute("searchVal", search);
	}
}
