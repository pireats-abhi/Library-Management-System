package com.das.abhijeet.project.JDBC;

import com.das.abhijeet.project.JDBC.util.DataTransferObject;

public class Account implements DataTransferObject {
	private long id;
	private String name;
	private String password;
	private String sq;
	private String sa;
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSq() {
		return sq;
	}

	public void setSq(String sq) {
		this.sq = sq;
	}

	public String getSa() {
		return sa;
	}

	public void setSa(String sa) {
		this.sa = sa;
	}

}
