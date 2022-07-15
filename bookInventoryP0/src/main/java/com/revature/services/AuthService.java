package com.revature.services;

import com.revature.daos.AuthDAO;

public class AuthService {


	AuthDAO aDAO = new AuthDAO();
	
	public String login(String username, String password) {
		
		if(aDAO.login(username, password)) {
			return username; //if the username and password get a "true" from the DAO, send the username back.
		}
		
		return null; //if login fails, return null
		
	}
	
	//in a more complete application, we'd have a User Class with a bunch of variables that we'd instantiate here
	//but for now, we'll return the username
	
}
	
	

