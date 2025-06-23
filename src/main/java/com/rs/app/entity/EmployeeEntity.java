package com.rs.app.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
 
@Entity
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String fName;
	private String lName;
	private String eMail;
	private int companyId;
 
	public EmployeeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeEntity(int id, String fName, String lName, String eMail, int companyId ) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.eMail = eMail;
		this.companyId = companyId;
		 
 
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String iMage) {
		this.eMail = iMage;
	}
	 
 
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	 
	@Override
	public String toString() {
		return "EmployeeEntity [id=" + id + ", fName=" + fName + ", lName=" + lName + ", eMail=" + eMail
				+ ", companyId=" + companyId +  "]";
	}
	 
	 
	
	
}
