package com.rs.app.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 

@Entity
public class CompanyEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;
	
    private String companyName;
    private String location;
    
    
	public CompanyEntity() {
		super();
		 
	}

	public CompanyEntity(int companyId, String companyName, String location) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.location = location;
		 
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	 
	@Override
	public String toString() {
		return "CompanyEntity [companyId=" + companyId + ", companyName=" + companyName + ", location=" + location
				+  "]";
	}
    
}
