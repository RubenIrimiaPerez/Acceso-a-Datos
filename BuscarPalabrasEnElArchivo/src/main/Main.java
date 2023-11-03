package main;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        try {
	            RandomAccessFile file = new RandomAccessFile("palabras.txt", "rw");

	          
	            while (true) {
	                System.out.print("1. Ingrese una palabra (máximo 50 caracteres)\n2. Buscar una palabra\n3. Salir\nSeleccione una opción: ");
	                int opcion = scanner.nextInt();
	                scanner.nextLine(); //
	                if (opcion == 1) {
	                    System.out.print("Ingrese una palabra (escriba 'fin' para terminar): ");
	                    String palabra = scanner.nextLine();

	                    if (palabra.equalsIgnoreCase("fin")) {
	                        break;
	                    }

	                    if (palabra.length() > 50) {
	                        System.out.println("La palabra es demasiado larga (máximo 50 caracteres).");
	                    } else {
	                        file.writeBytes(palabra + "\n");
	                        System.out.println("Palabra guardada en 'palabras.txt'.");
	                    }
	                } else if (opcion == 2) {
	                    System.out.print("Ingrese la palabra a buscar: ");
	                    String palabraBuscada = scanner.nextLine();
	                    int contador = 0;

	                    file.seek(0); // Volver al inicio del archivo

	                    String line;
	                    while ((line = file.readLine()) != null) {
	                        if (line.equals(palabraBuscada)) {
	                        	contador++;
	                        }
	                    }

	                    System.out.println("La palabra '" + palabraBuscada + "' aparece " + contador + " veces en el archivo.");
	                } else if (opcion == 3) {
	                    break;
	                } else {
	                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
	                }
	            }

	            file.close();
	        } catch (IOException e) {
	            System.err.println("Error al manejar el archivo: " + e.getMessage());
	        }

	}

}
