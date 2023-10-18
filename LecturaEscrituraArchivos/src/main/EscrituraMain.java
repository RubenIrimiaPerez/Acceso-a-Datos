package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EscrituraMain {

	public static void main(String[] args) {
		File archivoDestino = new File("archivoEscrito.txt");
		OutputStream  os;
		byte [] bytesEscribir = "Esto va a escribir".getBytes();
		
		try {
			
		if(!archivoDestino.exists()) {
			System.out.println("Creando");
			archivoDestino.createNewFile();
		}
		
		//si usamos true como segundo argumento entonces se añaade al final del archivo
		//en caso contrario se sobreescribe
		os = new FileOutputStream(archivoDestino,true);
		
		os.write(49);
		os.write(50);
		os.write(51);
		os.write(bytesEscribir);
		
		os.flush();
		os.close();
			} catch (IOException e) {
				System.out.println("no se puedo crear");
				e.printStackTrace();
			}
		}

	}


