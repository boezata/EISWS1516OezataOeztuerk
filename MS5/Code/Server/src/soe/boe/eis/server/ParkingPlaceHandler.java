package soe.boe.eis.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ParkingPlaceHandler extends Handler {

	private Socket client;
	private Stream conin;
	private PrintWriter conout;
	private boolean running = true;
	private DatabaseConnection dbconn;
	private int id;

	/**
	 * Constructor.
	 * 
	 * initilized the Strams, starts the new Thread
	 * 
	 * @param pClient
	 *            the socket
	 * @param pDbconn
	 *            the database connection
	 */
	public ParkingPlaceHandler(Socket pClient, DatabaseConnection pDbconn) {

		client = pClient;
		dbconn = pDbconn;

		try {

			// Streams erzeugen und Thread starten
			conin = new Stream(client.getInputStream());
			conout = new PrintWriter(client.getOutputStream(), true);

			this.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * starts the communication with the client with check of the id and
	 * reaction of this
	 */
	public void run() {

		// Implementation des Protokolls
		conout.println("Hello");

		try {

			conin.readLine();

			conout.println("#001");

			String sParkingPlaceNumber = conin.readLine();

			if (sParkingPlaceNumber.startsWith("#002")) {

				int parkingPlaceNumber = Integer.parseInt(sParkingPlaceNumber.substring(5));

				if (dbconn.parkingPlaceIDAvaiable(parkingPlaceNumber)) {
					loginProcess(parkingPlaceNumber);
				} else {

					conout.println("#004");

					logOff();

				}

			} else {

				conout.println("#012");
				conout.println("#013");

				logOff();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * check of the checksum and reaction of this
	 * 
	 * @param parkingPlaceNumber
	 *            the id of the parking place
	 * @throws IOException
	 *             when connection lost
	 */
	private void loginProcess(int parkingPlaceNumber) throws IOException {

		conout.println("#003");

		String sCheckCode = conin.readLine();

		if (sCheckCode.startsWith("#005")) {

			sCheckCode = sCheckCode.substring(5);

			if (dbconn.checkParkingPlaceSoftwareLogin(parkingPlaceNumber, sCheckCode)) {

				conout.println("#006");

				id = parkingPlaceNumber;

				process();

			} else {

				conout.println("#007");

				logOff();

			}

		} else {

			conout.println("#007");

			logOff();

		}

	}

	/**
	 * the communication with the client with logout, and manipulating of the
	 * number of free parking places
	 * 
	 * @throws IOException
	 *             when connection lost
	 */
	private void process() throws IOException {

		while (running) {

			String sCommand = conin.readLine();

			if (sCommand.startsWith("#013")) {

				running = false;

				conout.println("#013");

				logOff();

			} else if (sCommand.startsWith("#008")) {

				int response = dbconn.decreaseNumberOfFreeParkingPlaces(id);

				if (response == 1) {
					conout.println("#011");
				} else {
					conout.println("#012");
				}

			} else if (sCommand.startsWith("#009")) {

				int response = dbconn.increaseNumberOfFreeParkingPlaces(id);

				if (response == 1) {
					conout.println("#011");
				} else {
					conout.println("#012");
				}

			} else if (sCommand.startsWith("#010")) {

				sCommand = sCommand.substring(5);

				int pNumberOfFreeParkingPlaces = 0;

				try {

					pNumberOfFreeParkingPlaces = Integer.parseInt(sCommand);

					int response = dbconn.setValueOfFreePakingPlaces(id, pNumberOfFreeParkingPlaces);

					if (response == 1) {
						conout.println("#011");
					} else {
						conout.println("#012");
					}

				} catch (Exception e) {
					conout.println("#012");
				}

			} else {
				conout.println("#012");
			}

		}

	}

	/**
	 * closes the streams
	 * 
	 * @throws IOException
	 *             when something goes wrong
	 */
	private void logOff() throws IOException {

		conin.close();
		conout.close();

	}

}
