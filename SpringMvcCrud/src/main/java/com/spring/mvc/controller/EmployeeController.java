package com.spring.mvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springmvc.entity.Employee;
import com.springmvc.service.EmployeeService;

@Controller
public class EmployeeController {

	private EmployeeService employeeService ;
	
	@Autowired
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

	@RequestMapping("/home")
	public String home() {
		return "redirect:/display-employee";
	}
	
	@RequestMapping("/insert-page")
	public String insertPage () {
		return "insert";
	}
	
	@RequestMapping(value= "/insert-employee", method = RequestMethod.POST)
	public String insert(@ModelAttribute Employee employee) {
		if ( employee != null) {
			if (employee.getEmployeeId() != null) {
				employeeService.updateEmployee(employee);
			} else {
				employeeService.addEmployee(employee);
			}
		}
		return "redirect:/home";
	}
	
	@RequestMapping(path = "/display-employee", method = RequestMethod.GET)
	public String display(Model model) {
		List<Employee> list = employeeService.getAllEmploye();
		model.addAttribute("list", list);
		return "display";
	}
	
	@RequestMapping(value = "/edit-employee/{id}")
	public String editEmployee(@PathVariable int id, Model model) {
		Employee employee = employeeService.getEmpId(id);
		model.addAttribute("employee", employee);
		return "insert";
	}

	@RequestMapping(value="/delete-employee/{id}", method = RequestMethod.GET)    
	public String delete(@PathVariable int id){    
	        employeeService.deleteEmployee(id);    
	        return "redirect:/display-employee";    
	}    	
} 
