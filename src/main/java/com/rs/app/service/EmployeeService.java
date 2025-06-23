package com.rs.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rs.app.dao.EmployeeDao;
import com.rs.app.entity.EmployeeEntity;

@Service
public class EmployeeService {
	@Autowired
	EmployeeDao empdao;

	public EmployeeEntity saveRecord(EmployeeEntity ee) {

		return empdao.saveRecord(ee);
	}

	public String getRecord() throws Exception {
		return empdao.getRecord();
	}

	public EmployeeEntity updateEmployee(int id, EmployeeEntity updatedData) {
		return empdao.updateEmployee(id, updatedData);
	}

	public void deleteEmployee(int id) {
		empdao.deleteEmployee(id);
	}

}
