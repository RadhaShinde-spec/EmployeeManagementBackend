package com.rs.app.dao;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rs.app.entity.CompanyEntity;
import com.rs.app.repository.CompanyRepo;

@Component
public class CompanyDao {
	@Autowired
	CompanyRepo ec;
	
	
	public CompanyEntity saveComapany(CompanyEntity cc) {
		return ec.save(cc);
	}
	
	public List<CompanyEntity> getAllCompany(){
		return ec.findAll();
	}
	
 
}
