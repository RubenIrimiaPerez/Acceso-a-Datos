package main;

import java.sql.SQLException;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (Connection con = Conexion.open()){
			printSQL(con,"SELECT  m.alias, SUM(mp.unidades) AS 'suma unidades'"
					+ " from magos m  inner join magos_pocimas mp on m.id = mp.idMago "
					+ "group  by id "
					+ ""
			);
		}catch(SQLException ex) {
			ex.printStackTrace();
			
		}

	}
	
	public static void printSQL(Connection con,String query) {
		try (PreparedStatement ps = con.prepareStatement(query)){
			//ps.setString(1, );
			
			try(ResultSet rs = ps.executeQuery()){
				
				while(rs.next()) {
					//int id = rs.getInt("id");
					String alias = rs.getString("alias");
					int sum = rs.getInt("suma unidades");
					
					
			System.out.println(
					 "ALIAS: " +alias
					+ ",TOTAL: " + sum
					
					);		

					
							
					
				}
				
			}
			
		}catch(SQLException ex) {
			ex.printStackTrace();	
		}
	}

}
