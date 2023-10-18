package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LecturaCaracteres {

	public static void main(String[] args) {
		
		
		try {
			FileReader fr = new FileReader("archivo.txt");
			BufferedReader br = new BufferedReader (fr);
			String linea;
			
			while((linea = br.readLine()) !=  null) {
				System.out.println(linea);
			}
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
