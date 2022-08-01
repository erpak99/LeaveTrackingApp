package demo.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import demo.app.entities.Employee;
import demo.app.services.EmployeeService;

@Controller 
public class LoginController {
	
	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String welcomePage(ModelMap model, @RequestParam String email, @RequestParam String password) {
		Employee employee = employeeService.getEmployeeByEmail(email);
		

		if(employee.getPassword().equals(password)) {
			model.put("email",email);
			return "welcome";
		}
		
		model.put("errorMessage", "Please provide correct email and password");
		return "login";
	}
	
}
