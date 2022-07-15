package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.books;
import com.revature.models.editions;
import com.revature.utils.ConnectionUtil;

public class booksDAO implements booksDAOInterface{

	@Override
	public boolean insertBook(books book) {
		
		//at the top of EVERY DAO METHOD, we need to open a database connection.
				try(Connection conn = ConnectionUtil.getConnection()){
					
				//First, we need out SQL String that represents the INSERT statement we want to send to the DB
				//There are variables here, and we can fill them out thanks to a PreparedStatement object
				//The question marks are how we indiciate that it's a value that we'll fill below
				String sql = "insert into books (book_name, author_name, edition_id_fk) values (?, ?, ?);";
					
				//Instantiate a PreparedStatement to fill in the variables of our SQL String (the ?s).
				//we use the prepareStatement() method from our Connection object to do this.
				PreparedStatement ps = conn.prepareStatement(sql);
					
				//fill in the values of our variables using ps.setXYZ()
	
				ps.setString(1, book.getBook_name()); //by "1" here, we're referring to the first question mark in the SQL String.
				ps.setString(2, book.getBook_author());
				ps.setInt(3, book.getEdition_id_fk()); 
									   
				
				System.out.println(ps);
				
				//we've created the SQL String and filled it with data - now we need to EXECUTE THE STATEMENT!
				ps.executeUpdate(); //This is what actually sends our SQL off to the database.
				
				//Tell the user the insert was successful
				System.out.println("Book " + book.getBook_name() + " was added!");
				
				return true; //if the update is successful, true will get returned
					
				} catch (SQLException e) { //if anything goes wrong, this SQLException will get thrown
					System.out.println("INSERT BOOK FAILED D:"); //tell the console we failed
					e.printStackTrace(); //print out the error log, which we'll need for debugging
				}
		
		return false;
	} //end of insert book

	@Override
	public ArrayList<books> getBooks() {
		
	try(Connection conn = ConnectionUtil.getConnection()){
		String sql  = "select * from books;";
		
		Statement s = conn.createStatement();
		
		ResultSet rs = s.executeQuery(sql);
		
		ArrayList<books> booksList = new ArrayList<>();
		
		while(rs.next()) {
			
			books b = new books(
						rs.getInt("book_id"),
						rs.getString("book_name"),
						rs.getString("author_name"),
						null
						);
			
			int editionFK = rs.getInt("edition_id_fk");
			
			editionsDAO eDAO = new editionsDAO();
			
			editions e = eDAO.getEditionById(editionFK);
			
			b.setEdition(e);
			
			booksList.add(b);
			
			rs.next();
	}//end of while loop
		
		return booksList;
		
		} catch (SQLException e) {
			System.out.println("SOMETHING WENT WRONG WITH GETTING BOOKS");
			e.printStackTrace();
		}
	return null;
	}
		
		
		
	
	

	@Override
	public void deleteBooks(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "delete from books where book_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			System.out.println("I don't want to read " + id + " anymore!!");
		} catch (SQLException e) {
			System.out.println("NOOOOOOOOO YOU WILL READ ME !!");
			e.printStackTrace();
		}
		
	}

}
