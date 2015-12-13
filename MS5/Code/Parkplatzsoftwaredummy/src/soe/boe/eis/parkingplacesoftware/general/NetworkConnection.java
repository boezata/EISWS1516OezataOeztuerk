package soe.boe.eis.parkingplacesoftware.general;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkConnection {

	// the adress of the server
    private String ipAddress = "system-systeme.de";
    // the port of the server
	private int port = 10001;
	private Socket t;
	private Stream conin;
	private PrintWriter conout;

	public NetworkConnection(){
		//nothing to do
	}

    /**
     * trys to connect the server and gives feedback
     *
     *
     * @return <tt>true</tt> only if the server was connected successful
     */
    public boolean connectingServer() {
		
		try {

			t = new Socket(ipAddress, port);
			conin = new Stream(t.getInputStream());
			conout = new PrintWriter(t.getOutputStream(), true);
			
			conin.readLine();
			conout.println("Hello");
			conin.readLine();

			return true;
			 
		} catch (IOException e) {
			return false;
		}
		
	}

	public int login(String pParkingPlaceNumber, String pCheckCode) {
		
		try {
			
			conout.println("#002 " + pParkingPlaceNumber);
			
			String pAnswer = conin.readLine();
			
			if(pAnswer.startsWith("#003")){
				
				conout.println("#005 " + pCheckCode);
				
				pAnswer = conin.readLine();
				
				if(!pAnswer.startsWith("#006")){
					return 2;
				} 
				
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return 1;
			
		}
		
		return 0;
		
	}


	public void logoff() {
	
		conout.println("#13");
		
		try {
			
			conin.readLine();
			
			conout.close();
			
			conin.close();
			
		} catch (Exception e){
			// nothing to do
		}
		
	}

	public int sendNumberOfFreeParkingPlaces(int number) {
		
		conout.println("#010 " + number);
		
		try {
			
			String response = conin.readLine();
			
			if (response.startsWith("#011")) {
				return 1;
			}
			
			return 2;
			
		} catch (Exception e){
			return 2;
		}
		
	}

	public int oneLessFreeParkingPlace() {

		conout.println("#008");
		
		try {
			
			String response = conin.readLine();
			
			if (response.startsWith("#011")) {
				return 1;
			}
			
			return 2;
			
		} catch (Exception e){
			return 2;
		}
		
	}

	public int oneMoreFreeParkingPlace() {

		conout.println("#009");
		
		try {
			
			String response = conin.readLine();
			
			if (response.startsWith("#011")) {
				return 1;
			}
			
			return 2;
			
		} catch (Exception e){
			return 2;
		}
		
	}
	
}
