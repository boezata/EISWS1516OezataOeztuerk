package soe.boe.eis.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AppHandler extends Handler {

	private Socket client;
	private Stream conin;
	private PrintWriter conout;
	private DatabaseConnection dbconn;
	private boolean running = true;

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
	public AppHandler(Socket pClient, DatabaseConnection pDbconn) {

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
	 * starts the conversation to the client and decides the kind of process
	 */
	public void run() {

		// Implementation des Protokolls
		conout.println("Hello");

		try {

			conin.readLine();

			conout.println("#001");

			String sDecision = conin.readLine();

			if (sDecision.startsWith("#002")) {
				loginAndWorkProcess();
			} else {
				registrationProcess();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * starts the registration process checks the avaiabilty of the mail address
	 * and saves it
	 * 
	 * @throws IOException
	 *             when connection lost
	 */
	private void registrationProcess() throws IOException {

		conout.println("#004");

		String pAnswer = conin.readLine();

		if (pAnswer.startsWith("#005")) {

			try {

				if (dbconn.saveNewUser(XMLFormatter.getNewUserObject(pAnswer.substring(5)))) {
					conout.println("#007");
				} else {
					conout.println("#006");
				}

			} catch (Exception e) {

				e.printStackTrace();

				conout.println("#020");

			}

		} else if (pAnswer.startsWith("#010")) {

			if (dbconn.checkAvaiablityOfMailAdress(pAnswer.substring(5))) {
				conout.println("#019");
			} else {
				conout.println("#006");
			}

		} else {
			conout.println("#008");
		}

		logOff();

	}

	/**
	 * starts the work process checks the login datas and reacts on this. Also
	 * required for the forgot password function
	 */
	private void loginAndWorkProcess() {

		conout.println("#009");

		try {

			String mailAddress = conin.readLine();

			if (mailAddress.startsWith("#010")) {

				if (dbconn.checkAvaiablityOfUser(mailAddress.substring(5))) {

					conout.println("#011");

					String password = conin.readLine();

					if (password.startsWith("#013")) {

						if (dbconn.checkUserWithPassword(mailAddress.substring(5), password.substring(5))) {

							conout.println("#014");

							process(mailAddress);

						} else {

							conout.println("#015");

							logOff();

						}

					} else if (password.startsWith("#021")) {

						MailController.sendFergotPasswordMail(mailAddress.substring(5),
								dbconn.getSecCodeForUser(mailAddress.substring(5)));

						conout.println("#022");

						logOff();

					} else {

						conout.println("#008");

						logOff();

					}

				} else {

					conout.println("#012");

					logOff();

				}

			} else {

				conout.println("#008");

				logOff();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * the work process with sending parking Place list and logout
	 * 
	 * @param mailAddress
	 *            the mail address of the user
	 * @throws IOException
	 *             when connection lost
	 */
	private void process(String mailAddress) throws IOException {

		while (running) {

			String command = conin.readLine();

			if (command.startsWith("#016")) {

				int userGroup = dbconn.getUserGroupForEMail(mailAddress.substring(5));

				ParkingPlaceList parkingPlaces = dbconn.getParkingPlacesArrayForUserGroup(userGroup);

				conout.println(
						"#017 " + XMLFormatter.generateParkingPlacesXMLOutOfParkingPlaceObjectArray(parkingPlaces));

				conout.println("#-017");

			} else if (command.startsWith("#018")) {

				conout.println("#018");

				running = false;

				logOff();

			} else {
				conout.println("#008");
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
