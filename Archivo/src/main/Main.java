package main;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		File archivo = new File("text.txt"); // ruta relativa
		File padre = archivo.getParentFile();
		File creado = new File("creado.txt");
		File carpeta = new File(".");
		File[] hijos = carpeta.listFiles();
		
		double bytes = 0;
		
		
		if (creado.exists()) {
			System.out.println("El archivo ya existe");
		} else {
			System.out.println("El archivo no existe. Creando..");

			try {
				creado.createNewFile();
			} catch (IOException e) {

				e.printStackTrace();
			}
			System.out.println("Archivo creado");

		}
		System.out.println("Ruta absoluta de text.txt: " + archivo.getAbsolutePath());// para leer la ruta absoluta
		System.out.println("Ruta absoluta de text.txt: " + creado.getAbsolutePath());// para leer la ruta absoluta

	    System.out.println("El archivo text.txt es un directorio? " + (archivo.isDirectory() ? "Si" : "No")); // para saber si es un directorio
		System.out.println("El archivo se puede ejecutar? " + (archivo.canExecute() ? "si" : "no")); // para saber si es un ejecutable
		System.out.println("El archivo es invisible? " + (archivo.isHidden() ? "si" : "no")); // para saber si es invisible
		System.out.println("El archivo ocupa? " + archivo.length() + " bytes"); // para saber los caracteres que ocupa

		if (creado.delete()) {
			System.out.println("Archivo eliminado con exito");
		} else {
			System.out.println("Archivo no eliminado");
		}

		System.out.println("Carpeta del proyecto " + carpeta.getAbsolutePath()); // para leer la ruta absoluta
		
		for( File f: hijos) {
			System.out.println(f.getName());   // bucle para sacar los nombres de los archivos

	}
		for( File f: hijos) {
			
		bytes += f.length();
	}
		System.out.println( "Los archivos ocupan "+ bytes +" bytes");  // bucle para saber lo que pesan solo los archivos -
			
		}


}

