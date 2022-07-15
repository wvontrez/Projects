package com.revature.controllers;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.revature.daos.booksDAO;
import com.revature.models.books;

import io.javalin.http.Handler;

public class booksController {

	booksDAO bDAO = new booksDAO();
	
	//This Handler will get the HTTP GET Request for all employees, then collect the data and send it back in an HTTP Response 
		public Handler getBooksHandler = (ctx) -> {
		
			//what is ctx? it's the Context object! 
			//This object contains a bunch of method that we can use to take in HTTP Requests and send HTTP Responses
			
			if(AuthController.ses != null) { //if the user is logged in, they can access this functionality
			
			//We need an ArrayList of Employees, courtesy of our EmployeeDAO
			ArrayList<books> books = bDAO.getBooks();
			
			//create a GSON object, which has methods to convert our Java object into JSON
			Gson gson = new Gson();
			
			//use the GSON .toJson() method to turn our Java into JSON String (JSON is always in String format on the Java side)
			String JSONbooks = gson.toJson(books); //books is the ArrayList of our employee data
			
			//use ctx to provide an HTTP response containing our JSON string of employees (which is what was requested)
			
			ctx.result(JSONbooks); //ctx.result() sends a response back (this is where our data goes)
			
			ctx.status(200); //ctx.status() sets the HTTP status code. 200 stands for "OK", the generic success code.
			
			} else { //if the user is NOT logged in (aka AuthController.ses wil be null)
				ctx.result("YOU ARE NOT LOGGED IN!! *SMACK*");
				ctx.status(401); //"forbidden" access code
			}
			
		}; //semicolon after curly brace? That's lambdas for you.
		
		//This Handler will get the HTTP POST Request for inserting employees, then send the employee data to the DB.
		public Handler insertBookHandler = (ctx) -> {
			
			//With POST requests, we have some data coming in, which we access with ctx.body();
			//body?? it refers to the BODY of the HTTP Request (which is where the incoming data is found)
			String body = ctx.body(); //store the data in a String 
			
			//create a new GSON object to make JSON <-> Java conversions
			Gson gson = new Gson();
			
			//turn the incoming JSON String directly into an Employee object
			//remember, fromJson() is the method that takes a JSON String and turns it into some Java object
			books newBook = gson.fromJson(body, books.class);
			
			//we call the insertEmployee() method to send our newly created employee to the DB.
			//IF it succeeds, it'll return true since that's the return type of insertEmployee()
			if(bDAO.insertBook(newBook)) {
				//return a successful status code
				ctx.status(202); //202 stands for "accepted"
			} else {
				ctx.status(406); //406 stands for "Not Acceptable", AKA whatever the user sent couldn't be added to the DB
			}
			
		};
		
		public Handler deleteBookHandler = (ctx) -> {
			
			int dontLikeBook = Integer.valueOf(ctx.pathParam("id"));
			
			bDAO.deleteBooks(dontLikeBook);
			
			ctx.result("Book " + dontLikeBook + " terminated");
			ctx.status(200);
		};


}

