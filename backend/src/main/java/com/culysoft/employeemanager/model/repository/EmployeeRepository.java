package com.culysoft.employeemanager.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.culysoft.employeemanager.model.entiy.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
