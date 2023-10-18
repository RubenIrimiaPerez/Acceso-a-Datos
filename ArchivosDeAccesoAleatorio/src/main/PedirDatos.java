package main;




import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class PedirDatos {

	public static void main(String[] args) {
		

		try {
			
			Scanner sc = new Scanner(System.in);
			int tamRegistro = 39;
			byte[] texto = new byte[(int)tamRegistro];
			RandomAccessFile registros = new RandomAccessFile("personas.txt", "rw");
			System.out.println("Nombre a buscar");
			String nombre = sc.nextLine();
			String linea;
			boolean si =false;
			

			while ((linea = registros.readLine()) != null) {
				if(linea.contains(nombre)) {
					System.out.println("nombre encontrado");
					System.out.println(linea);
					si = true;
				}
			}
			
			if(!si) {
				System.out.println("No esta");
				
			}

		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}

}
 