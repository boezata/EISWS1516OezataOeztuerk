package soe.boe.eis.thparkplatzapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class NetworkConnection {
	
	private String serverAddress = "system-systeme.de";
	private int serverPort = 10002;
	private Socket t;
	private PrintWriter out;
	private Stream in;
	
	public NetworkConnection(){
		//nothing to do
	}

	public int tryToLogin(String email, String password) {
		
		if (connect()){
			
			try {
			
				out.println("#002");
				
				if(in.readLine().startsWith("#009")){
					
					out.println("#010 " + email);
					
					if(in.readLine().startsWith("#011")){
						
						out.println("#013 " + password);
						
						if(in.readLine().startsWith("#014")){
							
							out.println("#018");

							disconnect();
							
							return 1; //Zugangsdaten korrekt 
							
						} else {

							disconnect();
							
							return 0; //Fehlerhafte Zugansdaten
							
						}
						
					} else {

						disconnect();
						
						return 0; //Fehlerhafte Zugansdaten
						
					}
					
				} else {

					disconnect();
					
					return 2; // Verbindungsfehler
					
				}

			} catch (IOException e) {
			
				e.printStackTrace();
				
				return 2; // Verbindungsfehler

			}
			
		} else {
			return 2; // Verbindungsfehler
		}
		
	}

	private boolean connect() {

		try {

			t = new Socket(serverAddress, serverPort);
			
			in = new Stream(t.getInputStream());
			out = new PrintWriter(t.getOutputStream(), true);
			
			in.readLine();
			out.println("Hello");
			
			//stating Login process
			in.readLine();
			
			return true;
			
		} catch (Exception e){
			
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	private void disconnect(){
		
		try {

			in.close();
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public int checkAvaiabiltyOfMailAdress(String emailadresse) {
		
		if (connect()){
			
			out.println("#003");
			
			try {

				in.readLine();
				
				out.println("#010 " + emailadresse);
				
				String pAnswer = in.readLine();
				
				int returnValue = 1;
				
				if (pAnswer.startsWith("#019")){
					returnValue = 2;
				}
				
				disconnect();
				
				return returnValue;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return 0;
				
			}
		} else {
			return 0;
		}
		
	}

	public int registrateNewUser(String choosenMail, String password, int userGroup) {

		if (connect()){
			
			out.println("#003");
			
			try {

				in.readLine();
				
				// TODO Hier Quelltext einfügen
			
				disconnect();
			
				return 1;
				
			} catch (IOException e) {
				
				e.printStackTrace();
				
				return 0;
				
			}
			
		} else {
			return 0;
		}
		
	}

	public int passwordForgottenProcess(String email) {

		if (connect()){
			
			try {
			
				in.readLine();
				out.println("#002");
				
				if(in.readLine().startsWith("#009")){
					
					/*out.println("#010 " + email);
					
					if(in.readLine().startsWith("#011")){
						
						out.println("#013 " + password);
						
						if(in.readLine().startsWith("#014")){
							
							out.println("#018");

							disconnect();*/
							
							return 1; //Zugansdaten korrekt 
							
						/*} else {

							disconnect();
							
							return 0; //Fehlerhafte Zugangsdaten
							
						}
						
					} else {

						disconnect();
						
						return 0; //Fehlerhafte Zugangsdaten
						
					}*/
					
				} else {

					disconnect();
					
					return 2; // Verbindungsfehler
					
				}

			} catch (IOException e) {
			
				e.printStackTrace();
				
				return 2; // Verbindungsfehler

			}
			
		} else {
			return 2; // Verbindungsfehler
		}
		
	}

	public String getParkingPlaceList(String email, String password) {
		
		if (connect()){
			
			try {
			
				out.println("#002");
				
				if(in.readLine().startsWith("#009")){
					
					out.println("#010 " + email);
					
					if(in.readLine().startsWith("#011")){
						
						out.println("#013 " + password);
						
						if(in.readLine().startsWith("#014")){
							
							out.println("#016");
							
							String back = "";
							String sback = "";
							
							while(!sback.equals("#-017")){
								
								if (!sback.equals("")){
									if (!back.equals("")){
										back = back + "\r\n" + sback;
									} else {
										back = sback;
									}
								}
								
								sback = in.readLine();
								
							}
							
							out.println("#018");

							disconnect();
							
							return back.substring(5); //Zugangsdaten korrekt 
							
						} else {

							disconnect();
							
							return "ERROR"; //Fehlerhafte Zugansdaten
							
						}
						
					} else {

						disconnect();
						
						return "ERROR"; //Fehlerhafte Zugansdaten
						
					}
					
				} else {

					disconnect();
					
					return "ERROR"; // Verbindungsfehler
					
				}

			} catch (IOException e) {
			
				e.printStackTrace();
				
				return "ERROR"; // Verbindungsfehler

			}
			
		} else {
			return "ERROR"; // Verbindungsfehler
		}
		
	}

}
