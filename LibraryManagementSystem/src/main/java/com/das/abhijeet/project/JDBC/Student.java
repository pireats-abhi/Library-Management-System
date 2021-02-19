package com.das.abhijeet.project.JDBC;

import com.das.abhijeet.project.JDBC.util.DataTransferObject;

public class Student implements DataTransferObject {
	
	private long id;
	private String name;
	private String fatherName;
	private String department;
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
