package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class ContarLetras {

	public static void main(String[] args) {

		try {

			File archivo = new File("agua.txt");
			InputStream is = new FileInputStream(archivo);
			long i = archivo.length();
			int byteLeido;
			int recuento = 0;
			int caracter;

			while (i != 0) {
				byteLeido = is.read();
				caracter = (char) byteLeido;
				
				if((caracter >= 'a' && caracter <='z') || (caracter >= 'A' && caracter <='Z')) {
					
					recuento++;
					
				}
				i--;
			}

		System.out.println(recuento +" Son las letras del archivo");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

