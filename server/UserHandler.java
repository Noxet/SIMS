
package server;

import java.net.*;
import java.io.*;
import java.util.*;

class UserHandler {
	
	HashMap<String, User> users;

	public UserHandler() {
		users = new HashMap<String, User>();
	}

	public synchronized boolean addUser(String username) {
		if (users.containsKey(username)) return false;
		
		users.put(username, new User(username));
		return true;
	}

	public synchronized User getUser(String username) {
		return users.get(username);
	}
}
