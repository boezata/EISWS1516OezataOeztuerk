package soe.boe.eis.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ParkingPlaceServer extends Thread {

	private int port;
	// private InetAddress serverAddress;
	private ServerSocket listener;
	private boolean run = true;
	private Handler firstHandler;
	private DatabaseConnection dbconn;

	/**
	 * Constructor.
	 * 
	 * starts the listener, initlized the firstHandler
	 *
	 * @param pServerAddress
	 *            the InetAddress for the listener
	 * @param pPort
	 *            the port for the listener
	 * @param pDbconn
	 *            the initilized databaseConnection
	 */
	public ParkingPlaceServer(InetAddress pServerAddress, int pPort, DatabaseConnection pDbconn) {

		dbconn = pDbconn;
		port = pPort;
		// serverAddress = pServerAddress;

		// erster Handler um den Anfang der Reihe zu definieren
		firstHandler = new Handler();

		start();

	}

	/**
	 * activats and controlls the listener
	 */
	public void run() {

		try {

			System.out.println("Parking Place Server started");

			// Abhörer erstellen
			listener = new ServerSocket(port);

			while (run) {

				// jedes neue Gerät ein neuer Socket
				Socket client = listener.accept();

				System.out.println("New parking place client noticed " + client.getInetAddress().getHostName());

				// pro neuem Gerät ein neuer Handler erzeugt
				ParkingPlaceHandler pHandler = new ParkingPlaceHandler(client, dbconn);

				// Handler ans Ende der Liste
				addHandlerToLastHandler(pHandler);

			}

		} catch (IOException e) {

			// wenn nicht funktioniert, weinen und beenden
			e.printStackTrace();

			System.exit(-2);

		}

	}

	/**
	 * adds the handler at the end of the queue
	 * 
	 * @param pHandler
	 *            the handler to add
	 */
	private void addHandlerToLastHandler(Handler pHandler) {

		Handler actualHandler = firstHandler;

		// Liste bis zum Ende durch
		while (actualHandler.hasFollower()) {
			actualHandler = actualHandler.getFollower();
		}

		actualHandler.setFollower(pHandler);

	}

}
