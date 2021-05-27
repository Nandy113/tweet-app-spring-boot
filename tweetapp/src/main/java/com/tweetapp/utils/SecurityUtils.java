package com.tweetapp.utils;

import java.util.Base64;

import com.tweetapp.model.User;

public class SecurityUtils {

	public static User getUser(String authHeader) {

		String encodedCredentials = authHeader.split(" ")[1];
		byte[] credentials = Base64.getDecoder().decode(encodedCredentials);
		String username = new String(credentials).split(":")[0];
		String password = new String(credentials).split(":")[1];
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		return user;
	}

	

}
