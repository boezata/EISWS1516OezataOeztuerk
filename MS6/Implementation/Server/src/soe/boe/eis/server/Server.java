package soe.boe.eis.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {

	// Port des Servers
	private static int portForParkingPlaceClients = Settings.getInstance().getPortForParkingPlaceClients();
	private static int portForAppClients = Settings.getInstance().getPortForAppClients();
	private InetAddress serverAddress;
	// Klasse zum Verbinden mit der Datenbank und zum Handeln der Aktivitäten
	private DatabaseConnection dbconn;
	//Später gebraucht zur Effizienzsteigerung
	@SuppressWarnings("unused")
	private ParkingPlaceServer parkingPlaceServer;
	@SuppressWarnings("unused")
	private AppServer appServer;

	/**
	 * Constructor.
	 * 
	 * initilized and starts the server
	 */
	public Server() {

		startServer();

		runServer();

	}

	/**
	 * starts the two serverListener, stops the server if this isn't possible
	 */
	private void runServer() {

		try {
			// holen wir uns die Adresse des Servers (also unsere eigene)
			serverAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {

			// wenn nicht funktioniert, weinen und beenden
			e.printStackTrace();

			System.exit(-1);

		}

		appServer = new AppServer(serverAddress, portForAppClients, dbconn);
		parkingPlaceServer = new ParkingPlaceServer(serverAddress, portForParkingPlaceClients, dbconn);

	}

	/**
	 * connects the database, stops the server if this isn't possible
	 */
	private void startServer() {

		dbconn = new DatabaseConnection();

		if (!dbconn.connect()) {

			System.out.println("Konnte sich nicht mit Datenbank verbinden");

			System.exit(-3);

		}

	}

	/**
	 * the main method of the server
	 * 
	 * @param args
	 * 		the arguments given to server
	 */
	public static void main(String[] args) {
		
		if (args.length == 0){
			//nothing to do
		} else if (args.length == 0){
		
			Settings.getInstance().setDatabaseUserName(args[1]);
			Settings.getInstance().setDatabasePassword(args[2]);
			Settings.getInstance().setDatabaseLink(args[3]);
			Settings.getInstance().setSendingMailAddress(args[4]);
			Settings.getInstance().setMailServerUserName(args[5]);
			Settings.getInstance().setMailServerPassword(args[6]);
			Settings.getInstance().setWebsiteLink(args[7]);
			Settings.getInstance().setMailServerAddress(args[8]);
			Settings.getInstance().setMailServerPort(args[9]);
			Settings.getInstance().setPortForParkingPlaceClients(Integer.parseInt(args[10]));
			Settings.getInstance().setPortForAppClients(Integer.parseInt(args[11]));
		
		} else {
			
			System.err.println("Wrong arguments");
			
			System.exit(-10);
			
		}	
			
		new Server();
		
	}

}
