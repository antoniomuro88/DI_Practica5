package practica4;

import java.awt.BorderLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

import javax.swing.JButton;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.border.LineBorder;
import java.awt.Color;

import javax.swing.ImageIcon;

import javax.swing.JLayeredPane;
import java.awt.Font;
import javax.swing.JScrollPane;

/**
 * 
 * Esta clase crea la ventana para crear nuevos tickets.
 * 
 * @author: Antonio MuRo
 * @version: 20/02/2020/A
 * 
 */
public class NuevoTicket extends JFrame {

	private static final long serialVersionUID = 1L;

	/** Panel JPanel que contiene el resto de elementos */
	private JPanel contentPane;

	/** Campo de texto para la cantidad del articulo */
	private JTextField txtCantidad;

	/** Campo de texto para el precio del articulo */
	private JTextField txtPrecio;

	/** Campo de texto para la fecha del articulo */
	private JTextField txtFecha;

	/** Campo de texto para el total del articulo */
	private JTextField txtTotal;

	/** Tabla JTable para meter la cesta de la compra de la tienda */
	private JTable modelo;

	Connection conexion = null;
	PreparedStatement prepStat = null;
	ResultSet reSet = null;
	Connection conexion1 = null;
	PreparedStatement prepStat1 = null;
	@SuppressWarnings("rawtypes")
	Statement s = null;
	ResultSet reSet1 = null;
	int vacio = 0;

	/**
	 * Constructor por defecto en el que creamos el frame donde aparecerán el panel,
	 * los campos y los botones de NuevoTicket.
	 */

	public NuevoTicket() {
		setType(Type.UTILITY);
		setTitle("Nuevo Ticket");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 318);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		txtCantidad = new JTextField();
		txtCantidad.setText("1");
		txtCantidad.setBounds(209, 145, 58, 20);
		txtCantidad.setHorizontalAlignment(SwingConstants.CENTER);
		txtCantidad.setColumns(10);

		JComboBox<String> combo = new JComboBox<String>();

		combo.setBounds(10, 36, 181, 20);
		ArrayList<String> ArrayId = new ArrayList<String>();
		ArrayList<String> ArrayName = new ArrayList<String>();
		ArrayList<String> ArrayPrice = new ArrayList<String>();
		ArrayList<String> ArrayCuant = new ArrayList<String>();

