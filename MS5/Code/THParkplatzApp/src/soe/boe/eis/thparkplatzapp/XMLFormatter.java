package soe.boe.eis.thparkplatzapp;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XMLFormatter {

	public static ParkingPlaceList generateNewParkingPlaceObjectArray(String xml) {

		xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n" + xml;
		
		try {

			// TODO Hier Quelltext einfügen
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			
			Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));
			
			System.out.println(doc.);
			
			NodeList nodes = doc.getChildNodes();
			
			NodeList nodes2 = nodes.item(0).getChildNodes();
			
			System.out.println(nodes2.getLength());
			System.out.println(nodes2.item(0).getNodeName());
			System.out.println(nodes2.item(1).getNodeName());
			System.out.println(nodes2.item(2).getNodeName());
			
			for (int i = 0; i < nodes2.getLength(); i++){
				
				
				
			}
					
			return null;

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
