
package server;

import java.net.*;
import java.io.*;
import java.util.*;

class OutputThread extends Thread {
	
	private User user;
	private BufferedWriter out;
	private Socket socket;

	public OutputThread(User user, BufferedWriter out, Socket socket) {
		super("OThread for " + user.getUsername());
		this.user = user;
		this.out = out;
		this.socket = socket;
	}

	@Override
	public void run() {
		while (!socket.isClosed() && !socket.isOutputShutdown()) {
			try {
				if (user.incomingMessage() && !socket.isClosed()) {
					out.write(user.getMessage() + "\n");
					out.flush();
				}
			} catch (IOException e) {
				System.err.println("OThread<" + user.getUsername() + ">: " + e);
				break; // TEST
			}
		}
	}
}
