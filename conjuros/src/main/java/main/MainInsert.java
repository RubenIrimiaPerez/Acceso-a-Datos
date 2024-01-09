package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import conexion.Conexion;
import entidades.Mago;

public class MainInsert {

	public static void main(String[] args) {
		ArrayList<Mago> magosNuevos = new ArrayList<Mago>();
		
		magosNuevos.add(new Mago ("MagoNuevo1","Mago Nuevo1","652359741"));
		magosNuevos.add(new Mago ("MagoNuevo2","Mago Nuevo2","652357523"));
		magosNuevos.add(new Mago ("MagoNuevo3","Mago Nuevo3","632125895"));
		
		
		
		//codigo para hacer insert
		try (Connection con = Conexion.open()){
		
			//insertar(con,magosNuevos);
			//actualizarNombre(con,1,"Nombre Cambiado");
			borrar(con,2);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}

	}


	//METODO INSERTAR
	private static void insertar(Connection con, ArrayList<Mago> magosNuevos) {

		String sql = "INSERT INTO magos VALUES (NULL, ?, ?, ?)";
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			
			for(Mago m : magosNuevos) {
				ps.setString(1, m.getAlias());
				ps.setString(2, m.getNombre());
				ps.setString(3, m.getTelefono());
				
				int nFilas = ps.executeUpdate();
				
				System.out.println("se han añadidod "+ nFilas+" Correctamente");
			}
			
		}catch(SQLException ex) {
		ex.printStackTrace();
		
	}
		
	}
	
	//METODO ACTUALIZAR
		private static void actualizarNombre(Connection con ,int primaryKey, String nuevoNombre) {
			
			String sql = "UPDATE magos SET nombre = ? WHERE id = ?";
			
			try(PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setString(1, nuevoNombre);
				ps.setInt(2, primaryKey);
				
				int nFilas = ps.executeUpdate();
				
				System.out.println("se han Modificado "+ nFilas+" Correctamente");
				
			}catch(SQLException ex) {
			ex.printStackTrace();
			
			}
		}
		
	
	//METODO BORRAR
		private static void borrar(Connection con ,int primaryKey) {
			
			String sql = "DELETE FROM magos WHERE id = ?";
			
			try(PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setInt(1, primaryKey);
				
				int nFilas = ps.executeUpdate();
				
				System.out.println("se han borrado "+ nFilas+" Correctamente");
				
			}catch(SQLException ex) {
			ex.printStackTrace();
			
			}
		}

}


