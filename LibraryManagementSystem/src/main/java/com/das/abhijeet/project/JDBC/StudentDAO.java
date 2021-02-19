package com.das.abhijeet.project.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.das.abhijeet.project.JDBC.util.DataAccessObject;

public class StudentDAO extends DataAccessObject<Student> {
	
	private final String GET_ONE = "select * from student where id=?";
	
	private final String GET_ALL = "select * from student";
	
	private final String INSERT = "insert into student (id, name, father_name, department) values (?,?,?,?)";
	
	private final String DELETE = "delete from student where id=?";

	public StudentDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Student findById(long id) {
		Student student = new Student();
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ONE);) {
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
				student.setFatherName(rs.getString("father_name"));
				student.setDepartment(rs.getString("department"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return student;
	}

	@Override
	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		Student student;
		try(PreparedStatement statement = this.connection.prepareStatement(GET_ALL);) {
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				student = new Student();
				student.setId(rs.getLong("id"));
				student.setName(rs.getString("name"));
				student.setFatherName(rs.getString("father_name"));
				student.setDepartment(rs.getString("department"));
				students.add(student);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return students;
	}

	@Override
	public Student update(Student dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student create(Student dto) {
		try(PreparedStatement statement = this.connection.prepareStatement(INSERT);) {
			statement.setLong(1, dto.getId());
			statement.setString(2, dto.getName());
			statement.setString(3, dto.getFatherName());
			statement.setString(4, dto.getDepartment());
			statement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
