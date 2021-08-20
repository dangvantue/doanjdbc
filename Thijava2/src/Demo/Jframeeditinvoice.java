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
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Jframeeditinvoice extends JFrame {

	private JPanel contentPane;
	private JTextField JtextfieldId;
	private JTextField JtextfieldName;
	private JTextField JtextfieldTotal;
	private JButton ButtonSave;
	private JDateChooser DatechooserCreated;
	private Map<String, Object> data;
	private JComboBox<Category> ComboBoxPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframeeditinvoice frame = new Jframeeditinvoice();
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
	public Jframeeditinvoice() {
		setTitle("Edit invoice");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 584, 393);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setBounds(10, 30, 104, 30);
		contentPane.add(lblNewLabel);
		
		JtextfieldId = new JTextField();
		JtextfieldId.setEditable(false);
		JtextfieldId.setBounds(124, 33, 124, 25);
		contentPane.add(JtextfieldId);
		JtextfieldId.setColumns(10);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 71, 104, 30);
		contentPane.add(lblName);
		
		JtextfieldName = new JTextField();
		JtextfieldName.setColumns(10);
		JtextfieldName.setBounds(124, 74, 124, 25);
		contentPane.add(JtextfieldName);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setBounds(10, 111, 104, 30);
		contentPane.add(lblPayment);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(10, 152, 104, 30);
		contentPane.add(lblTotal);
		
		JtextfieldTotal = new JTextField();
		JtextfieldTotal.setColumns(10);
		JtextfieldTotal.setBounds(124, 155, 124, 25);
		contentPane.add(JtextfieldTotal);
		
		JLabel lblNewLabel_3_1 = new JLabel("Created");
		lblNewLabel_3_1.setBounds(10, 199, 104, 30);
		contentPane.add(lblNewLabel_3_1);
		
		DatechooserCreated = new JDateChooser();
		DatechooserCreated.setBounds(124, 199, 129, 30);
		contentPane.add(DatechooserCreated);
		
		ButtonSave = new JButton("Save");
		ButtonSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonSave_actionPerformed(e);
			}
		});
		ButtonSave.setBounds(138, 263, 89, 23);
		contentPane.add(ButtonSave);
		
		ComboBoxPayment = new JComboBox<Category>();
		ComboBoxPayment.setBounds(124, 115, 124, 26);
		contentPane.add(ComboBoxPayment);
	}
	public Jframeeditinvoice(Map<String,Object> data) {
		this();
		this.data = data;
		loadCategoryPaymentData();
		loadData();
		
	}
	
	
	public void ButtonSave_actionPerformed(ActionEvent e) {
		int id =Integer.parseInt(data.get("id").toString());
		
		Invoice invoice = new Invoice();
		invoice.setId(Integer.parseInt(JtextfieldId.getText().toString()));
		invoice.setName(JtextfieldName.getText());
		
		Category categorypayment = (Category) ComboBoxPayment.getSelectedItem();
		String paymentId = categorypayment.getId();
		invoice.setPaymentId(paymentId);
		
		invoice.setTotal(Double.parseDouble(JtextfieldTotal.getText().toString()));
		invoice.setCreated(DatechooserCreated.getDate());
		
		InvoiceModel invoicemodel = new InvoiceModel();
		if(invoicemodel.update(invoice)) {
			JOptionPane.showMessageDialog(null,"Done");
			Map<String,Object> data = new HashMap<String,Object>();
			data.put("id", id);
			Jframemain jframemain = new Jframemain();
			jframemain.setVisible(true);
			this.setVisible(false);
	
		}else {
			JOptionPane.showMessageDialog(null,"Faield");
		}
	}
		
	private void loadData() {
		int id =Integer.parseInt(data.get("id").toString());
		InvoiceModel invoicemodel = new InvoiceModel();
		Invoice invoice = invoicemodel.FindID(id);
		
		JtextfieldId.setText(String.valueOf(id));
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
