package practica4;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
/**

 * Esta clase crea la ventana Consulta de Articulos en la que se muestran todos los artículos
 * de la base de datos en una Jtable.

 * @author: Antonio MuRo
 * @version: 20/02/2020/A

 *

 */
public class ConsultaArt extends JFrame {

	private static final long serialVersionUID = 1L;
	
	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;
	
	/** Tabla JTable donde se volcarán los datos de los artículos.*/
	private JTable modelo;

	/** Variable donde se guardará el result de la conexión a la base de datos */
	Connection conexion = null;
	
	/** Variable donde se guardará la sentencia sql */
	PreparedStatement prepStat = null;
	
	/** Variable donde se guardará el resultado de la ejecución de la sentencia sql */
	ResultSet reSet = null;

	/**
	 * Creamos el frame donde aparecerán el panel y los botones.
	 */
	public ConsultaArt() {
		setType(Type.UTILITY);
		setTitle("Consultar Art\u00EDculos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 669, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Se crea una instancia JPanel
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//Se crea una instancia JButton "Volver"
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Articulos2 artic2 = new Articulos2();
				artic2.setVisible(true);
				setVisible(false);
			}
		});
		btnVolver.setBounds(285, 261, 89, 23);
		panel.add(btnVolver);
		
		//Se crea una instancia Jlabel "Lista de Artículos"
		JLabel lblListaDeArtculos = new JLabel("Lista de Art\u00EDculos");
		lblListaDeArtculos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeArtculos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblListaDeArtculos.setBounds(54, 11, 543, 36);
		panel.add(lblListaDeArtculos);
		
		//Se crea una instancia JScrollPane
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 58, 543, 173);
		panel.add(scrollPane);
		
		//Se crea una instancia JTable
		modelo = new JTable();
		scrollPane.setViewportView(modelo);
		//LLama al método conectar() de la clase Conexion.
		conexion = Conexion.conectar();
		String sql = "select * from articulos";
		//Intenta
		try {
			PreparedStatement pst = conexion.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			System.out.println("Mostrando Artículos...");
			if (rs.next() == true) {
				rs.previous();
				modelo.setModel(DbUtils.resultSetToTableModel(rs));
			}

			else {

				JOptionPane.showMessageDialog(null, "la tabla está vacía");

				lblListaDeArtculos.setText("La lista de artículos está vacía");
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
