package com.demo.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.demo.springboot.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();

	public Employee createEmployee(Employee employee);

	public ResponseEntity<Employee> getEmployeeById(Long id) throws Exception;

	public ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetail) throws Exception;

	public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) throws Exception;
}
