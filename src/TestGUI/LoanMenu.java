package TestGUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ControllerLayer.AccountController;
import ControllerLayer.LoanController;
import ControllerLayer.ProductController;
import ModelLayer.Account;
import ModelLayer.Product;

public class LoanMenu extends JPanel {

	private CardLayout parent;
	private JPanel parentPanel;
	private LoanController loanCtr;
	private AccountController accountCtr;
	private ProductController productCtr;
	private JTabbedPane tabbedPane;
	private JTable itemTable;
	private JTable customerTable;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private boolean status = false;
	private JDialog dialog;
	private int id;
	private String phone;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_5;
	private JLabel label_6;
	private int barcode;
	private double total;

	public LoanMenu(JPanel mainPanel, CardLayout cardLayout, LoanController lCtr, AccountController aCtr,
			ProductController pCtr) {
		parentPanel = mainPanel;
		parent = cardLayout;
		loanCtr = lCtr;
		accountCtr = aCtr;
		productCtr = pCtr;
		init();
	}

	private void init() {
		setBounds(100, 100, 750, 500);
		setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		tabbedPane.addTab("L�n Menu", null, loanMainMenu(), null);
		tabbedPane.addTab("Liste over L�n", null, loanListPanel(), null);
		tabbedPane.addTab("Opret L�n", null, createLoanPanel(), null);
		tabbedPane.setEnabledAt(1, false);
		tabbedPane.setEnabledAt(2, false);
	}

	private JPanel loanMainMenu() {

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);

