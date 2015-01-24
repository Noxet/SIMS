
package server;

import java.net.*;
import java.io.*;
import java.util.*;

import common.Protocol;

public class IMServer extends Thread {
	
	private static final int PORT = 1337;

	@Override
	public void run() {

		UserHandler uh = new UserHandler();

		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			while (true) {
				System.out.println("Waiting for connection...");
				Socket connection = serverSocket.accept();
				
				IMHandler imh = new IMHandler(connection, uh);
				imh.start();
			}
		} catch (IOException e) {
			System.err.println(e);
		}
	}

	public static void main(String[] args) {
		new IMServer().run();
	}
}

class IMHandler extends Thread {
	
	private Socket connection;
	private UserHandler userHandler;

	public IMHandler(Socket connection, UserHandler userHandler) {
		super("IMHandler Thread");

		this.connection = connection;
		this.userHandler = userHandler;
	}

	@Override
	public void run() {
		try {
			// ask for username
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			out.write("Enter username:\n");
			out.flush();

			String username = in.readLine();
			System.out.println("Username: " + username);
			userHandler.addUser(username);
			OutputThread ot = new OutputThread(userHandler.getUser(username), out, connection);
			ot.start();
			
			String friend;
			String msg;
			BufferedWriter outfriend;
			User sfriend;
			while (true) {
				friend = in.readLine();
				msg = in.readLine();
				sfriend = userHandler.getUser(friend);
				sfriend.sendMessage(msg);
				//outfriend = new BufferedWriter(new OutputStreamWriter(sfriend.getOutputStream()));
				
				//outfriend.write(msg + "\n");
				//outfriend.flush();
				//OutputThread ot = new OutputThread(out, sin);
				//ot.start();
			}

			//InputThread it = new InputThread(in, uh);
			//OutputThread ot = new OutputThread(out, uh);

		} catch (IOException e) {
			try {
				connection.close();
			} catch (IOException ie) {}

			System.err.println(e);
		}
	}
}

