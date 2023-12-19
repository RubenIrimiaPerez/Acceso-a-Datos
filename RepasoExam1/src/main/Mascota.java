package main;

import java.io.Serializable;
import java.util.ArrayList;

public class Mascota implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id,edad;
	private String nombre,sexo,especie;
	ArrayList<Cita> arrayHistoria = new ArrayList<Cita>();
	
	public Mascota(int id, int edad, String nombre, String sexo, String especie, ArrayList<Cita> arrayHistoria) {
		super();
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.sexo = sexo;
		this.especie = especie;
		this.arrayHistoria = arrayHistoria;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public ArrayList<Cita> getArrayHistoria() {
		return arrayHistoria;
	}
	public void setArrayHistoria(ArrayList<Cita> arrayHistoria) {
		this.arrayHistoria = arrayHistoria;
	}

	
	
	
}
