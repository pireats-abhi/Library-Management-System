package com.das.abhijeet.project.JDBC.util;

import java.sql.Connection;
import java.util.List;

public abstract class DataAccessObject <T extends DataTransferObject> {
	protected final Connection connection;
	
	public DataAccessObject(Connection connection) {
		super();
		this.connection = connection;
	}
	
	public abstract T findById(long id);
	public abstract List<T> findAll();
	public abstract T update(T dto);
	public abstract T create(T dto);
	public abstract void delete(long id);
}
