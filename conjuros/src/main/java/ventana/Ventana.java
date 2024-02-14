package ventana;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.MainCliente;

public class Ventana extends JFrame {
	
	private static final long serialVersionUID = 1L;

	// Área de mensajes
	public final JTextArea mensajes = new JTextArea();
	// Prompt de envío                                                                                 
	private final JTextArea prompt = new JTextArea();
	// Botón de envío
	private final JButton boton = new JButton();
	private final JScrollPane panel1 = new JScrollPane(mensajes);
	private final JScrollPane panel2 = new JScrollPane(prompt);
	

	public Ventana() {
		// Distribución de los componentes por zonas de la ventana
		setLayout(new BorderLayout());

		panel1.setPreferredSize(new Dimension(400, 200));
		panel2.setPreferredSize(new Dimension(200, 30));
		boton.setPreferredSize(new Dimension(20, 30));
		prompt.setToolTipText("Escribe aquí tu mensaje...");
		boton.setText("Enviar");
		boton.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				// Método que se ejecuta cuando hagamos click
				enviar();
			}

		});

		add(panel1, BorderLayout.NORTH);
		add(panel2, BorderLayout.CENTER);
		add(boton, BorderLayout.SOUTH);

		pack();

		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void enviar() {
		String mensaje = prompt.getText();

		// Mostrar mensaje en el área de texto
		mensajes.append("Yo: " + mensaje + "\n");
		// Borrar el mensaje enviado del prompt
		prompt.setText("");
		
		MainCliente.enviarMensaje(mensaje);
		// TODO CÓDIGO DE ENVÍO DE MENSAJE
	}

	

	

}
