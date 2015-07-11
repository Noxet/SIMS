
import java.net.*;
import java.io.*;
import java.util.*;

class IMClient {
	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: java IMClient <host>");
			System.exit(1);
		}

		String host = args[0];
		Socket socket = null;
		try {
			socket = new Socket(host, 1337);

			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			OThread ot = new OThread(out);
			ot.start();
			IThread it = new IThread(in);		
			it.start();

		} catch (Exception e) {
			System.err.println(e);
			try {
				socket.close();
			} catch (IOException ex) {}
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
		try {
			while (true) {
				System.out.println("-- " + in.readLine());
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				in.close();
				System.out.println("[IThread]: in closed");
			} catch (Exception ex) {}
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
		try {
			while (true) {
				String msg = s.nextLine();
				out.write(msg + "\n");
				out.flush();
			}
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			try {
				out.close();
				System.out.println("[OThread]: out closed");
			} catch (Exception ex) {}
		}
	}
}
