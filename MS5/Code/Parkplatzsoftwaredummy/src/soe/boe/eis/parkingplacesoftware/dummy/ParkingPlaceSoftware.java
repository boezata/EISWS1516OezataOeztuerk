package soe.boe.eis.parkingplacesoftware.dummy;

import java.util.Scanner;

import soe.boe.eis.parkingplacesoftware.general.NetworkConnection;

public class ParkingPlaceSoftware {

	private Scanner scanner;
	private NetworkConnection net;
	private boolean running = true;
    private String command = "";

	public ParkingPlaceSoftware() {
		
		scanner = new Scanner(System.in);
		
		net = new NetworkConnection();
		
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
			case 1:
				System.out.println("ID war falsch!");
				System.exit(0);
				break;
			case 2:
				System.out.println("Checkcode war falsch!");
				System.exit(0);
				break;
			case 0:
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
                case 1: System.out.println("The information of " + number + " free parking places was succesful sended");
                    break;
                    
                case 2: System.out.println("The information of " + number + " free parking places wasn't succesful sended");
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
            case 1: System.out.println("The information of one less free parking place was succesful sended");
                break;
                
            case 2: System.out.println("The information of one less free parking place wasn't succesful sended");
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
            case 1: System.out.println("The information of one more free parking place was succesful sended");
                break;
                
            case 2: System.out.println("The information of one more free parking place wasn't succesful sended");
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
		
		command = command.substring(1, 2);
		
		int iCommand = 0;
		
		try {
			iCommand = Integer.parseInt(command);
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

	public static void main(String[] args){
		new ParkingPlaceSoftware();
	}
	
}
