package objects;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class HiloLectorArchivo extends Thread {
	
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();

	public HiloLectorArchivo(ArrayList<Paciente> pacientes) {
		this.pacientes = pacientes;
		
	}


	@Override
	public void run() {
		System.out.println("Cargando archivos...");
		
		try {
			FileReader fr = new FileReader("pacientes.txt");
			BufferedReader br = new BufferedReader(fr);
			String linea = br.readLine();
			while (linea != null) {

				// añade el paciente y luego añadira las citas

				String[] camposPaciente = linea.split(";");
				Paciente p = new Paciente(camposPaciente[0], camposPaciente[1], camposPaciente[2], camposPaciente[3],
						camposPaciente[4], camposPaciente[5]);
				
				linea = br.readLine();

				while (linea != null && linea.charAt(0) == '#') {
					String[] camposCita = linea.split(";");
					Cita c = new Cita(camposCita[0], camposCita[1], camposCita[2], camposCita[3], camposCita[4]);
					p.getCitas().add(c);
					linea = br.readLine();

				}
				
						// se muestrea el paciente
						System.out.println("ID: " + p.getId());
						System.out.println("Nombre: " + p.getNombre() + " " + p.getApellido1() + " "
								+ p.getApellido2());
						System.out.println("Nacimiento:" + p.getNacimiento());
						System.out.println("Residencia: " + p.getResidencia());
						System.out.println("Citas: ");

						// citas
						for (int i = 0; i < p.getCitas().size(); i++) {
							System.out.println("> " + p.getCitas().get(i).getHospital() + ". "
									+ p.getCitas().get(i).getDoctor() + ". El"
									+ p.getCitas().get(i).getFechaCita() + " a las "
									+ p.getCitas().get(i).getHoraCita());
						}
						System.out.println();
					
				
				
				synchronized (pacientes) {
					  pacientes.add(p);
					  pacientes.notifyAll();
				}
				
			
			}

			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
