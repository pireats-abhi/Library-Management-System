package com.das.abhijeet.project.JDBC;

import com.das.abhijeet.project.JDBC.util.DataTransferObject;

public class Book implements DataTransferObject {
	private long id;
	private String name;
	private String author;
	private String edition;
	private double price;
	private String pages;
	@Override
	public long getId() {
		// TODO Auto-generated method stub
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEdition() {
		return edition;
	}
	public void setEdition(String edition) {
		this.edition = edition;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getPages() {
		return pages;
	}
	public void setPages(String pages) {
		this.pages = pages;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", edition=" + edition + ", price=" + price
				+ ", pages=" + pages + "]";
	}
	
}
