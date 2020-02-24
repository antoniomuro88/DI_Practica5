package practica4;

import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

/**
 * Esta clase crea la ventana para elegir entre consultar todos los artículos de la base de datos o generar un documento PDF
 * con todos los artículos de la base de datos.
 * @author: Antonio MuRo
 * @version: 20/02/2020/A

 */

public class Articulos2 extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;


	/**
	 * Constructor por defecto en el que creamos el frame y los JButton.
	 */
	
	public Articulos2() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("VER ARTICULOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblArtculos = new JLabel("VER ART\u00CDCULOS");
		lblArtculos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArtculos.setBounds(96, 22, 123, 23);
		panel.add(lblArtculos);

		JButton btnConsulta = new JButton("Generar Informe");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// Compilar el informe generando fichero jasper
					String nomfich = "InformeArticulos.jrxml";
					JasperCompileManager.compileReportToFile(nomfich);
					System.out.println("¡Fichero '" + nomfich + "' generado con éxito!");
					// Objeto para guardar parámetros necesarios para el informe
					HashMap<String, Object> parametros = new HashMap<String, Object>();

					// Cargar el informe compilado
					JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("InformeArticulos.jasper");

					// Conectar a la base de datos para sacar la información
					Class.forName("com.mysql.cj.jdbc.Driver");

					String servidor = "jdbc:mysql://localhost:3306/tiendecita?serverTimezone=UTC";
					String usuarioDB = "root";
					String passwordDB = "Studium2019;";
					Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

					// Completar el informe con los datos de la base de datos
					JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

					// Mostrar el informe en JasperViewer
					//JasperViewer.viewReport(print, false);

					// Para exportarlo a pdf
					JasperExportManager.exportReportToPdfFile(print, "InformeArticulos.pdf");

					// Abrir el fichero PDF generado
					File path = new File("InformeArticulos.pdf");
					Desktop.getDesktop().open(path);
				} catch (Exception o) {
					System.out.println("Error: " + o.toString());
				}

			}
				
			}
		);
		btnConsulta.setBounds(168, 78, 135, 23);
		panel.add(btnConsulta);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Articulos arti = new Articulos();
				arti.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setIcon(
				new ImageIcon(Articulos2.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_3.setBounds(10, 11, 49, 25);
		panel.add(btnNewButton_3);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultaArt consart = new ConsultaArt();
				consart.setVisible(true);
				setVisible(false);
			}
		});
		btnConsultar.setBounds(10, 78, 131, 23);
		panel.add(btnConsultar);
	}
}
