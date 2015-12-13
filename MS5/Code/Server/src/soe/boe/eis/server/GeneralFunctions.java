package soe.boe.eis.server;

import java.util.Random;

public class GeneralFunctions {

	/**
	 * Generates a 20 char long random code
	 * 
	 * @return the code
	 */
	public static String generateNewSecCode() {
		
		char[] cABack = new char[20];
		
		Random random = new Random();
		
		for (int i = 0; i < 20; i++){
			cABack[i] = (char) ((char) 'a' + random.nextInt(26));
		}
		
		return new String(cABack);
		
	}

}
