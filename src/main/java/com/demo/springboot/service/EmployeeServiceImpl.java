package com.demo.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.springboot.exception.ResourceNotFoundException;
import com.demo.springboot.model.Employee;
import com.demo.springboot.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employee createEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	public ResponseEntity<Employee> getEmployeeById(Long id) throws Exception {
		Optional<Employee> opt = this.employeeRepository.findById(id == null ? 0 : id);
		if (!opt.isPresent()) {
			throw new ResourceNotFoundException("employee with id is not exist!");
		}
		return ResponseEntity.ok(opt.get());
	}

	@Override
	public ResponseEntity<Employee> updateEmployee(Long id, Employee employeeDetail) throws Exception {
		Optional<Employee> opt = this.employeeRepository.findById(id == null ? 0 : id);
		if (!opt.isPresent()) {
			throw new ResourceNotFoundException("employee with id is not exist!");
		}
		opt.get().setFirstName(employeeDetail.getFirstName());
		opt.get().setLastName(employeeDetail.getLastName());
		opt.get().setEmail(employeeDetail.getEmail());
		return ResponseEntity.ok(this.employeeRepository.save(opt.get()));
	}

	@Override
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(Long id) throws Exception {
		Optional<Employee> opt = this.employeeRepository.findById(id == null ? 0 : id);
		if (!opt.isPresent()) {
			throw new ResourceNotFoundException("employee with id is not exist!");
		}
		this.employeeRepository.deleteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

}
