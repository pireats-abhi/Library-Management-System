package com.das.abhijeet.project.JDBC;

import java.sql.Date;

import com.das.abhijeet.project.JDBC.util.DataTransferObject;

public class IssueBook implements DataTransferObject {
	private long id;
	private Date issueDate;
	private boolean isReturn;
	private Date returnDate;
	private long bookID;
	private long studentID;
	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return id;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public boolean isReturn() {
		return isReturn;
	}
	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public long getBookID() {
		return bookID;
	}
	public void setBookID(long bookID) {
		this.bookID = bookID;
	}
	public long getStudentID() {
		return studentID;
	}
	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}
	public void setId(long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "IssueBook [id=" + id + ", issueDate=" + issueDate + ", isReturn=" + isReturn + ", returnDate="
				+ returnDate + ", bookID=" + bookID + ", studentID=" + studentID + "]";
	}
	
	
	
	
}
