package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.editions;
import com.revature.utils.ConnectionUtil;

public class editionsDAO implements editionsDAOInterface{

	@Override
	public editions getEditionById(int id) {
	
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from editions where edition_id = ?;";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id); //this means the first and only question mark
			
			//the data that is returned is a ResultSet - result set object holds our data
			ResultSet rs = ps.executeQuery(); //executes query into our new result set
			
			//need to iterate through the ResultSet to fill a editions all args constructor
			
			editions edition = new editions (
					rs.getInt("edition_id"),
					rs.getString("edition_title"),
					rs.getInt("edition_price")
					);
					
					
			return edition;		
					
					
		} catch (SQLException e) {
			System.out.println("Get Edition failed . . . bummer :("); //tell the console it failed
			e.printStackTrace(); //print an error log for debugs
		}
		
		return null;
	
	}//end of select by id method

	@Override
	public boolean updateEditionPrice(String title, int price) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//SQL String for our UPDATE command
			String sql = "update editions set edition_price = ? where edition_title = ?;";
			
			//create our PreparedStatement to fill in the variables
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, price);
			ps.setString(2, title);
			
			ps.executeUpdate();
			
			//tell the console the update was successfully 
			System.out.println(title + " has been updated to " + price);
			
			//if it succeeds, return true
			return true;
		
	} catch (SQLException e) {
		System.out.println("FAILED TO UPDATE");
		e.printStackTrace();
	}
		
		return false; 

}
	
}
