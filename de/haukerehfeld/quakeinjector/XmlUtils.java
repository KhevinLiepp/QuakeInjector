package de.haukerehfeld.quakeinjector;

import javax.xml.parsers.*;
import org.w3c.dom.*;

import java.io.*;

import java.lang.RuntimeException;

public class XmlUtils {
	/**
	 * get the first element in the parent element that's named name or null if no such element exists
	 */
	public static Element getFirstElement(Element parent, String name) {
		NodeList list = parent.getElementsByTagName(name);
		
		Node node = list.item(0);
		if (node == null) {
			//throw new RuntimeException("Malformed XML: No such node: " + name);
			return null;
		}
		
		if (node.getNodeType() != Node.ELEMENT_NODE) {
			throw new RuntimeException("XML Parsing error: " + name + " is not an Element");
		}

		return (Element) node;
	}

	public static Document getDocument(String file)
		throws java.io.IOException,
		       org.xml.sax.SAXException {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
	 
			Document doc = db.parse(file);
			return doc;
		}
		catch (javax.xml.parsers.ParserConfigurationException e) {
			return null;
		}
	}
	
}