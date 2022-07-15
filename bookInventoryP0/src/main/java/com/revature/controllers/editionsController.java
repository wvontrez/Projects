package com.revature.controllers;

import com.revature.daos.editionsDAO;

import io.javalin.http.Handler;

public class editionsController {

	editionsDAO eDAO = new editionsDAO();
	
	//this Handler will get the HTTP PUT request to update a Role salary.
		public Handler updatePriceHandler = (ctx) -> {
			
			
			String title = ctx.pathParam("title"); 
			
			//int to hold the new edition price, which the user will include in the BODY of the HTTP Request
			int price = Integer.parseInt(ctx.body()); //we need to use parseInt here, because ctx.body() returns a String
			//in postman, no need to make a JSON object, we can just input whatever number we want.
			
			//if the update DAO method returns true (which means successful)..
			if(eDAO.updateEditionPrice(title, price)) {
				ctx.status(202); //202 stands for "accepted"
			} else {
				ctx.status(406); //406 stands for "not acceptable"
			}
			
		};
}
