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

public class Tickets2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Tickets2() {
		setType(Type.UTILITY);
		setResizable(false);
		setTitle("CONSULTAR TICKETS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 329, 185);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Consultar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ConsultaTicket consutic = new ConsultaTicket();
				consutic.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(10, 73, 132, 23);
		panel.add(btnNewButton);

		JLabel lblArtculos = new JLabel("TICKETS");
		lblArtculos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblArtculos.setBounds(119, 22, 67, 23);
		panel.add(lblArtculos);

		JButton btnConsulta = new JButton("Generar Informe");
		btnConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TicketsDesdeHasta contidh = new TicketsDesdeHasta();
				contidh.setVisible(true);
				setVisible(false);
			}
		});
		btnConsulta.setBounds(163, 73, 140, 23);
		panel.add(btnConsulta);

		JButton btnNewButton_3 = new JButton("");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Tickets tic = new Tickets();
				tic.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setIcon(
				new ImageIcon(Tickets2.class.getResource("/com/sun/javafx/scene/web/skin/Undo_16x16_JFX.png")));
		btnNewButton_3.setBounds(22, 11, 49, 25);
		panel.add(btnNewButton_3);
	}
}
