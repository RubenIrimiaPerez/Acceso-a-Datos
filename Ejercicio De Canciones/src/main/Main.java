package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Cancion.Cancion;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Cancion> Canciones = new ArrayList();
		File archivoDestino = new File("Canciones.txt");
		int opciones;

		try {
			if (!archivoDestino.exists()) {
				System.out.println("Creando");
				archivoDestino.createNewFile();
			}

			FileReader fr = new FileReader("Canciones.txt");
			BufferedReader br = new BufferedReader(fr);
			FileWriter fw = new FileWriter("Canciones.txt",true);
			PrintWriter pw = new PrintWriter(fw,true);
			String linea;
			String Cosas[];

			while ((linea = br.readLine()) != null) {

				Cancion nueva = new Cancion();
				Cosas = linea.split(",");

				nueva.setId(Integer.parseInt(Cosas[0])); 
				nueva.setTitulo(Cosas[1]);
				nueva.setArtista(Cosas[2]);
				nueva.setMinutos(Cosas[3]);
				nueva.setAlbum(Cosas[4]);
				nueva.setLetra(Cosas[5]);
				Canciones.add(nueva);
			}
			


			do {
				
				System.out.println("ELIGE UNA OPCION");
				System.out.println("(1)Añade una cancion \n(2)Modifica una cancion \n(3)Borra una cancion \n(4)Ver canciones guardadas");
			opciones = sc.nextInt();
			sc.nextLine();
			
			switch (opciones) {
			  case 1 :
				  
				  Cancion nueva = new Cancion();
				  
					int Id = 1;
					
					for(int i = 0 ; i<Canciones.size();i++) {
						
						
						Id++;
					}
				
					nueva.setId(Id);
					System.out.println("Titulo de la cancion");
					String Titulo = sc.nextLine();
					
					nueva.setTitulo(Titulo);
					
					
					System.out.println("Artista de la cancion");
					String Artista = sc.nextLine();
					
					nueva.setArtista(Artista);
					
				
					System.out.println("Minutos de la cancion");
					String Minutos = sc.nextLine();
					
					
					nueva.setMinutos(Minutos);
					
					
					
					System.out.println("Album dal que pertenece la cancion");
					String Album = sc.nextLine();
					
					nueva.setAlbum(Album);
					
					nueva.setLetra(Titulo + ".txt");
					
					Canciones.add(nueva);
					
					File Letra = new File(nueva.getLetra());
					Letra.createNewFile();
					
					pw.println(nueva.getId() + "," + nueva.getTitulo() + "," + nueva.getArtista() + ","
							+ nueva.getMinutos() + "," + nueva.getAlbum() + "," + nueva.getLetra());
					
		
			    break;
			  case 2:
					System.out.println("Que quieres modificar");

					for (int i = 0; i < Canciones.size(); i++) {
						System.out.println(Canciones.get(i).getId() + "," + Canciones.get(i).getTitulo() + ","
								+ Canciones.get(i).getArtista() + "," + Canciones.get(i).getMinutos() + ","
								+ Canciones.get(i).getAlbum());
					}

					int tipoCambiar = sc.nextInt();
					sc.nextLine();

					System.out.println("ELIJE QUE DESEA MODIFICAR \n(1)Titulo\n(2)Artista\n(3)Minutos\n(4)Album");
					int seleccion = sc.nextInt();
					sc.nextLine();
				
				  switch(seleccion) {
				  case 1:
					  System.out.println("Nuevo Titulo");
					  String NuevoTitulo = sc.nextLine();
					  Canciones.get(tipoCambiar-1).setTitulo(NuevoTitulo);
					  break;
				  case 2:
					  System.out.println("Nuevo Artista");
					  String NuevoArtista = sc.nextLine();
					  Canciones.get(tipoCambiar-1).setArtista(NuevoArtista);
					  break;
				  case 3:
					  System.out.println("Nueva duracion");
					  String NuevaDuracion = sc.nextLine();
					  Canciones.get(tipoCambiar-1).setMinutos(NuevaDuracion);
					  break;
				  case 4:
					  System.out.println("Nuevo Album");
					  String NuevoAlbum = sc.nextLine();
					  Canciones.get(tipoCambiar-1).setAlbum(NuevoAlbum);
				  }
				  
					for (int i = 0; i < Canciones.size(); i++) {
						System.out.println(Canciones.get(i).getId() +","+ Canciones.get(i).getTitulo()+","
								+ Canciones.get(i).getArtista() +","+ Canciones.get(i).getMinutos()+","
								+ Canciones.get(i).getAlbum()+"," + Canciones.get(i).getLetra());
					}
				  
			    break;
			  case 3:
				  System.out.println("QUE CANCION QUIERES ELIMINAR");

		            for (int i = 0; i < Canciones.size(); i++) {

		            	System.out.println(Canciones.get(i).getId() + "," + Canciones.get(i).getTitulo() + ","
								+ Canciones.get(i).getArtista() + "," + Canciones.get(i).getMinutos() + ","
								+ Canciones.get(i).getAlbum());
		            }
		            int eliminar = sc.nextInt();
		            Canciones.remove(eliminar - 1);
		            
		            for (int i = 0; i < Canciones.size(); i++) {
		                
						pw.println(Canciones.get(i).getId() + "," + Canciones.get(i).getTitulo() + ","
								+ Canciones.get(i).getArtista() + "," + Canciones.get(i).getMinutos() + ","
								+ Canciones.get(i).getAlbum() + "," + Canciones.get(i).getLetra());
		            }
					
			 
				  break;
			  case 4:

					for (int i = 0; i < Canciones.size(); i++) {
						System.out.println(Canciones.get(i).getId() +","+ Canciones.get(i).getTitulo()+","
								+ Canciones.get(i).getArtista() +","+ Canciones.get(i).getMinutos()+","
								+ Canciones.get(i).getAlbum()+"," + Canciones.get(i).getLetra());
					}
				  
				  break;
			}
			System.out.println("");
			}while(opciones>0&&opciones<5);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
