package lectura;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class LeerXML {

	public static void main(String[] args) {
	DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
	DocumentBuilder db;
	Document document;
	File file;
	XPath xPath; 
	NodeList nodeList;
	String path;
	
	try {
		db = dbf.newDocumentBuilder();
		file = new File("personas.xml");
		document = db.parse(file);
		
		document.normalizeDocument();
		
		xPath = XPathFactory.newInstance().newXPath();
		
		//la consulta
		path = "//apellido";
		
		//los nodos resultantes de la consulta
		nodeList = (NodeList) xPath.compile(path).evaluate(document,XPathConstants.NODESET);		
		
		for (int i = 0;i< nodeList.getLength();i++) {
			Element elemento  = (Element) nodeList.item(i);
			
			System.out.println("ID: "+elemento.getElementsByTagName("id").item(0).getTextContent());
			System.out.println("Nombre: "+elemento.getElementsByTagName("Nombre").item(0).getTextContent());
			System.out.println("Especie:"+elemento.getElementsByTagName("Especie").item(0).getTextContent());
			System.out.println("Sexo: "+elemento.getElementsByTagName("Sexo").item(0).getTextContent());
			System.out.println("Edad: "+elemento.getElementsByTagName("Edad").item(0).getTextContent());
			System.out.println("Img: "+elemento.getElementsByTagName("img").item(0).getTextContent());


			
		}
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	
		

	}

}
