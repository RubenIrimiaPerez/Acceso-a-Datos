package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		  String nombreArchivo = "Muchos_Caracteres.txt"; 

	        try {
	            FileReader fr = new FileReader(nombreArchivo);
	            BufferedReader br = new BufferedReader(fr);
	            int lineasRecuento = 0;
	            String linea;

	            while ((linea = br.readLine()) != null) {
	            	lineasRecuento++;
	            }

	            br.close();
	            System.out.println("El archivo '" + nombreArchivo + "' tiene " + lineasRecuento + " líneas.");
	        } catch (IOException e) {
	            System.err.println("Error al leer el archivo: " + e.getMessage());
	        }

	}

}
