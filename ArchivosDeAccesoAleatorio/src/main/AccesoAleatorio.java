package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AccesoAleatorio {

	public static void main(String[] args) {
		
		
		try {
			int tamRegistro = 39;
			byte[]texto = new byte[tamRegistro];
			
			RandomAccessFile registros = new RandomAccessFile("personas.txt","rw");
			
			System.out.println((char)registros.read());
			System.out.println((char)registros.read());
			System.out.println((char)registros.read());
			System.out.println((char)registros.read());
			System.out.println((char)registros.read());
		
			
		
			registros.seek(0); // te coge desde el primer carácter
			
			System.out.println((char)registros.read());
			System.out.println((char)registros.read());
			
			
		
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
