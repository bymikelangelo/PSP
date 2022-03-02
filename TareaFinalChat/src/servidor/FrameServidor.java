package servidor;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FrameServidor extends JFrame {

	private JPanel contentPane;
	private Servidor servidor;
	private JTextArea textArea;
	private JList<String> listadoClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//inicia el frame y un objeto Servidor en un hilo
				try {
					FrameServidor frame = new FrameServidor("SERVIDOR");
					frame.setVisible(true);
					frame.servidor = (new Servidor(frame));
					new Thread(frame.servidor).start();
				} catch (Exception e) {  //salta si existe algun fallo al iniciar la aplicacion
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e.getMessage());
					System.exit(0);
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameServidor(String nombre) {
		super(nombre);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 42, 454, 274);
		contentPane.add(textArea);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBackground(Color.ORANGE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cierra todas las conexiones antes de cerrar el programa
				try {
					servidor.cerrarServer();
					JOptionPane.showMessageDialog(null, "Cerradas todas las conexiones.");
					btnSalir.setText("CERRAR");
					btnSalir.setBackground(Color.RED);
					//permite cerrar la ventana del programa
					btnSalir.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSalir.setBounds(10, 327, 89, 23);
		contentPane.add(btnSalir);
		
		listadoClientes = new JList<String>();
		listadoClientes.setBounds(474, 42, 200, 274);
		contentPane.add(listadoClientes);
		
		JLabel lblSalaChat= new JLabel("Sala de chat");
		lblSalaChat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSalaChat.setBounds(10, 11, 454, 20);
		contentPane.add(lblSalaChat);
		
		JLabel lblClientesConectados = new JLabel("Clientes conectados");
		lblClientesConectados.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblClientesConectados.setBounds(474, 10, 200, 20);
		contentPane.add(lblClientesConectados);
	}
	
	//muestra el mensaje en el area de texto
	public void mostrarMensaje(String mensaje) {
		textArea.append(mensaje + "\n");
	}
	
	/*
	 * muestra una lista con los clientes conectados en el momento.
	 * Recibe los datos de los clientes almacenados en el gestor
	 */
	public void mostrarClientes(Set<DatosCliente> clientes) {
		DefaultListModel<String> modeloClientes = new DefaultListModel<>();
		if (clientes.size() > 0) {
			for (DatosCliente cliente : clientes) {
				modeloClientes.addElement(cliente.toString());
			}
			listadoClientes.setModel(modeloClientes);
		}
		else {
			listadoClientes.setModel(modeloClientes);
		}
	}
}
