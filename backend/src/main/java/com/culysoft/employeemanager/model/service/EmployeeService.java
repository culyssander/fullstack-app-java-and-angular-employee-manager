package com.culysoft.employeemanager.model.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.culysoft.employeemanager.model.entiy.Employee;
import com.culysoft.employeemanager.model.exception.EmployeeNotFoundException;
import com.culysoft.employeemanager.model.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;
	
	public Employee saveEmployee(Employee employee) {
		if(employee.getId() == null)
			employee.setEmployeeCode(UUID.randomUUID().toString());
		return repository.save(employee);
	}
	
	public List<Employee> findAllEmployees() {
		return repository.findAll();
	}
	
	public Employee findByIdEmployee(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
	}
	
	public void deleteEmployeeById(Long id) {
		repository.deleteById(id);
	}
}
