package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;

public class ConectarBBDD extends JFrame {
	
	Connection conexion;		
	String url = "jdbc:mysql://54.201.30.39:3306/Personas";
	String user = "armando";
	String pw = "679470184zote";
	private JPanel contentPane;

	public ConectarBBDD() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Conectando con mi BBDD");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblNewLabel);
		
		JLabel lblPulsaEnConectar = new JLabel("Pulsa en conectar");
		contentPane.add(lblPulsaEnConectar);
		
		JLabel label = new JLabel("");
		contentPane.add(label);
		
		JButton btnConectar = new JButton("Conectar");
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
								
					ResultSet conjuntoResultados = instruccion.executeQuery("SELECT * FROM Personas");
					while (conjuntoResultados.next())
						System.out.println("nombre: "+ conjuntoResultados.getObject("Nombre") + " Edad "
								+ conjuntoResultados.getObject("edad"));
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
		contentPane.add(btnConectar);
		
		JTextArea textArea = new JTextArea();
		
		contentPane.add(textArea);
	}

}