		JButton btnSeAlleL�n = new JButton("Se alle L\u00E5n");
		btnSeAlleL�n.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
				tabbedPane.setSelectedIndex(1);

			}
		});
		btnSeAlleL�n.setBounds(142, 193, 144, 64);
		mainPanel.add(btnSeAlleL�n);

		JButton btnSalgUKunde = new JButton("Opret L\u00E5n");
		btnSalgUKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerDialog();
			}
		});
		btnSalgUKunde.setBounds(299, 193, 143, 64);
		mainPanel.add(btnSalgUKunde);

		JButton btnTilbage = new JButton("Tilbage");
		btnTilbage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				parent.show(parentPanel, "1");
			}
		});
		btnTilbage.setBounds(24, 367, 97, 25);
		mainPanel.add(btnTilbage);

		JButton button = new JButton("Returner L\u00E5n");
		button.setBounds(454, 192, 143, 64);
		mainPanel.add(button);

		return mainPanel;
	}

	private JPanel createLoanPanel() {
		JPanel loanPanel = new JPanel();
		loanPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		loanPanel.add(panel_1, BorderLayout.SOUTH);

		JButton btnAfslutSalg = new JButton("Afslut L�n");
		btnAfslutSalg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(2, false);
				clearOrdreData();
			}
		});
		panel_1.add(btnAfslutSalg);

		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setEnabledAt(2, false);
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_1.add(btnAnnuller);

		JLabel lblNewLabel_4 = new JLabel("Total Pris:");
		panel_1.add(lblNewLabel_4);

		label_6 = new JLabel("");
		panel_1.add(label_6);

		JPanel panel = new JPanel();
		JPanel panel_2 = new JPanel();
		loanPanel.add(panel, BorderLayout.CENTER);

		itemTable = new JTable();

		itemTable.setModel(itemTable());
		JScrollPane sp = new JScrollPane();
		sp.setBounds(88, 251, 452, 155);
		sp.setViewportView(itemTable);
		panel.add(sp);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panel, panel_2);
		panel_2.setLayout(null);

		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setBounds(12, 275, 56, 16);
		panel_2.add(lblTelefon);

		JLabel lblAdresse = new JLabel("Adresse");
		lblAdresse.setBounds(12, 161, 56, 16);
		panel_2.add(lblAdresse);

		JLabel lblNewLabel = new JLabel("Navn");
		lblNewLabel.setBounds(12, 123, 56, 16);
		panel_2.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("By");
		lblNewLabel_2.setBounds(12, 200, 56, 16);
		panel_2.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Post Nr");
		lblNewLabel_3.setBounds(12, 236, 56, 16);
		panel_2.add(lblNewLabel_3);

		label = new JLabel("");
		label.setBounds(79, 123, 140, 16);
		panel_2.add(label);

		label_2 = new JLabel("");
		label_2.setBounds(78, 162, 141, 14);
		panel_2.add(label_2);

		label_3 = new JLabel("");
		label_3.setBounds(78, 201, 141, 14);
		panel_2.add(label_3);

		label_4 = new JLabel("");
		label_4.setBounds(78, 236, 131, 14);
		panel_2.add(label_4);

		label_5 = new JLabel("");
		label_5.setBounds(78, 276, 141, 14);
		panel_2.add(label_5);

		JButton btnNewButton = new JButton("Tilf\u00F8j Produkt");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createItem();
				textField_4.setText("");
			}
		});
		btnNewButton.setBounds(66, 62, 153, 25);
		panel_2.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("Barkode");
		lblNewLabel_1.setBounds(12, 30, 56, 16);
		panel_2.add(lblNewLabel_1);

		textField_4 = new JTextField();
		textField_4.setBounds(66, 27, 153, 30);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		loanPanel.add(split);

		return loanPanel;
	}

	private JPanel loanListPanel() {
		JPanel loanPanel = new JPanel();
		loanPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		loanPanel.add(panel_1, BorderLayout.SOUTH);

		JButton btnAfslutSalg = new JButton("Se Kundel�n");
		btnAfslutSalg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setEnabledAt(1, false);
				clearOrdreData();
			}
		});
		panel_1.add(btnAfslutSalg);

		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tabbedPane.setEnabledAt(1, false);
				tabbedPane.setSelectedIndex(0);
			}
		});
		panel_1.add(btnAnnuller);
		customerTable = new JTable();
		refresh();

		JScrollPane sp = new JScrollPane();
		sp.setBounds(88, 251, 452, 155);
		sp.setViewportView(customerTable);
		loanPanel.add(sp);

		return loanPanel;
	}

	public void customerDialog() {
		dialog = new JDialog();

		dialog.setBounds(100, 100, 580, 400);
		dialog.setVisible(true);

		JPanel panel_1 = new JPanel();
		dialog.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblTelefon = new JLabel("Angiv l�ne periode i dage");
		lblTelefon.setBounds(129, 59, 106, 16);
		panel_1.add(lblTelefon);

		JLabel lblAddresse = new JLabel("Telefon");
		lblAddresse.setBounds(129, 108, 106, 16);
		panel_1.add(lblAddresse);

		textField = new JTextField();
		textField.setBounds(253, 56, 210, 32);
		panel_1.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(253, 105, 210, 32);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JLabel label = new JLabel("");
		label.setBounds(213, 193, 210, 16);
		panel_1.add(label);

		JButton btnOpretOrdre = new JButton("Opret L�n");
		btnOpretOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createLoan();
				// setCustomerInfo();
			}
		});
		btnOpretOrdre.setBounds(12, 315, 157, 25);
		panel_1.add(btnOpretOrdre);
	}

	public void createLoan() {
		int day = Integer.parseInt(textField.getText());
		String phone = textField_1.getText();
		System.out.println(day);
		if (JOptionPane.showConfirmDialog(null, "Bekr�ft Oprettelse?", "Advarsel",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			id = loanCtr.createLoan(day, phone);
			System.out.println(id);
			textField.setText(Integer.toString(id));
			tabbedPane.setSelectedIndex(2);
			dialog.setVisible(false);
			dialog.dispose();
		}
	}

	public void setCustomerInfo() {
		Account accountObj = accountCtr.findCustomer(phone);
		label.setText(accountObj.getName());
		label_2.setText(accountObj.getAddress());
		label_3.setText(accountObj.getCity());
		label_4.setText(accountObj.getZip());
		label_5.setText(accountObj.getPhone());
	}

	public void createItem() {
		barcode = Integer.parseInt(textField_4.getText());
		if (productCtr.isUnique(barcode) == true) {
			uniqueProductDialog();
		} else {
			JOptionPane.showMessageDialog(null, "Du er en skovl, dette er ikke unikt");
		}
	}

	public void uniqueProductDialog() {
		JDialog dialog = new JDialog();

		dialog.setBounds(100, 100, 280, 300);
		dialog.setVisible(true);

		JPanel panel_1 = new JPanel();
		dialog.getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(null);

		JLabel lblTelefon = new JLabel("Angiv Serie Nr");
		lblTelefon.setBounds(13, 59, 156, 16);
		panel_1.add(lblTelefon);

		textField_6 = new JTextField();
		textField_6.setBounds(13, 105, 210, 22);
		panel_1.add(textField_6);
		textField_6.setColumns(10);

		JButton btnOpretOrdre = new JButton("Tilf�j");
		btnOpretOrdre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int serial = Integer.parseInt(textField_6.getText());
				System.out.println(serial);
				System.out.println(id);
				loanCtr.addItem(id, barcode, serial);
				Product productObj = productCtr.findSpecificProduct(barcode);

				int tBarcode = productObj.getBarcode();
				String name = productObj.getName();
				String description = productObj.getDescription();
				double price = productObj.getPrice();

				DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
				model.addRow(new Object[] { tBarcode, name, description, price, 1 });
				dialog.setVisible(false);
				dialog.dispose();
			}
		});
		btnOpretOrdre.setBounds(12, 215, 157, 25);
		panel_1.add(btnOpretOrdre);
	}

	public void refresh() {
		customerTable.setModel(customerTable(accountCtr.getAccountsWithLoan()));
		System.out.println("REFRESH");
	}

	public void clearOrdreData() {
		label.setText("");
		label_2.setText("");
		label_3.setText("");
		label_4.setText("");
		label_5.setText("");
		label_6.setText("");
		textField_4.setText("");
		total = 0;
		DefaultTableModel model = (DefaultTableModel) itemTable.getModel();
		model.getDataVector().removeAllElements();
		model.fireTableDataChanged();
		tabbedPane.setSelectedIndex(0);
	}

	public void totalPrice(double p, int amount) {
		int price = (int) p;
		int multiply = price * amount;
		total += multiply;
		label_6.setText(String.valueOf(total));
	}

	public TableModel itemTable() {
		DefaultTableModel model = new DefaultTableModel(
				new Object[] { "Stregkode", "Navn", "Beskrivelse", "Pris", "Antal" }, 0);
		return model;

	}

	public TableModel customerTable(Map<String, Account> map) {

		DefaultTableModel model = new DefaultTableModel(new Object[] { "Navn", "Adresse", "Post Nr", "By", "Telefon" },
				0);
		for (Map.Entry<String, Account> entry : map.entrySet()) {

			model.addRow(new Object[] { entry.getValue().getName(), entry.getValue().getAddress(),
					entry.getValue().getZip(), entry.getValue().getCity(), entry.getValue().getPhone() });
		}
		return model;

	}

}
