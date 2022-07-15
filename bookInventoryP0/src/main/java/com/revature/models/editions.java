package com.revature.models;

public class editions {

	private int edition_id;
	private String edition_title;
	private int edition_price;
	
	
	
	//insert our boiler plate code
	
	//no args constructor
	
	
	public editions() {
		super();
		// TODO Auto-generated constructor stub
	}

//all args constructor

	public editions(int edition_id, String edition_title, int edition_price) {
		super();
		this.edition_id = edition_id;
		this.edition_title = edition_title;
		this.edition_price = edition_price;
	}

	//all args constructor without id fields, for INSERTing data with JDBC
public editions(String edition_title, int edition_price) {
	super();
	this.edition_title = edition_title;
	this.edition_price = edition_price;
}

//need a toString method to print out edition objects

@Override
public String toString() {
	return "editions [edition_id=" + edition_id + ", edition_title=" + edition_title + ", edition_price="
			+ edition_price + "]";
}

public int getEdition_id() {
	return edition_id;
}

public void setEdition_id(int edition_id) {
	this.edition_id = edition_id;
}

public String getEdition_title() {
	return edition_title;
}

public void setEdition_title(String edition_title) {
	this.edition_title = edition_title;
}

public int getEdition_price() {
	return edition_price;
}

public void setEdition_price(int edition_price) {
	this.edition_price = edition_price;
}

public void setEdition(editions e) {
	// TODO Auto-generated method stub
	
}
	
	
	
	
}
