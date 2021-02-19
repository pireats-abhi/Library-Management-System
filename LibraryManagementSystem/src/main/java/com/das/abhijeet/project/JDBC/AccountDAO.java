package com.das.abhijeet.project.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.das.abhijeet.project.JDBC.util.DataAccessObject;

public class AccountDAO extends DataAccessObject<Account> {
	
	private static final String GET_ONE = "select * from account where id = ?";
	private static final String INSERT = "insert into account (name, password, sq, sa) values (?,?,?,?)";

	public AccountDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Account findById(long id) {
		Account account = new Account();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				account.setId(rs.getLong("id"));
				account.setName(rs.getString("name"));
				account.setPassword(rs.getString("password"));
				account.setSq(rs.getString("sq"));
				account.setSa(rs.getString("sa"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return account;
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account update(Account dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account create(Account dto) {
		try(PreparedStatement statement = this.connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);) {
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getPassword());
			statement.setString(3, dto.getSq());
			statement.setString(4, dto.getSa());
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			long id = 0;
			while (rs.next()) {
				id = rs.getInt(1);
			}
			return findById(id);
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

}
