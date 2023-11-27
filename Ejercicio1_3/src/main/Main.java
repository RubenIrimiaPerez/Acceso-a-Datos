package main;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import objetos.Cita;
import objetos.DatosPaciente;
import objetos.Paciente;

public class Main {

	public static void main(String[] args) {
		List<DatosPaciente> pacientes = leerDatosPacientes("datos_pacientes.txt");
		//Mostrar el .txt
		for (DatosPaciente datosPaciente : pacientes) {
			Paciente paciente = datosPaciente.getPaciente();

			System.out.println("ID: " + paciente.getId());
			System.out.println("Nombre: " + paciente.getNombre() + " " + paciente.getApellido1() + " " + paciente.getApellido2());
			System.out.println("Nacimiento: " + paciente.getNacimiento());
			System.out.println("Residencia: " + paciente.getResidencia());
			System.out.println("Citas:");
			for (Cita cita : paciente.getCitas()) {
				System.out.println("#" + cita.getCentro() + ". " + cita.getEspecialidad() + ". " + cita.getDoctor()
						+ ". El " + cita.getFecha() + " a las " + cita.getHora());
			}
			System.out.println();
			crearCarpetasYArchivos(datosPaciente);
		}
		      
	}




	private static void crearCarpetasYArchivos(DatosPaciente datosPaciente) {
		 // Crea la carpeta con el ID del paciente
		String idPaciente = String.format("%09d", Integer.parseInt(datosPaciente.getPaciente().getId()));
		File carpetaPaciente = new File("Pacientes/" + idPaciente);
		carpetaPaciente.mkdirs();

		//  Crea DatosPersonales
		File datosPersonalesXML = new File(carpetaPaciente, "DatosPersonales.xml");
		escribirDatosPersonalesXML(datosPaciente.getPaciente(), datosPersonalesXML);

		// Crea Citas
		File citasXML = new File(carpetaPaciente, "Citas.xml");
		escribirCitasXML(datosPaciente.getPaciente().getCitas(), citasXML);
	    }
		
	

	private static void escribirCitasXML(List<Cita> citas, File citasXML) {
		try (FileWriter fr = new FileWriter(citasXML)) {
			fr.write("<Citas>\n");
	        for (Cita cita : citas) {
	            fr.write("  <Cita>\n");
	            fr.write("    <Centro>" + cita.getCentro() + "</Centro>\n");
	            fr.write("    <Especialidad>" + cita.getEspecialidad() + "</Especialidad>\n");
	            fr.write("    <Doctor>" + cita.getDoctor() + "</Doctor>\n");
	            fr.write("    <Fecha>" + cita.getFecha() + "</Fecha>\n");
	            fr.write("    <Hora>" + cita.getHora() + "</Hora>\n");
	            fr.write("  </Cita>\n");
	        }
	        fr.write("</Citas>\n");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}




	private static void escribirDatosPersonalesXML(Paciente paciente, File datosPersonalesXML) {
		 try (FileWriter fw = new FileWriter(datosPersonalesXML)) {
			 fw.write("<DatosPersonales>\n");
			 fw.write("  <ID>" + paciente.getId() + "</ID>\n");
			 fw.write("  <Nombre>" + paciente.getNombre() + " " + paciente.getApellido1() + " " + paciente.getApellido2() + "</Nombre>\n");
			 fw.write("  <Nacimiento>" + paciente.getNacimiento() + "</Nacimiento>\n");
			 fw.write("  <Residencia>" + paciente.getResidencia() + "</Residencia>\n");
			 fw.write("</DatosPersonales>\n");
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		
	}




	private static List<DatosPaciente> leerDatosPacientes(String filePath) {
		List<DatosPaciente> pacientes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String linea;
			Paciente paciente = null;

			// comprobar si es cita o paciente
			// si empieza por # la linea es cita
			while ((linea = br.readLine()) != null) {
				if (linea.startsWith("#")) {
					//si empieza por # se llama al metodo  parseCita
					Cita cita = parseCita(linea.substring(1));
					paciente.getCitas().add(cita);
				} else {
					//si no empieza por # se llama al metodo parsePaciente
					paciente = parsePaciente(linea);
					pacientes.add(new DatosPaciente(paciente));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return pacientes;
	}
	
	
// crear objeto paciente
	private static Paciente parsePaciente(String linea) {
		String[] datos = linea.split(";");
		Paciente paciente = new Paciente();
		paciente.setId(datos[0]);
		paciente.setNombre(datos[1]);
		paciente.setApellido1(datos[2]);
		paciente.setApellido2(datos[3]);
		paciente.setNacimiento(datos[4]);
		paciente.setResidencia(datos[5]);
		paciente.setCitas(new ArrayList<>());
		return paciente;
	}

	// crear objeto cita
	private static Cita parseCita(String linea) {
		String[] datos = linea.split(";");
		Cita cita = new Cita();
		cita.setCentro(datos[0]);
		cita.setEspecialidad(datos[1]);
		cita.setDoctor(datos[2]);
		cita.setFecha(datos[3]);
		cita.setHora(datos[4]);
		return cita;
	}
	

}
				