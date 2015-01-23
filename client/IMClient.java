
import java.net.*;
import java.io.*;
import java.util.*;

class IMClient {
	
	public static void main(String[] args) {
		String host = args[0];
		try {
			Socket socket = new Socket(host, 1337);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			OThread ot = new OThread(out);
			ot.start();
			IThread it = new IThread(in);		
			it.start();

		} catch (Exception e) {
			System.err.println(e);
		}
	}
}

class IThread extends Thread {
	
	private BufferedReader in;

	public IThread(BufferedReader in) {
		this.in = in;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("-- " + in.readLine());
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}

class OThread extends Thread {

	private BufferedWriter out;

	public OThread(BufferedWriter out) {
		this.out = out;
	}

	@Override
	public void run() {
		Scanner s = new Scanner(System.in);
		//System.out.print(s.nextLine()); // TEST
		while (true) {
			try {
				String msg = s.nextLine();
				out.write(msg + "\n");
				out.flush();
			} catch (Exception e) {
				System.err.println(e);
			}
		}
	}
}
