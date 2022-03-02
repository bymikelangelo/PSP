package cliente;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import comun.PaqueteEnvio;
import servidor.Servidor;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Color;

public class FrameCliente extends JFrame {

	private JPanel contentPane;
	private Cliente cliente;
	private JTextArea textArea;
	private JTextField textField;
	private JLabel lblSalaChat;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//inicia el frame y un objeto Servidor en un hilo
				try {
					FrameCliente frame = new FrameCliente("CLIENTE");
					frame.setVisible(true);
					frame.cliente = (new Cliente(frame));
					new Thread(frame.cliente).start();
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
	public FrameCliente(String nombre) {
		super(nombre);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setBounds(10, 42, 514, 240);
		contentPane.add(textArea);
		
		JButton btnSalir = new JButton("SALIR");
		btnSalir.setBackground(Color.ORANGE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//cierra las conexiones con el cliente antes de cerrar el programa
				try {
					if (cliente.isConectado()) {
						cliente.cerrarConexion();
					}
					//JOptionPane.showMessageDialog(null, "Cerradas todas las conexiones.");
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
		
		lblSalaChat = new JLabel("Sala de chat");
		lblSalaChat.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSalaChat.setBounds(10, 11, 454, 20);
		contentPane.add(lblSalaChat);
		
		textField = new JTextField();
		textField.setToolTipText("");
		textField.setBounds(10, 293, 415, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnEnviar = new JButton("ENVIAR");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String mensaje = textField.getText();
				if (!mensaje.equals("") || mensaje != null) {
					try {
						cliente.enviarMensaje(mensaje);
						mostrarMensajePropio(mensaje);
						textField.setText("");
						
					} catch (IOException e1) {
						e1.printStackTrace();
						mostrarPanelMensaje(e1.getMessage());
					}
				}
			}
		});
		btnEnviar.setBackground(Color.CYAN);
		btnEnviar.setBounds(435, 293, 89, 23);
		contentPane.add(btnEnviar);
	}
	
	//OptionPane que pide el nick al usuario necesario para el servidor
	public String introducirNick() {
		return JOptionPane.showInputDialog(null, "Introduce nick de usuario:");
	}
	
	//muestra notificaciones en un OptionPane
	public void mostrarPanelMensaje(String mensaje) {
		JOptionPane.showMessageDialog(null, mensaje);
	}
	
	//muestra en el area de texto un mensaje del emisor
	public void mostrarMensajePropio(String mensaje) {
		textArea.append("Tú: " + mensaje + "\n");
	}
	
	//muestra en el area de texto los mensajes recibidos por los otros clientes
	public void mostrarMensaje(PaqueteEnvio paquete) {
		textArea.append(paquete.getNick() + ": " + paquete.getMensaje() + "\n");
	}
	
	public void ponerNombre() {
		lblSalaChat.setText("Sala de chat (Cliente: " + cliente.getNick() + ")");
	}
}
