package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import ventana.Ventana;

public class MainCliente {
	public static DataOutputStream dos;
	public static DataInputStream dis;
	public static Ventana ventana = new Ventana();
	public static void main(String[] args) {
		ventana.setVisible(true);

		try {
			boolean continuar = true;
			Socket socket = new Socket("PC18621", 6565);
			
			dos = new DataOutputStream(socket.getOutputStream());
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			Scanner sc = new Scanner(System.in);
			String mensaje;

			do{
				// Recibimos la respuesta del server
				mensaje = dis.readUTF();
				ventana.mensajes.append(mensaje + "\n");
				continuar = !mensaje.toUpperCase().equals("FIN");
			}while (continuar) ;
			ventana.setVisible(false);
			// Cierre de todas las conexiones o streams de datos y del programa
			sc.close();
			dos.close();
			dis.close();
			socket.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void enviarMensaje(String mensaje) {
		try {
			dos.writeUTF(mensaje);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

