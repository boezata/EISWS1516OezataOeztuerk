package soe.boe.eis.server;

public class Settings {

	private static Settings instance;
	private String databaseUserName = "eis";
	private String databasePassword = "Schinkenwurst";
	private String databaseLink = "jdbc:mysql://localhost/eis";
	private String sendingMailAddress = "eis@system-systeme.de";
	private String mailServerUserName = "eis";
	private String mailServerPassword = "Schinkenwurst";
	private String websiteLink = "http://system-systeme.de/projects/eis/";
	private String mailServerAddress = "mail.system-systeme.de";
	private String mailServerPort = "25";
	private int portForParkingPlaceClients = 10001;
	private int portForAppClients = 10002;
	
	/**
	 * creates a new object of it wasn't, returns it or the earlier created one
	 * 
	 * @return the object
	 */
	public synchronized static Settings getInstance() {
		
		if (instance == null){
			instance = new Settings();
		}
		
		return instance;
		
	}

	/**
	 * sets the user name for the database
	 * 
	 * @param pDatabaseUserName
	 *            the user name for the database
	 */
	public void setDatabaseUserName(String pDatabaseUserName) {
		databaseUserName = pDatabaseUserName;
	}

	/**
	 * gets the user name for the database
	 * 
	 * @return the user name for the database
	 */
	public String getDatabaseUserName() {
		return databaseUserName;
	}

	/**
	 * sets the password for the database
	 * 
	 * @param pDatabasePassword
	 *            the password for the database
	 */
	public void setDatabasePassword(String pDatabasePassword) {
		databasePassword = pDatabasePassword;
	}

	/**
	 * gets the password for the database
	 * 
	 * @return the password for the database
	 */
	public String getDatabasePassword() {
		return databasePassword ;
	}

	/**
	 * sets the link for the database
	 * 
	 * @param pDatabaseLink
	 *            the link for the database
	 */
	public void setDatabaseLink(String pDatabaseLink) {
		databaseLink = pDatabaseLink;
	}

	/**
	 * gets the link for the database
	 * 
	 * @return the link for the database
	 */
	public String getDatabaseLink() {
		return databaseLink ;
	}

	/**
	 * sets the sender mail address
	 * 
	 * @param pSendingMailAddress
	 *            the sender mail address
	 */
	public void setSendingMailAddress(String pSendingMailAddress) {
		sendingMailAddress = pSendingMailAddress;
	}

	/**
	 * gets the sender mail address
	 * 
	 * @return the sender mail address
	 */
	public String getSendingMailAddress() {
		return sendingMailAddress ;
	}

	/**
	 * sets the user for the mail server login
	 * 
	 * @param pMailServerUserName
	 *            the user for the mail server login
	 */
	public void setMailServerUserName(String pMailServerUserName) {
		mailServerUserName = pMailServerUserName;
	}

	/**
	 * gets the user for the mail server login
	 * 
	 * @return the user for the mail server login
	 */
	public String getMailServerUserName() {
		return mailServerUserName;
	}

	/**
	 * sets the password for the mail server login
	 * 
	 * @param pMailServerPassword
	 *            the password for the mail server login
	 */
	public void setMailServerPassword(String pMailServerPassword) {
		mailServerPassword = pMailServerPassword;
	}

	/**
	 * gets the password for the mail server login
	 * 
	 * @return the password for the mail server login
	 */
	public String getMailServerPassword() {
		return mailServerPassword;
	}

	/**
	 * sets the link to the website
	 * 
	 * @param pWebsiteLink
	 *            the link to the website
	 */
	public void setWebsiteLink(String pWebsiteLink) {
		websiteLink = pWebsiteLink;
	}

	/**
	 * gets the link to the website
	 * 
	 * @return the link to the website
	 */
	public String getWebsiteLink() {
		return websiteLink ;
	}

	/**
	 * sets the address for the mail server login
	 * 
	 * @param pMailServerAddress
	 *            the address for the mail server login
	 */
	public void setMailServerAddress(String pMailServerAddress) {
		mailServerAddress = pMailServerAddress;
	}

	/**
	 * gets the address for the mail server login
	 * 
	 * @return the address for the mail server login
	 */
	public String getMailServerAddress() {
		return mailServerAddress ;
	}

	/**
	 * sets the port for the mail server login
	 * 
	 * @param pMailServerPort
	 *            the port for the mail server login
	 */
	public void setMailServerPort(String pMailServerPort) {
		mailServerPort = pMailServerPort;
	}

	/**
	 * gets the port for the mail server login
	 * 
	 * @return the port for the mail server login
	 */
	public String getMailServerPort() {
		return mailServerPort ;
	}

	/**
	 * sets the port for the parking place handler
	 * 
	 * @param pPortForParkingPlaceClients
	 *            the port for the parking place handler
	 */
	public void setPortForParkingPlaceClients(int pPortForParkingPlaceClients) {
		portForParkingPlaceClients = pPortForParkingPlaceClients;
	}

	/**
	 * gets the port for the parking place handler
	 * 
	 * @return the port for the parking place handler
	 */
	public int getPortForParkingPlaceClients() {
		return portForParkingPlaceClients ;
	}

	/**
	 * sets the port for the app handler
	 * 
	 * @param pPortForAppClients
	 *            the port for the app handler
	 */
	public void setPortForAppClients(int pPortForAppClients) {
		portForAppClients = pPortForAppClients;
	}
	
	/**
	 * gets the port for the app handler
	 * 
	 * @return the port for the app handler
	 */
	public int getPortForAppClients() {
		return portForAppClients ;
	}
	
}
