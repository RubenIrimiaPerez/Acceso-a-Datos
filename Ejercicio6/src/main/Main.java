package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

        try {
            FileWriter fw = new FileWriter("cadenas.txt", true);
            BufferedWriter br = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(br);

            File archivo = new File("cadenas.txt");

            while (true) {
                System.out.print("Ingrese una cadena (o escriba 'fin' para terminar): ");
                String cadena = scanner.nextLine();

                if (cadena.equalsIgnoreCase("fin")) {
                    break;
                }

                if (!existeCadenaEnArchivo(archivo, cadena)) {
                    pw.println(cadena);
                } else {
                    System.out.println("Error: Cadena duplicada.");
                }
            }
            pw.close();
            System.out.println("Cadenas guardadas en 'cadenas.txt'.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }

    public static boolean existeCadenaEnArchivo(File archivo, String cadena)  {
    	
    	 try {
    	        BufferedReader br = new BufferedReader(new FileReader(archivo));
    	        String linea;
    	        while ((linea = br.readLine()) != null) {
    	            if (linea.equals(cadena)) {
    	                br.close();
    	                return true;
    	            }
    	        }
    	        br.close();
    	    } catch (IOException e) {
    	        System.err.println("Error al leer el archivo: " + e.getMessage());
    	    }
    	    return false;
    

	}

}
