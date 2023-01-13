package com.example.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String home() 
	{
		return "/index";
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}
	
	@PostMapping("/register")
	public String empRegister(@ModelAttribute Employee emp, HttpSession session) {
		System.out.println(emp);
		 employeeService.addEmployee(emp);
		 session.setAttribute("msg", "employee added successfully...");
		return "redirect:/";
		
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id, Model m) {
		Employee emp = employeeService.getEmployeeById(id);
		m.addAttribute("employee", emp);
		return "edit";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee emp, HttpSession session) {
		employeeService.addEmployee(emp);
		session.setAttribute("msg", "Emp Data Update Sucessfully..");
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteEMp(@PathVariable int id, HttpSession session) {

		employeeService.deleteEmployee(id);
		session.setAttribute("msg", "Emp Data Delete Sucessfully..");
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<Employee> emplist = employeeService.getEmployeeByPaginate(pageno, 2);
		m.addAttribute("emp", emplist);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", emplist.getTotalPages());
		m.addAttribute("totalItem", emplist.getTotalElements());
		return "index";
	}
}
