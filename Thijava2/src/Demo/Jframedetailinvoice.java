package Demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import entities.Category;
import entities.Invoice;

import model.CategoryPaymentModel;
import model.InvoiceModel;


import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;

public class Jframedetailinvoice extends JFrame {

	private JPanel contentPane;
	private JTextField JtextfieldId;
	private JTextField JtextfieldName;
	private JTextField JtextfieldTotal;
	private JDateChooser DatechooserCreated;
	private Map<String,Object> data;
	private JComboBox ComboBoxPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframedetailinvoice frame = new Jframedetailinvoice();
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
	public Jframedetailinvoice() {
		setTitle("InvoiceDetail");
		setBounds(100, 100, 550, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 33, 71, 14);
		contentPane.add(lblNewLabel);
		
		JtextfieldId = new JTextField();
		JtextfieldId.setEditable(false);
		JtextfieldId.setBounds(91, 30, 114, 20);
		contentPane.add(JtextfieldId);
		JtextfieldId.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 78, 71, 14);
		contentPane.add(lblName);
		
		JtextfieldName = new JTextField();
		JtextfieldName.setEditable(false);
		JtextfieldName.setColumns(10);
		JtextfieldName.setBounds(91, 75, 114, 20);
		contentPane.add(JtextfieldName);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setBounds(10, 124, 71, 14);
		contentPane.add(lblPayment);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 169, 71, 14);
		contentPane.add(lblTotal);
		
		JtextfieldTotal = new JTextField();
		JtextfieldTotal.setEditable(false);
		JtextfieldTotal.setColumns(10);
		JtextfieldTotal.setBounds(91, 166, 114, 20);
		contentPane.add(JtextfieldTotal);
		
		JLabel lblCreated = new JLabel("Created");
		lblCreated.setBounds(10, 213, 71, 14);
		contentPane.add(lblCreated);
		
		DatechooserCreated = new JDateChooser();
		DatechooserCreated.setBounds(91, 207, 114, 20);
		contentPane.add(DatechooserCreated);
		
		ComboBoxPayment = new JComboBox();
		ComboBoxPayment.setBounds(91, 120, 114, 20);
		contentPane.add(ComboBoxPayment);
	}
	public Jframedetailinvoice(Map<String,Object>data) {
		this();
		this.data = data;
		loadCategoryPaymentData();
		loadData();
		
	}
	
	private void loadData() {
		int id =Integer.parseInt(data.get("id").toString());
		InvoiceModel invoices = new InvoiceModel();
		Invoice invoice = invoices.FindID(id);
		
		JtextfieldId.setText(String.valueOf(invoice.getId()));
		JtextfieldName.setText(invoice.getName());
		
		CategoryPaymentModel categorypaymentmodel = new CategoryPaymentModel();
		Category categorypayment = categorypaymentmodel.findId(invoice.getPaymentId());
		ComboBoxPayment.getModel().setSelectedItem(categorypayment);
		
		JtextfieldTotal.setText(String.valueOf(invoice.getTotal()));
		DatechooserCreated.setDate(invoice.getCreated());
		
		
	}
	
	private void loadCategoryPaymentData() {
		DefaultComboBoxModel<Category> categorypayment = new DefaultComboBoxModel<Category>();
		CategoryPaymentModel categorypaymentmodel = new CategoryPaymentModel();
		for(Category cate : categorypaymentmodel.findAll()) {
			categorypayment.addElement(cate);
		}
		ComboBoxPayment.setModel(categorypayment);
		ComboBoxPayment.setRenderer(new CategoryListcellrender());	
	}
	private class CategoryListcellrender extends DefaultListCellRenderer{

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			Category cate = (Category) value;
			return super.getListCellRendererComponent(list, cate.getName(), index, isSelected, cellHasFocus);
		}
		
		}
	
}
