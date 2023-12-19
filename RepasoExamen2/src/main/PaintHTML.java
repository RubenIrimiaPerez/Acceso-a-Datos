package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PaintHTML {

	ArrayList<Persona> arrayPersonas = new ArrayList<Persona>();
	
	public void CREAR(ArrayList<Persona> arrayPersonas) {
		
		try {
		
		
			FileWriter fr = new FileWriter("personas.html");
			BufferedWriter bw = new BufferedWriter(fr);

			bw.write("<!DOCTYPE html>");
			bw.write("<html>");
			bw.write("<head>");
			bw.write("    <title>Personas</title>");
			bw.write("    <link rel='stylesheet' type='text/css' href='personas.css'>");
			bw.write("<style>");
			bw.write(".persona {"
					+ "    display: inline-block;"
					+ "    border: 1px black solid;"
					+ "    padding: 10px;\r\n"
					+ "}");
			
			bw.write(".persona > * {"
					+ "    margin: 10px;"
					+ "}");
			bw.write("img {\r\n"
					+ "    width: 100px;"
					+ "}");
			bw.write(".avatar, .eliminar {"
					+ "    text-align: center;"
					+ "}\r\n"
					+ "");
			bw.write(".resaltar {"
					+ "    font-weight: bold;"
					+ "}");	
			bw.write("</style>");
			bw.write("</head>");
			bw.write("<body>");

			for (Persona p : arrayPersonas) {

				bw.write("    <div class=\"persona\" id=\""+p.getId()+">");
				bw.write("        <div class=\"avatar\"><img src=\""+p.getUrlImg()+"></div>");
				bw.write("        <div><span class=\"resaltar\">" + p.getNombre() + "</span> Juan</div>");
				bw.write("        <div><span class=\"resaltar\">" + p.getApellido1() + " " + p.getApellido2()+ "</span> Fernández López</div>");
				bw.write("        <div><span class=\"resaltar\">"+p.getEdad()+"</span> 22</div>");
				bw.write("        <div class=\"eliminar\"><button onclick=\"this.parentElement.parentElement.remove();\">Borrar</button></div>");
				bw.write("    </div>");

			}

			bw.write("</body>");
			bw.write("</html>");

			bw.close();
			fr.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
