package com.hello.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.document.AbstractPdfStamperView;
import org.springframework.ui.ModelMap;

@Controller
public class HelloController{
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String welcomePage(ModelMap model) {
		return "redirect:/hello";
	}
	
   @RequestMapping(value="/hello", method = RequestMethod.GET)
   public String printHello(ModelMap model) 
   {
      model.addAttribute("message", "Hello Spring MVC Framework! Unique hire");
      return "hello";
   }
}