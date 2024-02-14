package main;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import entidades.Cliente;

public class HiloCliente extends Thread {
	private Socket socket;
	DataInputStream dis;
	static DataOutputStream dos;
	static String id;
	static String nombre;
	static String edad;
	static String apellido1;
	static String apellido2;
	static String fechaNacimiento;

	public HiloCliente(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		String mensaje;
		try {
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
			
			do {
				dos.writeUTF(
						"Introduzca que desea realizar: \n 1. Ver datos de la tabla con 'ver clientes'\n 2. Insertar datos con 'insertar cliente'\n 3. Modificar cliente con 'mod cliente'\n 4. Borrar cliente con 'del cliente'\n 5. Salir con 'FIN'");
				mensaje = dis.readUTF();
				
				for (int i = 0; i < MainServidor.hilos.size(); i++) {
					try (Connection con = Conexion.open()) {
						if (mensaje.equals("ver clientes")) {
							// Ver tabla clientes
							printSQL(con, "SELECT * FROM clientes");
							
						} else if (mensaje.equals("insertar cliente")) {
							// Insertar clientes nuevos
							ArrayList<Cliente> clientesNuevos = new ArrayList<Cliente>();
							dos.writeUTF("Introduce nombre:");
							nombre = dis.readUTF();
							dos.writeUTF("Introduce el primer apellido:");
							apellido1 = dis.readUTF();
							dos.writeUTF("Introduce el segundo apellido:");
							apellido2 = dis.readUTF();
							dos.writeUTF("Introduce tu edad:");
							edad = dis.readUTF();
							Integer edadInt = Integer.parseInt(edad);
							dos.writeUTF("Introduce tu fecha de nacimiento (yyyy-mm-dd):");
							fechaNacimiento = dis.readUTF();

							clientesNuevos.add(new Cliente(nombre, apellido1, apellido2, edadInt, fechaNacimiento));
							insertar(con, clientesNuevos);
							break;
						} else if (mensaje.equals("del cliente")) {
							// Eliminar un cliente por ID
							dos.writeUTF("Introduce el ID del cliente que quieras eliminar: ");
							id = dis.readUTF();
							Integer IDInt = Integer.parseInt(id);
							
							borrar(con, IDInt);
							
						} else if (mensaje.equals("mod cliente")) {
							// Modificar los campos de un cliente
							dos.writeUTF("Introduce el ID del cliente que quieras modificar: ");
							id = dis.readUTF();
							Integer IDInt = Integer.parseInt(id);
							dos.writeUTF("Introduce nuevo nombre:");
							String nuevoNombre = dis.readUTF();
							dos.writeUTF("Introduce primer apellido nuevo:");
							String nuevoApellido1 = dis.readUTF();
							dos.writeUTF("Introduce segundo apellido nuevo:");
							String nuevoApellido2 = dis.readUTF();
							dos.writeUTF("Introduce edad nueva:");
							String nuevaEdad = dis.readUTF();
							Integer nuevaEdadInt = Integer.parseInt(nuevaEdad);
							dos.writeUTF("Introduce fecha de nacimiento nueva(yyyy-mm-dd):");
							String nuevoFechaNacimiento = dis.readUTF();
							
							actualizarNombre(con, IDInt, nuevoNombre, nuevoApellido1, nuevoApellido2, nuevaEdadInt,
									nuevoFechaNacimiento);
						} else if (mensaje.toUpperCase().equals("FIN")) {
							// Cerrar conexión (Ns que le pasa 😫)
							dos.writeUTF("FIN");
						} else {
							// Si el tio no sabe escribir o no entiende...
							dos.writeUTF("\nOrden desconocida. Introduzca una orden válida:\n");
						}
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// Leemos el mensaje y lo ponemos por consola para ir comprobando
				System.out.println(socket.getInetAddress().getHostName() + ": " + mensaje);
			} while (!mensaje.toUpperCase().equals("FIN"));
			// Elimina el hilo que sale del server
			MainServidor.hilos.remove(this);
			// Cerrar conexiones y streams
			dis.close();
			dos.close();
			socket.close();
		} catch (IOException e) {

		}
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	private static void actualizarNombre(Connection con, Integer primaryKey, String nuevoNombre, String nuevoApellido1,
			String nuevoApellido2, Integer nuevaEdad, String nuevoFechaNacimiento) {
		String sql = "UPDATE clientes SET nombre = ?, apellido1 = ?, apellido2 = ?, edad = ?, fechaNacimiento = ? WHERE id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, nuevoNombre);
			ps.setString(2, nuevoApellido1);
			ps.setString(3, nuevoApellido2);
			ps.setInt(4, nuevaEdad);
			ps.setString(5, nuevoFechaNacimiento);
			ps.setInt(6, primaryKey);

			int nFilas = ps.executeUpdate();

			try {
				dos.writeUTF("Se han modificado " + nFilas + " correctamente.\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	private static void borrar(Connection con, int primaryKey) {
		String sql = "DELETE FROM clientes WHERE id = ?";

		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, primaryKey);

			int nFilas = ps.executeUpdate();

			try {
				dos.writeUTF("Se han borrado " + nFilas + " correctamente.\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	private static void insertar(Connection con, ArrayList<Cliente> clientesNuevos) {
		String sql = "INSERT INTO clientes VALUES (NULL, ?, ?, ?, ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(sql)) {

			for (Cliente c : clientesNuevos) {
				ps.setString(1, c.getNombre());
				ps.setString(2, c.getApellido1());
				ps.setString(3, c.getApellido2());
				ps.setInt(4, c.getEdad());
				ps.setString(5, c.getFechaNacimiento());

				int nFilas = ps.executeUpdate();

				try {
					dos.writeUTF("Se han añadido " + nFilas + " correctamente.\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public static void printSQL(Connection con, String query) {

		try (PreparedStatement ps = con.prepareStatement(query)) {

			try (ResultSet rs = ps.executeQuery()) {

				dos.writeUTF("\nTABLA CLIENTES");
				while (rs.next()) {
					id = rs.getString("id");
					nombre = rs.getString("nombre");
					edad = rs.getString("edad");
					apellido1 = rs.getString("apellido1");
					apellido2 = rs.getString("apellido2");
					fechaNacimiento = rs.getString("fechaNacimiento");

					dos.writeUTF("ID: " + id + "\nNombre: " + nombre + " " + apellido1 + " " + apellido2 + "\nEdad: "
							+ edad + "\nNacimiento: " + fechaNacimiento + "\n");
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}
}