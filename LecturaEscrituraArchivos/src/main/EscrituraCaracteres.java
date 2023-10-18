package main;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class EscrituraCaracteres {

	public static void main(String[] args) {
		
		try {
			FileWriter fw = new FileWriter("archivoEscrito2.txt",true); //true para no sobreescribir
			PrintWriter pw = new PrintWriter(fw,true); //true para autoflush
			
			pw.println("Esta es la primera linea");
			pw.println("Esta es la segunda linea");
			pw.println("Esta es la tercera linea");
			
			pw.flush();
			pw.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
