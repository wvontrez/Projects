package com.revature.daos;

import java.util.ArrayList;

import com.revature.models.books;

public interface booksDAOInterface {

	//I'm going to lay out a bunch of abstract methods that the EmployeeDAO must implement
	
		//What's the point? DAO classes get VERY long and complicated. This is a great way to see what methods are actually there.
		//A good way to quickly reference the methods found in the very code heavy DAO Classes.
		
		//A method to insert a new book
		boolean insertBook(books book);
		
		//A method to get all books
		ArrayList<books> getBooks();
		
		
		//A method to delete books
		public void deleteBooks(int id);
		
		
}
