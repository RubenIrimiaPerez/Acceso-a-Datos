package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import objects.HiloCreadorCarpetas;
import objects.HiloLectorArchivo;
import objects.Paciente;

public class Main {
	public static Scanner sc = new Scanner(System.in);

	public static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	
	public static void main(String[] args) {
		int menu;
		
		HiloLectorArchivo hiloLector = new HiloLectorArchivo(pacientes);
		HiloCreadorCarpetas hiloCarpetas = new HiloCreadorCarpetas(pacientes,hiloLector);
		
		// menu
				do {
					System.out.println(
							"QUE DESEAS HACER:\n0 - Salir  \n1 -INICIAR PROGRAMA");
					menu = sc.nextInt();
					sc.nextLine();

					switch (menu) {

					case 0:
						System.out.println("Programa finalizado");
						break;
					case 1:
						
						hiloLector.start();
						hiloCarpetas.start();
						
						try {
							hiloLector.join();
							hiloCarpetas.join();
						} catch (InterruptedException e) {
						
							e.printStackTrace();
						}
						
						System.out.println("Quieres ver algun paciente:\n 1 -si  \n 2 - no");
						int mostrar = sc.nextInt();
						sc.nextLine();
						
						if(mostrar == 1) {
							mostrarPacienteConsola();
						}
						break;
					
						
					}
				} while (menu != 0);
	
	}
	
	public static void mostrarPacienteConsola() {
		
		System.out.println("introduce la ruta del paciente que quieres leer: ");
		String pathPaciente = sc.nextLine();
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newDefaultInstance();  //se crea el objeto
		DocumentBuilder db;
		Document documentDatosPersonales;
		Document documentCitas ;
		File file;
		XPath xPath;
		NodeList nodeList;
		String path;
		
		try {
			db = dbf.newDocumentBuilder();
			file = new File(pathPaciente+"/Datos Personales.xml");
			documentDatosPersonales = db.parse(file);
			
			documentDatosPersonales.normalizeDocument();
			
			xPath = XPathFactory.newInstance().newXPath();
			
			//la consulta
			path = "//Paciente";
			
			// los nodos resultantes de la consulta
			nodeList = (NodeList) xPath.compile(path).evaluate(documentDatosPersonales,XPathConstants.NODESET);
			
			for (int i = 0;i< nodeList.getLength();i++) {
				Element elemento  = (Element) nodeList.item(i);
				
				System.out.println("ID: "+elemento.getElementsByTagName("id").item(0).getTextContent());
				System.out.println("Nombre: "+elemento.getElementsByTagName("Nombre").item(0).getTextContent()+elemento.getElementsByTagName("Apellido").item(0).getTextContent()+elemento.getElementsByTagName("Apellido2").item(0).getTextContent());
				System.out.println("Nacimiento: "+elemento.getElementsByTagName("Nacimiento").item(0).getTextContent());
				System.out.println("Residencia: "+elemento.getElementsByTagName("Localidad").item(0).getTextContent());


				
			}
			
			file = new File(pathPaciente+"/Citas.xml");
			documentDatosPersonales = db.parse(file);
			
			documentDatosPersonales.normalizeDocument();			
			xPath = XPathFactory.newInstance().newXPath();
			
			//la consulta
			path = "//Citas";
			
			// los nodos resultantes de la consulta
			nodeList = (NodeList) xPath.compile(path).evaluate(documentDatosPersonales,XPathConstants.NODESET);
			
			System.out.println("Citas:");
			for (int i = 0;i< nodeList.getLength();i++) {
				Element elemento  = (Element) nodeList.item(i);
				NodeList listaCitas = elemento.getElementsByTagName("Cita");
				
				
				for (int x = 0;x< nodeList.getLength();x++) {
					Element cita = (Element) listaCitas.item(x);
					
					System.out.println(">Centro:  "+cita.getElementsByTagName("Centro").item(0).getTextContent());
					System.out.println(">Especialidad:  "+cita.getElementsByTagName("Especialidad").item(0).getTextContent());
					System.out.println(">Doctor"+cita.getElementsByTagName("Doctor").item(0).getTextContent());
					System.out.println(">Fecha  "+cita.getElementsByTagName("Fecha").item(0).getTextContent());
					System.out.println(">Hora  "+cita.getElementsByTagName("Hora").item(0).getTextContent());
				

				}

				
			}
			
			
			
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		
		
	}

}
