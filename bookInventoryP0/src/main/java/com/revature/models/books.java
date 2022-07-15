package com.revature.models;

public class books {

	private int book_id;
	private String book_name;
	private String book_author;
	//every book has an edition, so we create edition obj as a variable
	private editions editions;
	
	//variable for int edition_id for inserting editions - its for insertion
	private int edition_id_fk;

	
	
	
	//boiler plate code
	
	public int getEdition_id_fk() {
		return edition_id_fk;
	}

	public void setEdition_id_fk(int edition_id_fk) {
		this.edition_id_fk = edition_id_fk;
	}

	public books() {
		super();
		// TODO Auto-generated constructor stub
	}

	public books(int book_id, String book_name, String book_author, com.revature.models.editions editions) {
		super();
		this.book_id = book_id;
		this.book_name = book_name;
		this.book_author = book_author;
		this.editions = editions;
	}

	public books(String book_name, String book_author, com.revature.models.editions editions) {
		super();
		this.book_name = book_name;
		this.book_author = book_author;
		this.editions = editions;
	}

	public books(String book_name, String book_author, int edition_id_fk) {
		super();
		this.book_name = book_name;
		this.book_author = book_author;
		this.edition_id_fk = edition_id_fk;
	}

	@Override
	public String toString() {
		return "books [book_id=" + book_id + ", book_name=" + book_name + ", book_author=" + book_author + ", editions="
				+ editions + ", edition_id_fk=" + edition_id_fk + "]";
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public editions getEditions() {
		return editions;
	}

	public void setEditions(editions editions) {
		this.editions = editions;
	}

	public void setEdition(com.revature.models.editions e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	
	
}
