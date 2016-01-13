package soe.boe.eis.parkingplacesoftware.dummy;

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

    /**
     * trys to connect the server and gives feedback
     *
     * @param pParkingPlaceNumber   ID des Parkplatzes
     * @param pCheckCode            Checksumme des Parkplatz
     *
     * @return <tt>0</tt> wenn erfolgreich, <tt>1</tt> wenn Verbindung fehlschlägt, <tt>2</tt> wenn erfolgreich
     */
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


    /**
     * logs the software off of the server
     *
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
	
}
