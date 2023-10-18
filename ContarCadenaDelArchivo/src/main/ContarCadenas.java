package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ContarCadenas {

	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(System.in);
			File archivo = new File("Archivo.txt");
			InputStream is = new FileInputStream(archivo);
			long i = archivo.length();
			int byteLeido;
			int j = 0;
			int recuento = 0;
			String cadena;

			System.out.println("Introduce la cadena deseada");
			cadena = scanner.nextLine();
			scanner.close();

			while (i != 0) {
				byteLeido = is.read();
				char caracter = (char) byteLeido;

				// si hay coincidencia incrementamos la j
				// si j = a la longitud aumentamos recuento y reseteamos j

				if (cadena.charAt(j) == caracter) {
					j++;

					if (j == cadena.length()) {
						recuento++;
						j = 0;
					}

				} else {
					// si no hay coincidencia j debe resetearse
					j = 0;

					if (cadena.charAt(j) == caracter) {
						j = 1;
					}
				}

				i--;
			}

			System.out.println("hay " + recuento + " repeticiones");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}


	


