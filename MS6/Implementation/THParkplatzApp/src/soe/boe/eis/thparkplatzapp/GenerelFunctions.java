package soe.boe.eis.thparkplatzapp;

import java.sql.Time;

public class GenerelFunctions {

	/**
	 * converts a time string out of xml to an Time Object
	 * 
	 * @param textContent
	 * 			a string with a time
	 * @return the time object
	 */
	@SuppressWarnings("deprecation")
	public static Time generateTimeObjectOutOfXMLTimeString(String textContent) {
		
		String textContentParts[] = textContent.split(":");
		
		return new Time(Integer.parseInt(textContentParts[0]), Integer.parseInt(textContentParts[1]), Integer.parseInt(textContentParts[2]));
		
	}

}
