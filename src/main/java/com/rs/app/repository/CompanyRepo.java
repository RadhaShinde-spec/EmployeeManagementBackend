package com.rs.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rs.app.entity.CompanyEntity;

@Repository
public interface CompanyRepo extends JpaRepository<CompanyEntity, Integer> {
	boolean existsByCompanyNameAndLocation(String companyName, String location);
}
