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

	/**
	 * Constructor.
	 * 
	 * nothing to do here
	 */
	public NetworkConnection(){
		//nothing to do
	}

    /**
	 * Constructor.
	 * 
	 * replaces the address and the port
	 * 
	 * @param pIpAddress
	 *            the address of the server
	 * @param pPort
	 *            the port of the server
	 */
	public NetworkConnection(String pIpAddress, int pPort){
	
		ipAddress = pIpAddress;
		port = pPort;
	
	}

    /**
     * trys to connect the server and gives feedback
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

	/**
	 * Checks wheather an parkingPlaceID with the checkcode is avaiable
	 * 
	 * @param pParkingPlaceNumber
	 *            the id of the parking place
	 * @param pCheckCode
	 *            the checksum of the parking place
	 * @return <tt>Enums.serverResponseWrongCheckCode</tt> only if the checkcode ist wrong but the id is correct<br>
	 * 		   <tt>Enums.serverResponseWrongID</tt> only if the id is wrong<br>
	 * 		   <tt>Enums.serverConnectionLost</tt> only if the connection is not avaiable<br>
	 * 		   <tt>Enums.serverResponseLoginCorrect</tt> only if the process was successfull
	 * 
	 */
	public int login(String pParkingPlaceNumber, String pCheckCode) {
		
		try {
			
			conout.println("#002 " + pParkingPlaceNumber);
			
			String pAnswer = conin.readLine();
			
			if(pAnswer.startsWith("#003")){
				
				conout.println("#005 " + pCheckCode);
				
				pAnswer = conin.readLine();
				
				if(!pAnswer.startsWith("#006")){
					return Enums.serverResponseWrongCheckCode;
				} 
				
			} else {
				return Enums.serverResponseWrongID;
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
			return Enums.serverConnectionLost;
			
		}
		
		return Enums.serverResponseLoginCorrect;
		
	}


	/**
	 * sends the logoff code closes the Streams
	 */
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

	/**
	 * sends the number of the free parking places to the server
	 * 
	 * @param number
	 *            the number of free parking palaces
	 * 
	 * @return <tt>Enums.successfullSendNumberOfFreeParkingPlaces</tt> only if the process was successfull<br>
	 * 		   <tt>Enums.failedToSendNumberOfFreeParkingPlaces</tt> only if the process failed
	 * 
	 */
	public int sendNumberOfFreeParkingPlaces(int number) {
		
		conout.println("#010 " + number);
		
		try {
			
			String response = conin.readLine();
			
			if (response.startsWith("#011")) {
				return Enums.successfullSendNumberOfFreeParkingPlaces;
			}
			
			return Enums.failedToSendNumberOfFreeParkingPlaces;
			
		} catch (Exception e){
			return Enums.failedToSendNumberOfFreeParkingPlaces;
		}
		
	}

	/**
	 * sends the command for decreasing the number of free parking places
	 * 
	 * @return <tt>Enums.successfullSendOneLessFreeParkingPlaces</tt> only if the process was successfull<br>
	 * 		   <tt>Enums.failedToSendOneLessFreeParkingPlaces</tt> only if the process failed
	 * 
	 */
	public int oneLessFreeParkingPlace() {

		conout.println("#008");
		
		try {
			
			String response = conin.readLine();
			
			if (response.startsWith("#011")) {
				return Enums.successfullSendOneLessFreeParkingPlaces;
			}
			
			return Enums.failedToSendOneLessFreeParkingPlaces;
			
		} catch (Exception e){
			return Enums.failedToSendOneLessFreeParkingPlaces;
		}
		
	}

	/**
	 * sends the command for increasing the number of free parking places
	 * 
	 * @return <tt>Enums.successfullSendOneMoreFreeParkingPlaces</tt> only if the process was successfull<br>
	 * 		   <tt>Enums.failedToSendOneMoreFreeParkingPlaces</tt> only if the process failed
	 * 
	 */
	public int oneMoreFreeParkingPlace() {

		conout.println("#009");
		
		try {
			
			String response = conin.readLine();
			
			if (response.startsWith("#011")) {
				return Enums.successfullSendOneMoreFreeParkingPlaces;
			}
			
			return Enums.failedToSendOneMoreFreeParkingPlaces;
			
		} catch (Exception e){
			return Enums.failedToSendOneMoreFreeParkingPlaces;
		}
		
	}
	
}