		String sql = ("Select * from articulos");
		conexion = Conexion.conectar();
		try {

			prepStat = conexion.prepareStatement(sql);

			ResultSet reSet = prepStat.executeQuery(sql);
			if (reSet.next() == true) {
				reSet.previous();
				while (reSet.next()) {
					String name = reSet.getString("descripcionArticulo");
					ArrayName.add(name);
					String id = reSet.getString("idArticulo");
					ArrayId.add(id);
					String price = reSet.getString("precioArticulo");
					ArrayPrice.add(price);
					String cuant = reSet.getString("cantidadArticulo");
					ArrayCuant.add(cuant);

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
				JOptionPane.showConfirmDialog(null,
						"La tabla 'Artículos está vacía'. Ve a la pantalla 'Insertar Artículos'", "No hay artículos",
						JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Error al consultar: " + e2);
		}
		txtPrecio = new JTextField();
		txtPrecio.setBounds(207, 95, 58, 20);
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setText("");
		txtPrecio.setColumns(10);

		JLabel lblNewLabel = new JLabel("Fecha");
		lblNewLabel.setBounds(442, 11, 66, 14);

		JLabel lblNewLabel_1 = new JLabel("Art\u00EDculos");
		lblNewLabel_1.setBounds(72, 11, 89, 14);

		JLabel lblNewLabel_2 = new JLabel("Precio");
		lblNewLabel_2.setBounds(218, 79, 64, 14);

		JButton btnInsertar = new JButton("A\u00F1adir a la cesta");
		btnInsertar.setBounds(20, 200, 149, 23);

		btnInsertar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				String zid = "";
				String znom = "";
				String zpre = "";
				String zcan = "";

				int ex = combo.getSelectedIndex();
				for (int a = 0; a < ArrayName.size(); a++) {

					String ex1 = ArrayName.get(a);
					String ex2 = ArrayId.get(a);
					String ex3 = ArrayPrice.get(a);
					String ex4 = ArrayCuant.get(a);
					if (ex == a) {
						zid = ex2;
						znom = ex1;
						zpre = ex3;
						zcan = ex4;

					}

				}

				DefaultTableModel model = (DefaultTableModel) modelo.getModel();

				@SuppressWarnings("rawtypes")
				Vector<Comparable> row = new Vector<Comparable>();
				row.add(znom);
				row.add(zpre);
				int numArt = Integer.parseInt(txtCantidad.getText());
				if (numArt > 0) {
					row.add(numArt);
				} else {

					numArt = 1;
					txtCantidad.setText("" + numArt);
					row.add(numArt);
				}
				Float precio = Float.parseFloat(zpre);
				DecimalFormat df = new DecimalFormat("0.00");
				df.setMaximumFractionDigits(2);
				zpre = df.format(precio);
				double total = precio * numArt;

				row.add(total);
				model.addRow(row);

				double t = 0;
				double p = 0;
				if (modelo.getRowCount() > 0) {
					for (int i = 0; i < modelo.getRowCount(); i++) {
						p = Double.parseDouble(modelo.getValueAt(i, 3).toString());
						t += p;

					}

					txtTotal.setText("" + t);

				}
				System.out.println(" -------------------- Cesta de la compra --------------------");
				System.out.println("NOMBRE: " + znom + " ID: " + zid + " PRECIO: " + zpre + " CANTIDAD: " + numArt
						+ " STOCK: " + zcan + "\n -------------------- TOTAL--------------------\n" + t);

			}
		});

		combo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("unused")
				String zid = "";
				@SuppressWarnings("unused")
				String znom = "";
				String zpre = "";
				@SuppressWarnings("unused")
				String zcan = "";

				int ex = combo.getSelectedIndex();

				for (int a = 0; a < ArrayName.size(); a++) {

					String ex1 = ArrayName.get(a);
					String ex2 = ArrayId.get(a);
					String ex3 = ArrayPrice.get(a);
					String ex4 = ArrayCuant.get(a);
					if (ex == a) {
						zid = ex2;
						znom = ex1;
						zpre = ex3;
						zcan = ex4;

					}

				}

				/*
				 * System.out.println("HA SELECCIONADO EL COMBO NUMERO: " + ex +
				 * " NUMERO DE REGISTRO: " + z + " ID: " + zid + " NOMBRE: " + znom +
				 * " PRECIO: " + zpre + " CANTIDAD: " + zcan);
				 */

