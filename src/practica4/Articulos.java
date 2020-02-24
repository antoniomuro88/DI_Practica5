package practica4;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

/**

 * Esta clase crea la ventana Articulos en la que se muestran los botones
 * para insertar, mofificar, elminar y ver artículos.

 * @author: Antonio MuRo
 * @version: 20/02/2020/A

 */
public class Articulos extends JFrame {

	//Declaración de variables.
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Creamos el frame "Articulos" y los botones con sus correspondientes propiedades.
	 */

	public Articulos() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("ARTICULOS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Se crea una instancia JPanel
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//Se crea una instancia JButton "Insertar"
		JButton btnNewButton = new JButton("Insertar");
		btnNewButton.addActionListener(new ActionListener() {
			//Cuando se pulsa el botón se muestra ventana y se cierra esta.
			public void actionPerformed(ActionEvent e) {
				InsertarArticulo insArt = new InsertarArticulo();
				insArt.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 73, 89, 23);
		panel.add(btnNewButton);
		
		//Se crea una instancia JLabel "ARTÍCULOS"
		JLabel lblArtculos = new JLabel("ART\u00CDCULOS");
		lblArtculos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArtculos.setBounds(111, 22, 89, 23);
		panel.add(lblArtculos);
		
		//Se crea una instancia JButton "Ver artículos"
		JButton btnConsulta = new JButton("Ver Art\u00EDculos");
		btnConsulta.addActionListener(new ActionListener() {
			//Cuando se pulsa el botón se muestra ventana y se cierra esta.
			public void actionPerformed(ActionEvent e) {
				Articulos2 consart2 = new Articulos2();
				consart2.setVisible(true);
				setVisible(false);
			}
		});
		btnConsulta.setBounds(96, 112, 117, 23);
		panel.add(btnConsulta);
		
		//Se crea una instancia JButton "Volver"
		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			//Cuando se pulsa el botón se muestra ventana y se cierra esta.
			public void actionPerformed(ActionEvent arg0) {
				Tiendecita principal = new Tiendecita();
				principal.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setIcon(
				new ImageIcon(Articulos.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_3.setBounds(10, 11, 49, 25);
		panel.add(btnNewButton_3);
		
		//Se crea una instancia JButton "Modificar"
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			//Cuando se pulsa el botón se muestra ventana y se cierra esta.
			public void actionPerformed(ActionEvent e) {
				ModificarArt modiselart = new ModificarArt();
				modiselart.setVisible(true);
				setVisible(false);

			}
		});
		btnModificar.setBounds(111, 73, 89, 23);
		panel.add(btnModificar);
		
		//Se crea una instancia JButton "Eliminar"
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			//Cuando se pulsa el botón se muestra ventana y se cierra esta.
			public void actionPerformed(ActionEvent e) {
				EliminarArt eliminart = new EliminarArt();
				eliminart.setVisible(true);
				setVisible(false);
			}
		});
		btnEliminar.setBounds(214, 73, 89, 23);
		panel.add(btnEliminar);
	}
}
