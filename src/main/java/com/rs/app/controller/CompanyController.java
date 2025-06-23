package com.rs.app.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rs.app.entity.CompanyEntity;
import com.rs.app.repository.CompanyRepo;
import com.rs.app.service.CompanyService;

@RestController
@RequestMapping("/company")
//@CrossOrigin("*")
@CrossOrigin(origins = "http://localhost:4200")
public class CompanyController {

	@Autowired
	CompanyService cs;

	@Autowired
	CompanyRepo crepo;

	@PostMapping("/save")
	public CompanyEntity saveCompany(@RequestBody CompanyEntity ed) {
		return cs.saveCompany(ed);
	}

	@GetMapping("/getAll")
	public List<CompanyEntity> getAllCompany() {
		return cs.getAllCompany();
	}

	@GetMapping("/getCompById/{id}")
	public ResponseEntity<CompanyEntity> getEmployeeById(@PathVariable int id) {
		CompanyEntity emp = crepo.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee not found with id: " + id));

		return ResponseEntity.ok(emp);
	}
}
