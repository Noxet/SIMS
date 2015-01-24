
package server;

import java.util.*;

public class User {
	
	String username;
	Queue<String> incoming;
	
	public User(String username) {
		this.username = username;
		incoming = new LinkedList<String>();
	}

	public synchronized String getUsername() {
		return username;
	}

	public synchronized void sendMessage(String message) {
		System.out.println("inc for " + username + ": " + message); // DEBUG
		incoming.offer(message);
	}

	public synchronized boolean incomingMessage() {
		return incoming.size() > 0;
	}

	public synchronized String getMessage() {
		String msg = incoming.poll();
		System.out.println("out for " + username + ": " + msg); // DEBUG
		return msg;
		//return incoming.poll();
	}
}
