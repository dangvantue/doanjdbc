package Demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import entities.Category;
import entities.Invoice;

import model.CategoryPaymentModel;
import model.InvoiceModel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Jframeaddinvoce extends JFrame {

	private JPanel contentPane;
	private JTextField JtextfieldName;
	private JTextField JtextfieldTotal;
	private JButton ButtonSave;
	private JDateChooser DatechooserCreated;
	private JComboBox ComboBoxPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframeaddinvoce frame = new Jframeaddinvoce();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Jframeaddinvoce() {
		setTitle("AddInvoice");
		setBounds(100, 100, 541, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JtextfieldName = new JTextField();
		JtextfieldName.setBounds(115, 31, 151, 27);
		contentPane.add(JtextfieldName);
		JtextfieldName.setColumns(10);

		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setBounds(10, 69, 95, 27);
		contentPane.add(lblPayment);

		JtextfieldTotal = new JTextField();
		JtextfieldTotal.setColumns(10);
		JtextfieldTotal.setBounds(115, 110, 151, 27);
		contentPane.add(JtextfieldTotal);

		JLabel lblNewLabel_2_1 = new JLabel("Created");
		lblNewLabel_2_1.setBounds(10, 148, 95, 27);
		contentPane.add(lblNewLabel_2_1);

		DatechooserCreated = new JDateChooser();
		DatechooserCreated.setBounds(115, 148, 151, 27);
		contentPane.add(DatechooserCreated);

		ButtonSave = new JButton("Save");
		ButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonSave_actionPerformed(e);
			}
		});
		ButtonSave.setBounds(135, 218, 89, 23);
		contentPane.add(ButtonSave);

		JLabel lblNewLabel_1_1 = new JLabel("Name");
		lblNewLabel_1_1.setBounds(10, 31, 95, 27);
		contentPane.add(lblNewLabel_1_1);

		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 110, 95, 27);
		contentPane.add(lblTotal);

		ComboBoxPayment = new JComboBox();
		ComboBoxPayment.setBounds(115, 71, 151, 25);
		contentPane.add(ComboBoxPayment);
		
		loadCategoryPaymentData();
	}

	public void ButtonSave_actionPerformed(ActionEvent e) {
		Invoice invoice = new Invoice();
		invoice.setName(JtextfieldName.getText());
		
		Category categorypayment = (Category) ComboBoxPayment.getSelectedItem();
		invoice.setPaymentId(categorypayment.getId());

		invoice.setTotal(Double.parseDouble(JtextfieldTotal.getText().toString()));
		invoice.setCreated(DatechooserCreated.getDate());

		InvoiceModel invoicemodel = new InvoiceModel();
		if (invoicemodel.Create(invoice)) {
			JOptionPane.showMessageDialog(null, "Done");
			Jframemain jframemain = new Jframemain();
			jframemain.setVisible(true);
			this.setVisible(false);
		} else {
			JOptionPane.showMessageDialog(null, "Failed");
		}

	}

	private void loadCategoryPaymentData() {
		DefaultComboBoxModel<Category> categorypayment = new DefaultComboBoxModel<Category>();
		CategoryPaymentModel categorypaymentmodel = new CategoryPaymentModel();
		for (Category cate : categorypaymentmodel.findAll()) {
			categorypayment.addElement(cate);
		}
		ComboBoxPayment.setModel(categorypayment);
		ComboBoxPayment.setRenderer(new CategoryListcellrender());
	}

	private class CategoryListcellrender extends DefaultListCellRenderer {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Category cate = (Category) value;
			return super.getListCellRendererComponent(list, cate.getName(), index, isSelected, cellHasFocus);
		}

	}

}