				txtPrecio.setText(zpre + " €");
			}
		});

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Tickets tick = new Tickets();
				tick.setVisible(true);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(47, 234, 89, 23);

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(212, 125, 64, 14);

		txtFecha = new JTextField();
		txtFecha.setBounds(478, 8, 66, 20);
		txtFecha.setHorizontalAlignment(SwingConstants.CENTER);
		txtFecha.setColumns(10);

		JLabel lblCestaDeLa = new JLabel("Cesta de la compra");
		lblCestaDeLa.setBounds(301, 11, 121, 14);
		panel.setLayout(null);
		panel.add(lblNewLabel_1);
		panel.add(lblNewLabel_2);
		panel.add(lblCestaDeLa);
		panel.add(combo);
		panel.add(txtPrecio);
		panel.add(lblCantidad);
		panel.add(txtCantidad);
		panel.add(btnInsertar);
		panel.add(lblNewLabel);
		panel.add(btnCancelar);
		panel.add(txtFecha);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String fechaComoCadena = sdf.format(new Date());
		txtFecha.setText(fechaComoCadena);
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBorder(new LineBorder(Color.GRAY, 2));
		layeredPane.setBackground(Color.WHITE);
		layeredPane.setBounds(288, 36, 256, 187);
		panel.add(layeredPane);

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(230, 38, 16, 14);
		layeredPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DefaultTableModel model = (DefaultTableModel) modelo.getModel();

				double t = 0;
				double p = 0;

				int numRows = modelo.getSelectedRows().length;
				if (modelo.getRowCount() > 0) {
					if (modelo.getSelectedRowCount() > 0) {
						for (int i = 0; i < numRows; i++) {

							model.removeRow(modelo.getSelectedRow());
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Debes seleccionar un artículo de la cesta de la compra. Puedes seleccionar varios artículos dejando pulsado el botón 'Ctrl'.");
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"La cesta de la compra está vacía. Prueba añadiendo artículos ");
				}

				for (int i = 0; i < modelo.getRowCount(); i++) {
					p = Double.parseDouble(modelo.getValueAt(i, 3).toString());
					t += p;

				}
				txtTotal.setText("" + t);
			}
		});

		btnNewButton_1.setIcon(new ImageIcon(
				NuevoTicket.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-error.png")));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 218, 137);
		layeredPane.add(scrollPane);

		modelo = new JTable();
		scrollPane.setViewportView(modelo);
		modelo.setBorder(new LineBorder(new Color(0, 0, 0)));
		modelo.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Art\u00EDculo", "Precio", "Cantidad", "Total" }));
		modelo.getColumnModel().getColumn(0).setPreferredWidth(110);
		modelo.getColumnModel().getColumn(1).setPreferredWidth(40);
		modelo.getColumnModel().getColumn(2).setPreferredWidth(52);
		modelo.getColumnModel().getColumn(3).setPreferredWidth(35);

		txtTotal = new JTextField();
		txtTotal.setBounds(101, 164, 58, 20);
		layeredPane.add(txtTotal);
		txtTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtTotal.setColumns(10);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTotal.setBounds(113, 150, 57, 14);
		layeredPane.add(lblTotal);

		JButton btnTotalizar = new JButton("TOTALIZAR");
		btnTotalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model = (DefaultTableModel) modelo.getModel();

				int rows = model.getRowCount();

				System.out.println("" + rows + " articulos en la cesta de la compra.");
				if (rows > 0) {
					for (int row = 0; row < rows; row++) {

						String desc = model.getValueAt(row, 0).toString();
						@SuppressWarnings("unused")
						String prec = model.getValueAt(row, 1).toString();
						String cuant = model.getValueAt(row, 2).toString();
						String total = model.getValueAt(row, 3).toString();
						String fecha = fechaComoCadena;

						try {
							String sql = ("Insert into Ticket (descripcionArticuloFK, fechaTicket, totalTicket, cantidadArtiTick, idArticuloFK) values (?,?,?,?, (SELECT idArticulo FROM articulos WHERE descripcionArticulo = ?))");
							conexion = Conexion.conectar();

							total = txtTotal.getText();
							prepStat = conexion.prepareStatement(sql);
							prepStat.setString(1, desc);
							prepStat.setString(2, fecha);
							prepStat.setString(3, total);
							prepStat.setString(4, cuant);
							prepStat.setString(5, desc);

							prepStat.addBatch();
							prepStat.executeBatch();
							conexion.setAutoCommit(false);
							conexion.commit();

						}

						catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Error al Insertar. " + ex);
						}
					}

					JOptionPane.showMessageDialog(null, "¡La venta ha sido realizada!");
					System.out.println("Se ha creado un ticket con " + rows + " artículos");
					model.setRowCount(0);
					modelo.validate();
					txtTotal.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "La cesta de la compra está vacía. Prueba a añadir artículos.");
				}
			}

		});
		btnTotalizar.setBounds(342, 234, 149, 23);
		panel.add(btnTotalizar);
		combo.setSelectedIndex(0);
	}
}