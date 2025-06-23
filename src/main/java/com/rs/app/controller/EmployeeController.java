package com.rs.app.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.dao.EmployeeDao;
import com.rs.app.entity.EmployeeEntity;
import com.rs.app.repository.EmployeeRepo;
import com.rs.app.service.EmployeeService;

@RestController
@RequestMapping("/employee")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	@Autowired
	EmployeeDao ed;

	@Autowired
	EmployeeRepo emprepo;

	@Autowired
	EmployeeService empserve;

	@PostMapping("/save")
	public EmployeeEntity saveRecord(@RequestBody EmployeeEntity ee) {
		return empserve.saveRecord(ee);
	}

	@GetMapping("/getAll")
	public String getRecord() throws Exception {
		return empserve.getRecord();
	}

	@GetMapping("/getEmpById/{id}")
	public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable int id) {
		EmployeeEntity emp = emprepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));

		return ResponseEntity.ok(emp);
	}

	@PutMapping("/updateEmp/{id}")
	public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable int id, @RequestBody EmployeeEntity emp) {
		EmployeeEntity Emp = emprepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));

		Emp.setfName(emp.getfName());
		Emp.setlName(emp.getlName());
		Emp.seteMail(emp.geteMail());

		EmployeeEntity updatedEmp = emprepo.save(Emp);
		return ResponseEntity.ok(updatedEmp);

	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateEmployee1(@PathVariable int id, @RequestBody EmployeeEntity updatedData) {
		try {
			EmployeeEntity updated = empserve.updateEmployee(id, updatedData);
			return ResponseEntity.ok(updated);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		try {
			empserve.deleteEmployee(id);
			return ResponseEntity.ok("Employee deleted successfully.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

}
