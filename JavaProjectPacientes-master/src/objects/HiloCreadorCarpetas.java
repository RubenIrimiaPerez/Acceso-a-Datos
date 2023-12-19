package objects;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class HiloCreadorCarpetas extends Thread{
	
	private ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
	private HiloLectorArchivo hLector;
	
	public HiloCreadorCarpetas(ArrayList<Paciente> pacientes,HiloLectorArchivo hLector) {
		this.hLector = hLector;
		this.pacientes = pacientes;
		
	}

	@Override
	public void run() {
		System.out.println("Creando carpetas");
		
		 while(hLector.isAlive() || pacientes.size() != 0) {
			 
			synchronized (pacientes) {
				if(pacientes.size() == 0){
					 try {
						pacientes.wait();
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				 }
			}
			 
			//siempre vamos a pillar la primera posicion del arraylist de pacientes, al final de crear los xml, se elimina el paciente del arraylist para que el siguiente pase a la posicion 0
				
				//creo la ruta donde se creara la carpeta con ese dni
				String path = "Pacientes/" + pacientes.get(0).getId() + "/";
				File carpeta = new File(path);

				// creamos la carpeta con el id del paciente
				carpeta.mkdir();

				try {
					FileWriter fw = new FileWriter(path + "Datos Personales.xml", true);
					PrintWriter pw = new PrintWriter(fw, true);
					pw.write("<Paciente>\n");
					pw.write("<id>" + pacientes.get(0).getId() + "</id>\n");
					pw.write("<Nombre>" + pacientes.get(0).getNombre() + "</Nombre>\n");
					pw.write("<Apellido>" + pacientes.get(0).getApellido1() + "</Apellido>\n");
					pw.write("<Apellido2>" + pacientes.get(0).getApellido2() + "</Apellido2>\n");
					pw.write("<Nacimiento>" + pacientes.get(0).getNacimiento() + "</Nacimiento>\n");
					pw.write("<Localidad>" + pacientes.get(0).getResidencia() + "</Localidad>\n");
					pw.write("</Paciente>\n");

					pw.close();
					fw.close();

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					FileWriter fw = new FileWriter(path + "Citas.xml", true);
					PrintWriter pw = new PrintWriter(fw, true);
					pw.write("<Citas>\n");
					for(int i = 0;i<pacientes.get(0).getCitas().size();i++) {
						
						
						pw.write("\t<Cita>\n");
						pw.write("\t\t<Centro>" + pacientes.get(0).getCitas().get(i).getHospital() + "</Centro>\n");
						pw.write("\t\t<Especialidad>" + pacientes.get(0).getCitas().get(i).getDepartamento() + "</Especialidad>\n");
						pw.write("\t\t<Doctor>" +  pacientes.get(0).getCitas().get(i).getDoctor()  + "</Doctor>\n");
						pw.write("\t\t<Fecha>" +  pacientes.get(0).getCitas().get(i).getFechaCita() + "</Fecha>\n");
						pw.write("\t\t<Hora>" +  pacientes.get(0).getCitas().get(i).getHoraCita() + "</Hora>\n");
						pw.write("\t</Cita>\n");

						
					}
					pw.write("</Citas>");
					pw.close();
					fw.close();
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 synchronized (pacientes) {
					//una vez creado los dos xml se elimina de la lista de pacientes.
						pacientes.remove(0);
					}
				 
				
		 }
		 
		
			
	}
}
