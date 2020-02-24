package practica4;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * Esta clase crea la ventana principal de la aplicación "Tiendecita".
 * 
 * @author: Antonio MuRo
 * @version: 20/02/2020/A
 * 
 */
public class Tiendecita extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;

	/**
	 * Este es el método principal el cual crea el frame Tiendecita.
	 * 
	 * @param args No se utiliza.
	 * @exception Exception Lanza excepción si falla la ejecución.
	 * @see Exception
	 */
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tiendecita frame = new Tiendecita();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor por defecto en el que creamos el frame donde aparecerán el panel
	 * y los botones de la ventana principal.
	 */

	public Tiendecita() {
		setType(Type.UTILITY);
		setTitle("Tiendecita");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 141, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("Art\u00EDculos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Articulos artic = new Articulos();
				artic.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(21, 23, 89, 23);
		panel.add(btnNewButton);

		JButton btnTickets = new JButton("Tickets");
		btnTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tickets tick = new Tickets();
				tick.setVisible(true);
				setVisible(false);
			}
		});
		btnTickets.setBounds(21, 57, 89, 23);
		panel.add(btnTickets);
	}
}
