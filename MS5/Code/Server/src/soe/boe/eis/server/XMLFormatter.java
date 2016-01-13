package soe.boe.eis.server;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.thoughtworks.xstream.XStream;

public class XMLFormatter {

	private static DocumentBuilder db;

	/**
	 * Generates an UserObject out of an xml String
	 * 
	 * @param xmlCode
	 *            the xml String
	 * @return the gernerated User Object
	 * @throws Exception when the xml is not well formed
	 */
	public static UserObject getNewUserObject(String xmlCode) throws Exception {

		UserObject back = new UserObject("", "", 4);

		db = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		Document dom = db.parse(new ByteArrayInputStream(xmlCode.getBytes()));

		Element docEle = dom.getDocumentElement();

		NodeList nodes = docEle.getChildNodes();

		back.setEmail(nodes.item(0).getTextContent());
		back.setPassword(nodes.item(1).getTextContent());
		back.setUserGroup(Integer.parseInt(nodes.item(2).getTextContent()));

		return back;

	}

	/**
	 * Generates an UserObject out of an xml String
	 * 
	 * @param parkingPlaces
	 *            of the ParkingPlace Objects
	 * @return the gernerated String
	 */
	public static String generateParkingPlacesXMLOutOfParkingPlaceObjectArray(ParkingPlaceList parkingPlaces) {

		XStream xstream = new XStream();
		xstream.alias("parkingPlace", ParkingPlace.class);
		xstream.alias("list", ParkingPlaceList.class);
		xstream.alias("openingTimes", OpeningTimeObject.class);

		return xstream.toXML(parkingPlaces);

	}

}
