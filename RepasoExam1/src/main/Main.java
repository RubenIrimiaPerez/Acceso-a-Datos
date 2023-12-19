package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

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
import org.xml.sax.SAXException;


public class Main {

	public static void main(String[] args) {

		File archivo = new File("mascotas.xml");
	
		ArrayList<Mascota> arrayMascota = new ArrayList<Mascota>();
		ArrayList<Cita> arrayHistoria = new ArrayList<Cita>();
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder db;
		Document document;

		XPath xPath; // para buscar dentro del archivo XML
		NodeList nodeList;
		String path;

		try {
			db = dbf.newDocumentBuilder();
			document = db.parse(archivo);
			document.normalizeDocument();
			xPath = XPathFactory.newInstance().newXPath();

			path = "//mascota";
			// consulta y nodos de la consulta
			nodeList = (NodeList) xPath.compile(path).evaluate(document, XPathConstants.NODESET);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element elemento = (Element) nodeList.item(i);
				String id = elemento.getElementsByTagName("id").item(0).getTextContent();
				String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
				String especie = elemento.getElementsByTagName("especie").item(0).getTextContent();
				String sexo = elemento.getElementsByTagName("sexo").item(0).getTextContent();
				String edad = elemento.getElementsByTagName("edad").item(0).getTextContent();

				// Obtener la lista de citas para la mascota actual
				NodeList citasNodeList = elemento.getElementsByTagName("cita");
				ArrayList<Cita> historial = new ArrayList<Cita>();
				Cita cita;
				for (int j = 0; j < citasNodeList.getLength(); j++) {
					Element elementoCitas = (Element) citasNodeList.item(j);
					String fecha = elementoCitas.getElementsByTagName("fecha").item(0).getTextContent();
					String motivo = elementoCitas.getElementsByTagName("motivo").item(0).getTextContent();
					cita = new Cita(fecha, motivo);
					historial.add(cita);
				}
				conversorDatos(id, nombre, especie, sexo, edad, historial, arrayMascota);
				
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// PINTAMOS LAS MASCOTAS
		for (Mascota m : arrayMascota) {
			System.out.println(m.getNombre());
			System.out.println(m.getEspecie());
			System.out.println(m.getSexo());
			System.out.println(m.getEdad());
			System.out.println("------------");

			for (Cita cita : m.getArrayHistoria()) {
				System.out.println(cita.getFecha());
				System.out.println(cita.getMotivo());
			}
			System.out.println("------------");

		}

		serializar(arrayMascota);
		
	}

	private static void  conversorDatos(String id, String nombre, String especie, String sexo, String edad,
		//COMO NO SE PUEDE PASAR INT EN EL FOR DEL NODETLIST TENEMOS QUE HACER LA TRANSOFMRACION A STRING
		ArrayList<Cita> historial, ArrayList<Mascota> listaMascotas) {
		int idInt = Integer.parseInt(id);
		int edadInt = Integer.parseInt(edad);
		Mascota m = new Mascota(idInt, edadInt, nombre, sexo, especie, historial);
		listaMascotas.add(m);
	}
	
	
	public static void serializar(ArrayList<Mascota> arrayMascota) {
		
		String path = "serializable.txt";
	
		try {
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutput oos = new ObjectOutputStream(fos);
			
			oos.writeObject(arrayMascota);
		
			oos.close();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
