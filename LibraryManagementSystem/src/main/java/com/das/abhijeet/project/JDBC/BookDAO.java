package com.das.abhijeet.project.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.das.abhijeet.project.JDBC.util.DataAccessObject;

public class BookDAO extends DataAccessObject<Book> {
	
	private final String GET_ONE = "select * from book where id=?";
	
	private final String GET_BOOK = "select * from book where name=?";
	
	private final String GET_ALL = "select * from book";

	private final String INSERT = "insert into book (name, author, edition, price, pages) values (?,?,?,?,?)";
	
	private final String DELETE = "delete from book where id=?";

	public BookDAO(Connection connection) {
		super(connection);
	}

	@Override
	public Book findById(long id) {
		Book book = new Book();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				book.setId(rs.getLong("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getString("edition"));
				book.setPrice(rs.getDouble("price"));
				book.setPages(rs.getString("pages"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return book;
	}
	
	public List<Book> findByName(String bookName) {
		List<Book> books = new ArrayList<>();
		Book book;
		try(PreparedStatement statement = this.connection.prepareStatement(GET_BOOK);) {
			statement.setString(1, bookName);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				book = new Book();
				book.setId(rs.getLong("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getString("edition"));
				book.setPrice(rs.getDouble("price"));
				book.setPages(rs.getString("pages"));
				books.add(book);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return books;
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		Book book;
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);) {
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				book = new Book();
				book.setId(rs.getLong("id"));
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setEdition(rs.getString("edition"));
				book.setPrice(rs.getDouble("price"));
				book.setPages(rs.getString("pages"));
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return books;
	}

	@Override
	public Book update(Book dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book create(Book dto) {
		try(PreparedStatement statement = this.connection.prepareStatement(INSERT);) {
			statement.setString(1, dto.getName());
			statement.setString(2, dto.getAuthor());
			statement.setString(3, dto.getEdition());
			statement.setDouble(4, dto.getPrice());
			statement.setString(5, dto.getPages());
			statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		try(PreparedStatement statement = this.connection.prepareStatement(DELETE);) {
			statement.setLong(1, id);
			statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
