package objetos;

import java.util.ArrayList;
import java.util.List;

public class Paciente {
	private String id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nacimiento;
    private String residencia;
    private List<Cita> citas;

	public Paciente(String id, String nombre, String apellido1, String apellido2, String nacimiento, String residencia,
			List<Cita> citas) {
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacimiento = nacimiento;
		this.residencia = residencia;
		this.citas = citas;
	}
	public Paciente() {
		// TODO Auto-generated constructor stub
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(String nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getResidencia() {
		return residencia;
	}
	public void setResidencia(String residencia) {
		this.residencia = residencia;
	}
	public List<Cita> getCitas() {
		return citas;
	}
	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
	
	

}
