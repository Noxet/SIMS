import java.net.*;
import java.io.*;
import java.util.*;

class OutputThread extends Thread {
	
	private BufferedWriter out;
	private BufferedReader in;

	public OutputThread(BufferedWriter out, BufferedReader in) {
		this.out = out;
		this.in = in;
	}

	@Override
	public void run() {
		while (true) {
			try {
				out.write(in.readLine());
			} catch (IOException e) {
				System.err.println("Fucked");
			}
		}
	}
}
