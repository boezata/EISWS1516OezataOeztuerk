package soe.boe.eis.thparkplatzapp;

public class DataTransfer {

	private static DataTransfer instance;
	private NetworkConnection net;
	private String email;
	private String password;

	public synchronized static DataTransfer getInstance() {
		
		if (instance == null){
			instance = new DataTransfer();
		}
		
		return instance;
		
	}

	public void setNetworkConnection(NetworkConnection pnet) {
		net = pnet;
	}

	public NetworkConnection getNetworkConnection() {
		return net;
	}

	public void setEmail(String pEmail) {
		email = pEmail;
	}
	
	public String getEmail(){
		return email;
	}

	public void setPassword(String pPassword) {
		password = pPassword;
	}
	
	public String getPassword(){
		return password;
	}

}
