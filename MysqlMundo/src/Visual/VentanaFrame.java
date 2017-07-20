package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.awt.event.ActionEvent;

public class VentanaFrame extends JFrame {

	private JPanel contentPane;
	Connection conexion;		
	String url = "jdbc:mysql://mydatabase.c45ldqay1ebi.us-west-2.rds.amazonaws.com:3306/Mundo";
	String user = "armando";
	String pw = "679470184zote";
	
	public VentanaFrame() {
		setTitle("BBDD_MUNDO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		JButton btnConectar = new JButton("CONECTAR");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e1) {			
					e1.printStackTrace();
				}

				// obtenemos la conexión con el DriverManager
				try {
					conexion = DriverManager.getConnection(url, user, pw);
					System.out.println("Conexión realizada correctamente usando DriverManager");
					Statement instruccion = (Statement) conexion.createStatement();
								
					ResultSet conjuntoResultados = instruccion.executeQuery("SELECT * FROM Paises");
					while (conjuntoResultados.next())
						System.out.println("Nombre: "+ conjuntoResultados.getObject("Nombre") + " Capital: "
								+ conjuntoResultados.getObject("capital") + " N.Habitantes : "+ conjuntoResultados.getObject("n.habitantes"));
					conexion.close();
				} catch (SQLException e1) {			
					e1.printStackTrace();
				}

				// obtenemos el driver del controlador desde el DriverManager
				try {
					Driver driver = DriverManager.getDriver(url);
					Properties properties = new Properties();
					properties.setProperty("user", user);
					properties.setProperty("password", pw);
					conexion = driver.connect(url, properties);
					System.out.println("Conexión realizada correctamente usando Driver");
					conexion.close();

				} catch (SQLException ex) {
					System.err.println("Error " + ex.getMessage());
				}
				
			}
		});
		btnConectar.setBackground(Color.GREEN);
		contentPane.add(btnConectar);
		
		JLabel lblConectandoConLa = new JLabel("CONECTANDO CON LA BBDD MUNDO");
		contentPane.add(lblConectandoConLa);
	}

}
