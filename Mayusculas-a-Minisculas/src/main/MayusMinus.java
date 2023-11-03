package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MayusMinus {

	public static void main(String[] args) {

		  String archivoEntrada = pedirRutaArchivo("Introduce la ruta del archivo de entrada: ");

	        try {
	            // Abre el archivo de entrada para lectura
	            FileReader fileReader = new FileReader(archivoEntrada);
	            BufferedReader bufferedReader = new BufferedReader(fileReader);

	            // Genera el nombre del archivo de salida con "_inver" al final
	            String archivoSalida = archivoEntrada.replaceFirst("[.][^.]+$", "_inver$0");

	            // Abre el archivo de salida para escritura
	            PrintWriter printWriter = new PrintWriter(new FileWriter(archivoSalida));

	            String linea;

	            // Lee el archivo de entrada línea por línea
	            while ((linea = bufferedReader.readLine()) != null) {
	                // Procesa cada línea y cambia mayúsculas por minúsculas y viceversa
	                String lineaInvertida = invertirMayusculasMinusculas(linea);

	                // Escribe la línea procesada en el archivo de salida
	                printWriter.println(lineaInvertida);
	            }

	            // Cierra los archivos
	            fileReader.close();
	            printWriter.close();

	            System.out.println("Archivo procesado con éxito. El resultado se encuentra en " + archivoSalida);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Función para cambiar mayúsculas por minúsculas y viceversa
	    private static String invertirMayusculasMinusculas(String texto) {
	        char[] caracteres = texto.toCharArray();
	        for (int i = 0; i < caracteres.length; i++) {
	            char c = caracteres[i];
	            if (Character.isUpperCase(c)) {
	                caracteres[i] = Character.toLowerCase(c);
	            } else if (Character.isLowerCase(c)) {
	                caracteres[i] = Character.toUpperCase(c);
	            }
	        }
	        return new String(caracteres);
	    }

	    // Función para pedir la ruta del archivo al usuario
	    private static String pedirRutaArchivo(String mensaje) {
	        java.util.Scanner scanner = new java.util.Scanner(System.in);
	        System.out.print(mensaje);
	        return scanner.nextLine();
	}

}
