package soe.boe.eis.parkingplacesoftware.dummy;

import java.util.Scanner;

import soe.boe.eis.parkingplacesoftware.general.Enums;
import soe.boe.eis.parkingplacesoftware.general.NetworkConnection;

public class ParkingPlaceSoftware {

	private Scanner scanner;
	private NetworkConnection net;
	private boolean running = true;
    private String command = "";

	/**
	 * Constructor.
	 * 
	 * starts the network connection and the process
	 */
	public ParkingPlaceSoftware() {
		
		net = new NetworkConnection();

		start();
		
	}
	
	/**
	 * Constructor.
	 * 
	 * starts the network connection with specific data and the process
	 * 
	 * @param pServerAddress
	 *            the address of the server
	 * @param pPort
	 *            the port of the server
	 */
	public ParkingPlaceSoftware(String pServerAddress, int pPort) {
		
		net = new NetworkConnection(pServerAddress, pPort);
		
		start();
		
	}

	/**
	 * aks's for the login data and makes login process
	 */
	public void start() {
		
		scanner = new Scanner(System.in);
		
		if(!net.connectingServer()){
			
			System.out.println("Couldn't not connect to the server");
			
			System.exit(-1);
			
		} else {
			
			System.out.print("ID des Parkplatzes: ");
			String pParkingPlaceNumber = scanner.nextLine();

			System.out.print("Checkcode des Parkplatzes: ");
			String pCheckCode = scanner.nextLine();
			
			int iLoginResult = net.login(pParkingPlaceNumber, pCheckCode);
			
			switch (iLoginResult){
			case Enums.serverResponseWrongID:
				System.out.println("ID war falsch!");
				System.exit(0);
				break;
			case Enums.serverResponseWrongCheckCode:
				System.out.println("Checkcode war falsch!");
				System.exit(0);
				break;
			case Enums.serverResponseLoginCorrect:
				process();
			}
			
		}
		
	}

    /**
     * the process of the server dummy
     *
     */
    private void process() {

		while(running){
			
			printOutPossibleCommands();
			
			int iCommand = getCommand();
			
			switch (iCommand){
			case 1:
                oneLessFreeParkingPlace();
                break;
			case 2:
                oneMoreFreeParkingPlace();
				break;
			case 3:
                sendNumberOfFreeParkingPlaces();
                break;
			case 4:
				logoff();
				break;
			default:
				System.out.println("Wrong command - please try again");
			}
			
		}
		
	}
    
    /**
     * sends the information of how much free parking places are avaiable to the server, the number extracted from the command String value
     *
     */
    private void sendNumberOfFreeParkingPlaces() {
        
        String sNumber = command.substring(3);
        int number = 0;
        
        try {
            
            number = Integer.parseInt(sNumber);
            
            int response = net.sendNumberOfFreeParkingPlaces(number);
            
            switch (response) {
                case Enums.successfullSendNumberOfFreeParkingPlaces: System.out.println("The information of " + number + " free parking places was succesful sended");
                    break;
                    
                case Enums.failedToSendNumberOfFreeParkingPlaces: System.out.println("The information of " + number + " free parking places wasn't succesful sended");
                    break;
            }
            
        } catch (Exception e){
            
            e.printStackTrace();
            
            System.out.println("Dies war keine Nummer");
            
        }
        
    }
    
    /**
     * sends the information of one less free parking place to the server
     *
     */
    private void oneLessFreeParkingPlace() {
        
        int response = net.oneLessFreeParkingPlace();
        
        switch (response) {
            case Enums.successfullSendOneLessFreeParkingPlaces: System.out.println("The information of one less free parking place was succesful sended");
                break;
                
            case Enums.failedToSendOneLessFreeParkingPlaces: System.out.println("The information of one less free parking place wasn't succesful sended");
                break;
        }
        
    }
	
    /**
     * sends the information of one more free parking place to the server
     *
     */
    private void oneMoreFreeParkingPlace() {
        
        int response = net.oneMoreFreeParkingPlace();
        
        switch (response) {
            case Enums.successfullSendOneMoreFreeParkingPlaces: System.out.println("The information of one more free parking place was succesful sended");
                break;
                
            case Enums.failedToSendOneMoreFreeParkingPlaces: System.out.println("The information of one more free parking place wasn't succesful sended");
                break;
        }
        
    }

    /**
     * logs the parking place software off of the server
     *
     */
    private void logoff() {
		
		running = false;
		net.logoff();
		System.out.println("Goodbye");
		
	}

    /**
     * sends the information of one less free parking place to the server
     *
     * @return the integer value of the command
     *
     */
    private int getCommand() {
		
		System.out.print("Command: ");
		
		command = scanner.nextLine();
		
		String pCommand = command.substring(1, 2);
		
		int iCommand = 0;
		
		try {
			iCommand = Integer.parseInt(pCommand);
		} catch (Exception e){
			//nothing to do
		}
		
		return iCommand;
		
	}

	
    /**
     * prints out the possible commands with meaning
     *
     */
    private void printOutPossibleCommands() {
		
		System.out.println("One less free parking place - #1");
		System.out.println("One more free parking place - #2");
		System.out.println("This number of free parkingplaces - #3 [Integer]");
		System.out.println("EXIT - #4");
		
	}

	/**
     * the start method
     *
     * @param args all arguments are optional, the first argument is a the server address, the second the port. It is only possible to give 0 or two arguments
     */
    public static void main(String[] args){
		
		if (args.length == 0){
			new ParkingPlaceSoftware();
		} else if (args.length == 2) {
			new ParkingPlaceSoftware(args[0], Integer.parseInt(args[1]));
		} else {
			
			System.err.println("Wrong arguments");
			
			System.exit(-10);
			
		}
		
	}
	
}
