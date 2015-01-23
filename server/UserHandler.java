
import java.net.*;
import java.io.*;
import java.util.*;

class UserHandler {
	
	HashMap<String, Socket> users;

	public UserHandler() {
		users = new HashMap<String, Socket>();
	}

	public synchronized boolean addUser(String username, Socket connection) {
		if (users.containsKey(username)) return false;
		
		users.put(username, connection);
		return true;
	}

	public synchronized Socket getUser(String username) {
		return users.get(username);
	}
}
