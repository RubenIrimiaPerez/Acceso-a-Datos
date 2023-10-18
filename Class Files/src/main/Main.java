package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

	public static void main(String[] args) {

		if (Files.exists(Path.of("text.txt"))) {
			System.out.println("El archivo 'text.txt' ya exite");
		} else {
			try {
				Files.createFile(Path.of("text.txt"));
				System.out.println("CREANDO EL ARCHIVO");
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		System.out.println("Creando acceso directo en Escritorio...");
		
		try {
			Files.copy(Path.of("text.txt"),Path.of("/Users/Alumno/Desktop/text - copia.txt")); //si falla poner esta barra \\
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
