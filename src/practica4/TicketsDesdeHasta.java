package practica4;

import java.awt.BorderLayout;
import java.awt.Desktop;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import com.toedter.calendar.JDateChooser;

/**
 * 
 * Esta clase crea la ventana para generar un documento PDF de tickets en un
 * rango de fechas seleccionados mediante JDateChooser usando JasperReports.
 * 
 * @author: Antonio MuRo
 * @version: 20/02/2020/A
 * 
 */

public class TicketsDesdeHasta extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;

	/**
	 * Constructor por defecto en el que creamos el frame y los JDateChooser.
	 */

	public TicketsDesdeHasta() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("TICKETS - DESDE/HASTA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JDateChooser hasta = new JDateChooser();
		hasta.setDateFormatString("yyyy-MM-dd");
		JDateChooser desde = new JDateChooser();
		desde.setDateFormatString("yyyy-MM-dd");

		JLabel lblTickets = new JLabel("TICKETS");
		lblTickets.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblTickets.setBounds(119, 22, 67, 23);
		panel.add(lblTickets);

		JButton btnConsulta = new JButton("Generar PDF");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (desde.getDate() == null || hasta.getDate() == null) {
					JOptionPane.showMessageDialog(null, "Primero debe indicar el rango de fechas");
				}

				else if (desde.getDate().after(hasta.getDate())
						|| ChronoUnit.DAYS.between(desde.getDate().toInstant(), hasta.getDate().toInstant()) == 0) {
					JOptionPane.showMessageDialog(null, "la fecha 'Desde' debe ser ANTERIOR a la fecha 'Hasta'");
				} else {
					try {
						// Compilar el informe generando fichero jasper
						String nomfich = "report3.jrxml";
						JasperCompileManager.compileReportToFile(nomfich);
						System.out.println("¡Fichero '" + nomfich + "' generado con éxito!");
						// Objeto para guardar parámetros necesarios para el informe
						HashMap<String, Object> parametros = new HashMap<String, Object>();

						parametros.put("desde", desde.getDate());
						parametros.put("hasta", hasta.getDate());

						// Cargar el informe compilado
						JasperReport report = (JasperReport) JRLoader.loadObjectFromFile("report3.jasper");

						// Conectar a la base de datos para sacar la información
						Class.forName("com.mysql.cj.jdbc.Driver");

						String servidor = "jdbc:mysql://localhost:3306/tiendecita?serverTimezone=UTC";
						String usuarioDB = "root";
						String passwordDB = "Studium2019;";
						Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

						// Completar el informe con los datos de la base de datos
						JasperPrint print = JasperFillManager.fillReport(report, parametros, conexion);

						// Mostrar el informe en JasperViewer
						// JasperViewer.viewReport(print, false);

						// Para exportarlo a pdf
						JasperExportManager.exportReportToPdfFile(print, "InformeFechaTickets.pdf");

						// Abrir el fichero PDF generado
						File path = new File("InformeFechaTickets.pdf");
						Desktop.getDesktop().open(path);
					} catch (Exception o) {
						System.out.println("Error: " + o.toString());
					}

				}
			}
		});
		btnConsulta.setBounds(94, 112, 122, 23);
		panel.add(btnConsulta);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tickets2 tic2 = new Tickets2();
				tic2.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(
				TicketsDesdeHasta.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_3.setBounds(22, 11, 49, 25);
		panel.add(btnNewButton_3);

		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(80, 56, 46, 14);
		panel.add(lblDesde);

		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(185, 56, 46, 14);
		panel.add(lblHasta);

		desde.setBounds(44, 81, 95, 20);
		panel.add(desde);

		hasta.setBounds(154, 81, 95, 20);
		panel.add(hasta);
	}
}
