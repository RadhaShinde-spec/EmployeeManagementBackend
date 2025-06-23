package com.rs.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.app.dao.CompanyDao;
import com.rs.app.entity.CompanyEntity;
import com.rs.app.repository.CompanyRepo;

@Service
public class CompanyService {
	@Autowired
	CompanyDao cd;

	@Autowired
	CompanyRepo cr;

	public CompanyEntity saveCompany(CompanyEntity ed) {
		boolean exists = cr.existsByCompanyNameAndLocation(ed.getCompanyName(), ed.getLocation());
		
		if(exists) {
			throw new RuntimeException("Company already exists with name: " 
	                + ed.getCompanyName() + " and location: " + ed.getLocation());
		}
		return cd.saveComapany(ed);
	}

	public List<CompanyEntity> getAllCompany() {
		return cd.getAllCompany();
	}

}
