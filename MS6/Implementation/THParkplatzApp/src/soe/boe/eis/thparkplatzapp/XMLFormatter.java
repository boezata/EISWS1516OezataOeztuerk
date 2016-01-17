package soe.boe.eis.thparkplatzapp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLFormatter {

	/**
	 * generates out of the xml string a list of parking place objects
	 * 
	 * @param xml
	 *            the xml string
	 * 
	 * @return the list with the parking places
	 */
	public static ParkingPlaceList generateNewParkingPlaceObjectArray(String xml) {

		xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + xml;

		try {

			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

			NodeList nodes = doc.getChildNodes();

			NodeList nodes2 = nodes.item(0).getChildNodes();

			NodeList nodes3 = nodes2.item(1).getChildNodes();

			List<Node> parkingPlaceNodeList = new ArrayList<Node>();

			for (int i = 0; i < nodes3.getLength(); i++) {

				if (nodes3.item(i).getNodeName().equals("parkingPlace")) {
					parkingPlaceNodeList.add(nodes3.item(i));
				}

			}

			List<ParkingPlace> back = new ArrayList<ParkingPlace>();

			for (int i = 0; i < parkingPlaceNodeList.size(); i++) {

				Node parkingPlaceNode = parkingPlaceNodeList.get(i);

				NodeList parkingPlaceNodeNodeList = parkingPlaceNode.getChildNodes();

				NodeList openingTimesNodeList = parkingPlaceNodeNodeList.item(23).getChildNodes();

				OpeningTimeObject openingTimes = new OpeningTimeObject(
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(1).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(3).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(5).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(7).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(9).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(11).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(13).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(15).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(17).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(19).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(21).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(23).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(25).getTextContent()),
						GenerelFunctions
								.generateTimeObjectOutOfXMLTimeString(openingTimesNodeList.item(27).getTextContent()));

				ParkingPlace onePlace = new ParkingPlace(
						Integer.parseInt(parkingPlaceNodeNodeList.item(1).getTextContent()),
						Integer.parseInt(parkingPlaceNodeNodeList.item(3).getTextContent()),
						Integer.parseInt(parkingPlaceNodeNodeList.item(5).getTextContent()),
						Double.parseDouble(parkingPlaceNodeNodeList.item(7).getTextContent()),
						Double.parseDouble(parkingPlaceNodeNodeList.item(9).getTextContent()),
						parkingPlaceNodeNodeList.item(11).getTextContent(),
						Double.parseDouble(parkingPlaceNodeNodeList.item(13).getTextContent()),
						parkingPlaceNodeNodeList.item(15).getTextContent(),
						parkingPlaceNodeNodeList.item(17).getTextContent(),
						Integer.parseInt(parkingPlaceNodeNodeList.item(19).getTextContent()),
						parkingPlaceNodeNodeList.item(21).getTextContent(), openingTimes,
						GenerelFunctions.generateTimeObjectOutOfXMLTimeString(
								parkingPlaceNodeNodeList.item(25).getTextContent()),
						Double.parseDouble(parkingPlaceNodeNodeList.item(27).getTextContent()),
						Double.parseDouble(parkingPlaceNodeNodeList.item(29).getTextContent()),
						Boolean.parseBoolean(parkingPlaceNodeNodeList.item(31).getTextContent()),
						Boolean.parseBoolean(parkingPlaceNodeNodeList.item(33).getTextContent()),
						Boolean.parseBoolean(parkingPlaceNodeNodeList.item(35).getTextContent()),
						Boolean.parseBoolean(parkingPlaceNodeNodeList.item(37).getTextContent()),
						Boolean.parseBoolean(parkingPlaceNodeNodeList.item(39).getTextContent()),
						Boolean.parseBoolean(parkingPlaceNodeNodeList.item(41).getTextContent()));

				back.add(onePlace);

			}

			return new ParkingPlaceList(back);

		} catch (ParserConfigurationException e) {

			e.printStackTrace();

			return null;

		} catch (SAXException e) {

			e.printStackTrace();

			return null;

		} catch (IOException e) {

			e.printStackTrace();

			return null;

		}

	}

}
