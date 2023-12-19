package main;

import java.io.Serializable;

public class Cita implements Serializable {

	private String fecha,motivo;

	public Cita(String fecha, String motivo) {
		super();
		this.fecha = fecha;
		this.motivo = motivo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
