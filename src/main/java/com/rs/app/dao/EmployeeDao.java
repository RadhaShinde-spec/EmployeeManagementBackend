package com.rs.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rs.app.entity.EmployeeEntity;
import com.rs.app.repository.EmployeeRepo;

@Component
public class EmployeeDao {
	@Autowired
	EmployeeRepo er;

	public EmployeeEntity saveRecord(EmployeeEntity ee) {
		if (er.existsByEMail(ee.geteMail())) {
			throw new RuntimeException("Employee with email " + ee.geteMail() + " already exists.");
		}
		return er.save(ee);
	}

	public String getRecord() throws Exception {
		JSONArray result = new JSONArray();

		// Load MySQL JDBC Driver
		Class.forName("com.mysql.jdbc.Driver");

		// Establish connection
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Project", "root", "mysql@1234");

		// Create a statement
		Statement stmt = con.createStatement();

		// Execute the query
//		    ResultSet rs = stmt.executeQuery("SELECT c.company_name as companyName, e.id, e.f_name, e.l_name ,e.e_mail \n"
//		    		+ "		            FROM employee_entity e \n"
//		    		+ "		            INNER JOIN company_entity c ON e.company_id = c.company_id \n"
//		    		+ "                    group by e.id;");
		ResultSet rs = stmt
				.executeQuery("SELECT e.id, e.f_name, e.l_name, e.e_mail, c.company_name " + "FROM employee_entity e "
						+ "INNER JOIN company_entity c ON e.company_id = c.company_id " + "GROUP BY e.id");

		// Process the ResultSet and populate the JSONArray

		ResultSetMetaData md = rs.getMetaData();
		int numCols = md.getColumnCount();
		List<String> colNames = IntStream.range(0, numCols).mapToObj(i -> {
			try {
				return md.getColumnName(i + 1);
			} catch (SQLException e) {
				e.printStackTrace();
				return "?";
			}
		}).collect(Collectors.toList());

		while (rs.next()) {
			JSONObject employee = new JSONObject();

			colNames.forEach(cn -> {
				try {
					employee.put(cn, rs.getObject(cn));
				} catch (JSONException | SQLException e) {
					e.printStackTrace();
				}
			});

			result.put(employee);
		}

		return "" + result;
	}

	public EmployeeEntity updateEmployee(int id, EmployeeEntity updatedData) {
		Optional<EmployeeEntity> optional = er.findById(id);
		if (optional.isPresent()) {
			EmployeeEntity existing = optional.get();
			existing.setfName(updatedData.getfName());
			existing.setlName(updatedData.getlName());
			existing.seteMail(updatedData.geteMail());
			existing.setCompanyId(updatedData.getCompanyId());
			return er.save(existing);
		} else {
			throw new RuntimeException("Employee with ID " + id + " not found.");
		}
	}

	public void deleteEmployee(int id) {
		if (!er.existsById(id)) {
			throw new RuntimeException("Employee with ID " + id + " not found.");
		}
		er.deleteById(id);
	}

}
