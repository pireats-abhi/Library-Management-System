package com.das.abhijeet.project.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.das.abhijeet.project.JDBC.util.DataAccessObject;

public class IssueBookDAO extends DataAccessObject<IssueBook> {

	private final String INSERT = "insert into issueBook (issueDate, isReturn, returnDate, bookID, studentID) values (?,?,?,?,?)";
	
	private final String GET_ONE = "select * from issueBook where bookID=? and studentID=?";
	
	private final String UPDATE = "update issueBook set isReturn=?, returnDate=? where id=?";
	
	private final String GET_ALL = "select * from issueBook";
	
	public IssueBookDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public IssueBook findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public IssueBook findBook(long bookId, long studentId) {
		IssueBook issueBook = new IssueBook();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
			statement.setLong(1, bookId);
			statement.setLong(2, studentId);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				if (!rs.getBoolean("isReturn")) {
					issueBook.setId(rs.getLong("id"));
					issueBook.setIssueDate(rs.getDate("issueDate"));
					issueBook.setReturn(rs.getBoolean("isReturn"));
					issueBook.setReturnDate(rs.getDate("returnDate"));
					issueBook.setBookID(rs.getLong("bookID"));
					issueBook.setStudentID(rs.getLong("studentID"));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return issueBook;
	}

	@Override
	public List<IssueBook> findAll() {
		List<IssueBook> issueBooks = new ArrayList<>();
		IssueBook issueBook;
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				issueBook = new IssueBook();
				issueBook.setId(rs.getLong("id"));
				issueBook.setIssueDate(rs.getDate("issueDate"));
				issueBook.setReturn(rs.getBoolean("isReturn"));
				issueBook.setReturnDate(rs.getDate("returnDate"));
				issueBook.setBookID(rs.getLong("bookID"));
				issueBook.setStudentID(rs.getLong("studentID"));
				issueBooks.add(issueBook);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return issueBooks;
	}

	@Override
	public IssueBook update(IssueBook dto) {
		IssueBook issueBook = null;
		try(PreparedStatement statement = this.connection.prepareStatement(UPDATE);) {
			statement.setBoolean(1, dto.isReturn());
			statement.setDate(2, dto.getReturnDate());
			statement.setLong(3, dto.getId());
			statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public IssueBook create(IssueBook dto) {
		try (PreparedStatement statement = this.connection.prepareStatement(INSERT);) {
			statement.setDate(1, dto.getIssueDate());
			statement.setBoolean(2, false);
			statement.setDate(3, null);
			statement.setLong(4, dto.getBookID());
			statement.setLong(5, dto.getStudentID());
			statement.execute();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

}
