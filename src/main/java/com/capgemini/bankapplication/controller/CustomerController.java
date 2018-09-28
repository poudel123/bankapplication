package com.capgemini.bankapplication.controller;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capgemini.bankapplication.entities.Customer;
import com.capgemini.bankapplication.sevice.CustomerService;
@Controller
public class CustomerController {
	@Autowired
	private CustomerService customerservice;
	
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String sayHello() {
		return "index";
	}
	
	@RequestMapping(value="login")
	public String getLoginPage() {
		return "login";
	}
	@RequestMapping(value="login.do", method=RequestMethod.POST)
	public String authenticateCustomer1(@RequestParam int customerId, @RequestParam String password, HttpSession session, HttpServletRequest request) {
		Customer cust = customerservice.authenticate(new Customer(null, customerId, password, null, null, LocalDate.now(),null));
		session = request.getSession();
		session.setAttribute("customer", cust);
		return "display";
	}
	@RequestMapping(value = "/editProfile", method = RequestMethod.GET)
	public String updateCustomeru(HttpSession session,HttpServletRequest request, Model model) {
		
		 session = request.getSession();
		Customer c = (Customer) session.getAttribute("customer");
     	model.addAttribute("customer", c);
		return "editProfile";
	}
	
	
	@RequestMapping(value = "editProfile.do", method =  RequestMethod.POST)
	public String updateCustomer(Model model,@RequestParam String address,@RequestParam String emailId,HttpSession session,HttpServletRequest request) {
		 Customer cust=new Customer();
		 
		 session= request.getSession();
		 
		 Customer c=(Customer) session.getAttribute("customer");
		 c.setAddress(address);
		 c.setEmail(emailId);
		
		 Customer customer= customerservice.updateProfile(c);
		 System.out.println(customer);
		 session.setAttribute("customer", customer);
		 
		 
		 
		 System.out.println(c);
     	model.addAttribute("customer", customer);
		return "redirect:/editProfile";
	}
}
