package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;


public class LecturaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {

			File archivo = new File("archivo.txt");
			InputStream is = new FileInputStream(archivo);
			long i = archivo.length();
			int byteLeido;
			                                         // este try lo muestra letra a letra en lineas diferentes , los saltos de linea
			while (i != 0) {
				byteLeido = is.read();
				System.out.println((char) byteLeido);       //se pone (char) para convertirlo  en caracteres
				i--;
			}
			
			is.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {

			File archivo = new File("archivo.txt");
			InputStream is = new FileInputStream(archivo);
			byte[] bytesLeidos = new byte [(int) archivo.length()];  //se pone (int) para convertirlo  en int para poder luego leerlo
			int byteLeido;
			
			is.read(bytesLeidos);                     // este try lo muestra tal cual como el documento de texto
			
			System.out.println(Arrays.toString(bytesLeidos));       // muestra un array con numeros relacionados con los caracteres del documento
			System.out.println(new String(bytesLeidos));             
			
			
			is.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
