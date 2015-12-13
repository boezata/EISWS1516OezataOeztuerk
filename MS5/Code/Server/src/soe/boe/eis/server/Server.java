package soe.boe.eis.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {

	// Port des Servers
	private static int portForParkingPlaceClients = 10001;
	private static int portForAppClients = 10002;
	private InetAddress serverAddress;
	// Klasse zum Verbinden mit der Datenbank und zum Handeln der Aktivitäten
	private DatabaseConnection dbconn;
	private ParkingPlaceServer parkingPlaceServer;
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
		new Server();
	}

}
