package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<Persona> arrayPersonas = new ArrayList<Persona>();
		PaintHTML pintar = new PaintHTML();
		try {
			FileReader fr = new FileReader("personas.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea;

			while ((linea = br.readLine()) != null) {
				
				String[] campos = linea.split(",");
				Persona p = new Persona();
				
				p.setId(Integer.parseInt(campos[0]));
				p.setNombre(campos[1]);
				p.setApellido1(campos[2]);
				p.setApellido2(campos[3]);
				p.setEdad(Integer.parseInt(campos[4]));
				p.setUrlImg(campos[5]);
			
				arrayPersonas.add(p);
			}
			
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (Persona persona : arrayPersonas) {
			
			System.out.print(persona.getId()+",");
			System.out.print(persona.getNombre()+",");
			System.out.print(persona.getApellido1()+",");
			System.out.print(persona.getApellido2()+",");
			System.out.print(persona.getUrlImg()+",");
			System.out.print(persona.getEdad()+",");
			System.out.println();
			
		}
		pintar.CREAR(arrayPersonas);
	}
}
