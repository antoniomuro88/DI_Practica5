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
 * 
 * Esta clase crea la ventana que permite acceder a las operaciones con los
 * tickets.
 * 
 * @author: Antonio MuRo
 * @version: 20/02/2020/A
 * 
 */

public class Tickets extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;

	/**
	 * Constructor por defecto en el que creamos el frame donde aparecerán el panel
	 * y los botones: 'Nuevo' y 'Ver Tickets'.
	 */
	public Tickets() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("TICKETS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Nuevo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				NuevoTicket nuevoTi = new NuevoTicket();
				nuevoTi.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(22, 73, 120, 23);
		panel.add(btnNewButton);

		JLabel lblArtculos = new JLabel("TICKETS");
		lblArtculos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArtculos.setBounds(119, 22, 67, 23);
		panel.add(lblArtculos);

		JButton btnConsulta = new JButton("Ver Tickets");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Tickets2 tick2 = new Tickets2();
				tick2.setVisible(true);
				setVisible(false);
			}
		});
		btnConsulta.setBounds(172, 73, 120, 23);
		panel.add(btnConsulta);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tiendecita principal = new Tiendecita();
				principal.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3
				.setIcon(new ImageIcon(Tickets.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_3.setBounds(22, 11, 49, 25);
		panel.add(btnNewButton_3);
	}
}
