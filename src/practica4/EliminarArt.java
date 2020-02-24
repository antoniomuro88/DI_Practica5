package practica4;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

/**
 * 
 * Esta clase crea la ventana para eliminar art�culos seleccionados en una lista
 * desplegable.
 * 
 * @author: Antonio MuRo
 * @version: 20/02/2020/A
 * 
 */

public class EliminarArt extends JFrame {

	private static final long serialVersionUID = -2948429002419309357L;

	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;

	/** Variable donde se guardar� el result de la conexi�n a la base de datos */
	Connection conexion = null;

	/** Crea una variable del tipo PreparedStatement */
	PreparedStatement prepStat = null;

	/** Crea una variable del tipo PreparedStatement */
	PreparedStatement prepStat1 = null;

	/**
	 * Variable donde se guardar� el resultado de la ejecuci�n de la sentencia sql
	 */
	ResultSet reSet1 = null;

	/**
	 * Variable donde se guardar� el resultado de la ejecuci�n de la otra sentencia
	 * sql
	 */
	ResultSet reSet = null;

	/** Variable donde se guardar� la cantidad de art�culos ne la base de datos */
	int vacio = 0;

	/**
	 * Creamos el frame donde aparecer�n el panel y los botones.
	 */

	@SuppressWarnings("unchecked")
	public EliminarArt() {
		setType(Type.UTILITY);
		setTitle("Elininar Art\u00EDculo");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 217, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		// Se crea una instancia JPanel
		JPanel panel = new JPanel();

		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		// Se crea una instancia JCombobox
		@SuppressWarnings("rawtypes")
		JComboBox combo = new JComboBox();
		combo.setBounds(10, 59, 181, 20);
		panel.add(combo);

		// Se crea una instancia ArrayList
		ArrayList<String> ArrayId = new ArrayList<String>();

		// Se crea una instancia ArrayList
		ArrayList<String> ArrayName = new ArrayList<String>();

		/** Variable que guarda la sentencia sql. */
		String sql = ("Select idArticulo, descripcionArticulo from articulos");

		// Conecta a la base da datos
		conexion = Conexion.conectar();

		// Intenta ejecutar la sentencia sql en el servidor mysql
		try {

			prepStat = conexion.prepareStatement(sql);

			ResultSet reSet = prepStat.executeQuery(sql);

			// Si hay resultado
			if (reSet.next() == true) {

				reSet.previous();

				// Mientras que haya datos
				while (reSet.next()) {

					// Los a�ade al array
					String id = reSet.getString("idArticulo");
					ArrayId.add(id);
					String name = reSet.getString("descripcionArticulo");
					ArrayName.add(name);

					// Si la descripion est� vac�a
					if (name.equals("")) {

						// No muestra nada
						combo.addItem("");
						combo.setVisible(false);

						// Si no esta vac�a
					} else {

						// Muestra la descripci�n en el combobox
						combo.addItem(reSet.getString("descripcionArticulo"));

						combo.setVisible(true);
					}
				}
				// Si no hay resultado
			} else {

				// Asigna 1 a la variable vacio
				vacio = 1;
				// Muestra mensaje avisando de que est� vac�o
				JOptionPane.showMessageDialog(null,
						"La tabla 'Art�culos est� vac�a'. Ve a la pantalla 'Insertar Art�culos'");
			}
			// En caso de error
		} catch (Exception e2) {
			// Muestra mensaje
			JOptionPane.showMessageDialog(null, "Error al intentar insertar: " + e2);
		}

		// Se crea una instancia Jlabel
		JLabel lblSeleccionaArtculoA = new JLabel("Selecciona art\u00EDculo a eliminar");
		lblSeleccionaArtculoA.setBounds(10, 23, 201, 14);
		panel.add(lblSeleccionaArtculoA);

		// Se crea una instancia JButton
		JButton btnInsertar = new JButton("Eliminar");
		// Se crea un listener en el bot�n insertar
		btnInsertar.addActionListener(new ActionListener() {
			// Cuando el bot�n se pulsa
			public void actionPerformed(ActionEvent arg0) {
				// Aparece un mensaje con dos opciones
				int resp = JOptionPane.showConfirmDialog(null,
						"Est� apunto de borrar un registro de manera permanente. �Est� seguro?", "�Atenci�n!",
						JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
				// Si eliges SI
				if (resp == 0) {

					/*
					 * Variable entera que indica el n�mero del combobox seleccionado teniendo el
					 * primero valor 0
					 */
					int ex = combo.getSelectedIndex();

					/* Variable donde guardaremos el numero de registro */
					int z = 0;

					/* Variable donde guardaremos el id del art�culo */
					String zid = "";

					/* Variable donde guardaremos el nombre del art�culo */
					String znom = "";

					// Recorre el array completo
					for (int a = 0; a < ArrayName.size(); a++) {

						String ex1 = ArrayName.get(a);
						String ex2 = ArrayId.get(a);

						// Si el n�mero de combo es igual a el n�mero del registro del array
						if (ex == a) {

							// Guarda el n�mero de registro en z
							z = a;

							// Guarda el id del art�culo en zid
							zid = ex2;

							// Guarda el nombre del art�culo en znom
							znom = ex1;
						}

					}
					// Muestra en consola los datos del combo seleccionado
					System.out.println("COMBO NUMERO: " + ex + " NUMERO DE REGISTRO: " + z + " ID DEL REGISTRO: " + zid
							+ " NOMBRE DEL REGISTRO: " + znom);
					// Guarda la sentencia sql para borrar articulos en sql1
					String sql1 = ("delete from articulos WHERE idArticulo = ?");

					// Conecta a mysql
					conexion = Conexion.conectar();

					// Intenta conectar
					try {
						prepStat1 = conexion.prepareStatement(sql1);
						prepStat1.setString(1, zid);

						int resultado = prepStat1.executeUpdate();
						if (resultado > 0) {
							int respo = JOptionPane.showConfirmDialog(null,
									"El registro se ha borrado correctamente. �Desea seguir borrando art�culos?",
									"�Exito�", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
							ArrayName.remove(ex);
							ArrayId.remove(ex);
							if (respo == 0) {
								ResultSet reSet = prepStat.executeQuery(sql);

								if (reSet.next() == true) {

									reSet.previous();
									if (combo.getSelectedIndex() > -1)
										combo.removeAllItems();
									while (reSet.next()) {

										String id = reSet.getString("idArticulo");
										ArrayId.add(id);
										String name = reSet.getString("descripcionArticulo");
										ArrayName.add(name);

										if (name.equals("")) {
											combo.addItem("");
											combo.setVisible(false);

										} else {
											combo.addItem(reSet.getString("descripcionArticulo"));

											combo.setVisible(true);
										}
									}
								} else {
									vacio = 1;
									JOptionPane.showMessageDialog(null,
											"La tabla 'Art�culos est� vac�a'. Ve a la pantalla 'Insertar Art�culos'");
								}

							} else {
								Articulos artic = new Articulos();
								artic.setVisible(true);
								setVisible(false);
							}
						}

					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Error al intentar borrar: " + e);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Has pulsado NO");
				}
			}
		});
		btnInsertar.setBounds(56, 211, 89, 23);
		panel.add(btnInsertar);

		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String zid = "";
				String znom = "";

				int ex = combo.getSelectedIndex();

				for (int a = 0; a < ArrayName.size(); a++) {

					String ex1 = ArrayName.get(a);
					String ex2 = ArrayId.get(a);

					if (ex == a) {
						zid = ex2;
						znom = ex1;

					}

				}

				System.out.println("COMBO NUMERO: " + ex + " ID: " + zid + " NOMBRE: " + znom);

			}
		});

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Articulos artic = new Articulos();
				artic.setVisible(true);
				setVisible(false);

			}
		});
		btnNewButton.setBounds(56, 245, 89, 23);
		panel.add(btnNewButton);

		combo.setSelectedIndex(0);

	}
}
