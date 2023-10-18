package mainserializacion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import serializable.Persona;

public class Main {
	private static ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
	public static void main(String[] args) {
		
		if(new File("listaPersonas.ser").exists()) {
			System.out.println("Archivo guardado, Cargando el Array...");
			deserializar();
		}else {
			System.out.println("Archivo no encontrado generando Array");
			listaPersonas.add(new Persona("Maria", "Garcia","9145367894"));
			listaPersonas.add(new Persona("Luis", "Fernandez","634597125"));
			listaPersonas.add(new Persona("Eusebio", "Luna","647821963"));	
		}
		
		
		serializar();

	}
	public static void serializar() {
		String path = "listaPersonas.ser";
		
		
		try {
			FileOutputStream fos = new FileOutputStream(path) ;
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(listaPersonas);
			oos.close();
			fos.close();
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void deserializar() {
		String path = "listaPersonas.ser";
		try {
			FileInputStream fis = new FileInputStream(path) ;
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			listaPersonas = (ArrayList<Persona>)ois.readObject();
			
			
			ois.close();
			fis.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}


