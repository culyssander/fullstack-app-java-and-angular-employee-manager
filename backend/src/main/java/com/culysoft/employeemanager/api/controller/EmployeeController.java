package com.culysoft.employeemanager.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.culysoft.employeemanager.model.entiy.Employee;
import com.culysoft.employeemanager.model.service.EmployeeService;

@RestController
@RequestMapping("employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAll() {
		List<Employee> employees = service.findAllEmployees();
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable Long id) {
		return service.findByIdEmployee(id);
	}
	
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		employee.setId(null);
		Employee emp = service.saveEmployee(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		employee.setId(id);
		Employee emp = service.saveEmployee(employee);
		return new ResponseEntity<Employee>(emp, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		service.deleteEmployeeById(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
