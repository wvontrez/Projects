package com.revature;

import java.sql.Connection;
import java.sql.SQLException;

import com.revature.controllers.AuthController;
import com.revature.controllers.booksController;
import com.revature.controllers.editionsController;
import com.revature.utils.ConnectionUtil;

import io.javalin.Javalin;

public class Launcher {

	public static void main(String[] args) {
		
System.out.println("========Welcome to the Book Inventory========");
		
		//this is a try-with-resources block. it will test whether our Connection works.
		//try-with-resources works by trying to open a certain resource (Connection in this case)
		//and catch an exception if the resource fails to open. 
		//So in this case, if we fail to open a connection, it will throw an exception
		try(Connection conn = ConnectionUtil.getConnection()){
			System.out.println("CONNECTION SUCCESSFUL :)");
		} catch (SQLException e) {
			//if the connection fails to open, catch the resulting SQLException and print the stack trace (error log)
			System.out.println("connection failed...");
			e.printStackTrace();
		} //end of the connection test
			
		
		//Typical Javalin syntax to CREATE a javalin object 
		Javalin app = Javalin.create(
				
				//the config lambda lets us specify certain configurations for our Javalin app.
				config -> {
					config.enableCorsForAllOrigins(); //this lets us process HTTP requests from any origin
				}
				
				).start(5699); //we need .start() to start our Java server on port 3000.
				//This port is important, because this is where we need to send requests to.
		
		//We need to make some endpoint handlers, which will take in requests and send them where they need to go
		//Javalin endpoint handlers are like a traffic cop to your Java server. They take HTTP traffic and direct it.
		
		booksController bc = new booksController();
		
		//Instantiate a RoleController so that we can use its handler
		editionsController ec = new editionsController();
		
		//Instantiate an AuthController... you know why hopefully :)
		AuthController ac = new AuthController();
		
		//endpoint handlers below--------------------------
		
		
		app.get("/books", bc.getBooksHandler);
		//what does /books relate to? it's something we define. we want requests ending in /books to get all books
		
		//app.post() is the javalin method that takes in POST requests. It will insert book data into the DB.
		//how come we can have two endpoints of "/books"? that's because one is for a GET, while the other is a POST
		app.post("/books", bc.insertBookHandler);
		
		//app.put() is a javalin method that takes PUT requests. 
		//It will take in two pieces of data: the edition title (in the path parameter) and the price (in the Request body)
		//:title? This is a PATH PARAMETER. Whatever the user inserts here in the request will be used in the controller
		app.put("/editions/:title", ec.updatePriceHandler);
		
		app.delete("/delete/:id", bc.deleteBookHandler);
	
		app.post("/login", ac.loginHandler);
		
	} //end of main method
}
	
	
	

