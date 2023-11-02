package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		 String nombreArchivo = "numeros.txt"; // Reemplaza con la ruta o nombre de tu archivo

	        try {
	            FileReader fr = new FileReader(nombreArchivo);
	            BufferedReader br = new BufferedReader(fr);
	            String linea;
	            int suma = 0;

	            while ((linea = br.readLine()) != null) {
	                for (int i = 0; i < linea.length(); i++) {
	                    char caracter = linea.charAt(i);
	                    if (Character.isDigit(caracter)) {
	                        int digito = Character.getNumericValue(caracter);
	                        suma += digito;
	                    }
	                }
	            }

	            br.close();
	            System.out.println("Suma: " + suma);
	        } catch (IOException e) {
	            System.err.println("Error al leer el archivo: " + e.getMessage());
	        }
	    }
}
