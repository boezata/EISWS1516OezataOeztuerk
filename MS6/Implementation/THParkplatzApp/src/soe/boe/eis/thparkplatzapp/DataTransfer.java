package soe.boe.eis.thparkplatzapp;

public class DataTransfer {

	private static DataTransfer instance;
	private NetworkConnection net;
	private String email;
	private String password;

	/**
	 * creates the object if it's required and returns it
	 * 
	 * @return the object
	 */
	public synchronized static DataTransfer getInstance() {
		
		if (instance == null){
			instance = new DataTransfer();
		}
		
		return instance;
		
	}

	/**
	 * sets the NetworkConnection
	 * 
	 * @param pnet
	 *            the NetworkConnection
	 */
	public void setNetworkConnection(NetworkConnection pnet) {
		net = pnet;
	}

	/**
	 * gets the NetworkConnection
	 * 
	 * @return the NetworkConnection
	 */
	public NetworkConnection getNetworkConnection() {
		return net;
	}

	/**
	 * sets the email for login
	 * 
	 * @param pEmail
	 *            the email for login
	 */
	public void setEmail(String pEmail) {
		email = pEmail;
	}
	
	/**
	 * gets the email for login
	 * 
	 * @return the email for login
	 */
	public String getEmail(){
		return email;
	}

	/**
	 * sets the password for login
	 * 
	 * @param pPassword
	 *            the password for login
	 */
	public void setPassword(String pPassword) {
		password = pPassword;
	}
	
	/**
	 * gets the password for login
	 * 
	 * @return the password for login
	 */
	public String getPassword(){
		return password;
	}

}
