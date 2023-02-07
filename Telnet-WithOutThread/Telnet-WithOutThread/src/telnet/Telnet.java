package telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Telnet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String host;
		int port;
		
		if(args.length >= 2) {
			host=args[0];
			port =Integer.parseInt(args[1]);
			
		}else {
			System.out.println("I NEED TWO ARGUMENTS(HOST) AND (PORT)!!! ");
			return;
		}
		
		try {
			
			Socket s = new Socket(host, port);
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			String input;
			String quit = "";
			System.out.println("press q to quit");
			Scanner sc = new Scanner(System.in);
			byte[] byteArr = new byte[1000];
			while(is.available() >= 0) {
				if(System.in.available() > 0) {
					quit = sc.nextLine() + "\n";
					if(quit.equals("QUIT\n")) {
						System.out.println("you have exited");
						break;
					}
					os.write(quit.getBytes());
					os.flush();
				}
				if(is.available() > 0) {					
					int num = is.read(byteArr);
					System.out.write(byteArr, 0, num);
				}		
				Thread.sleep(100);
			}
			System.out.println("the server has closed the connetion");
			s.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		

	}

}
