package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void addEmployee(Employee emp) {
		employeeRepository.save(emp);
	}
	public List<Employee> getAllEmp() {
		return employeeRepository.findAll();
	}
	public void deleteEmployee(int id) {
		employeeRepository.deleteById(null);
		
	}
	public Employee getEmployeeById(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		if (emp.isPresent()) {
			return emp.get();
		}
		return null;
	}

	public Page<Employee> getEmployeeByPaginate(int currentPage, int size) {
		Pageable p = PageRequest.of(currentPage, size);
		return employeeRepository.findAll(p);
	}
	
}
