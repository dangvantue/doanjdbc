package Demo;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import entities.Category;
import entities.Invoice;
import model.CategoryPaymentModel;
import model.InvoiceModel;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;


import javax.swing.JComboBox;

public class Jframemain extends JFrame {

	private JPanel contentPane;
	private JTable ListTableInvoice;
	private JButton ButtonDetail;
	private JButton JtextfieldAdd;
	private JButton Buttonedit;
	private JButton ButtonDeleted;
	private JLabel lblNewLabel;
	private JTextField JtextfieldNumber;
	private JButton ButtonSearch;
	private JDateChooser Datechooser1;
	private JDateChooser Datechooser2;
	private JButton ButtonSearchDate;
	private JTextField JtextfieldCount;
	private JTextField JtextfieldSum;
	private JComboBox ComboBoxPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jframemain frame = new Jframemain();
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
	public Jframemain() {
		setTitle("Main");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 69, 406, 144);
		contentPane.add(scrollPane);

		ListTableInvoice = new JTable();
		scrollPane.setViewportView(ListTableInvoice);
		
		ButtonDetail = new JButton("Detail");
		ButtonDetail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonDetail_actionPerformed(e);
			}
		});
		ButtonDetail.setBounds(22, 237, 89, 23);
		contentPane.add(ButtonDetail);
		
		JtextfieldAdd = new JButton("Add");
		JtextfieldAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JtextfiedlAdd_actionPerformed(e);
			}
		});
		JtextfieldAdd.setBounds(121, 237, 89, 23);
		contentPane.add(JtextfieldAdd);
		
		Buttonedit = new JButton("Edit");
		Buttonedit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonEdit_actionPerformed(e);
			}
		});
		Buttonedit.setBounds(231, 237, 113, 23);
		contentPane.add(Buttonedit);
		
		ButtonDeleted = new JButton("Deleted");
		ButtonDeleted.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buttondeleted_actionPerformed(e);
			}
		});
		ButtonDeleted.setBounds(360, 237, 89, 23);
		contentPane.add(ButtonDeleted);
		
		lblNewLabel = new JLabel("Input number n:");
		lblNewLabel.setBounds(22, 19, 113, 23);
		contentPane.add(lblNewLabel);
		
		JtextfieldNumber = new JTextField();
		JtextfieldNumber.setBounds(121, 20, 76, 20);
		contentPane.add(JtextfieldNumber);
		JtextfieldNumber.setColumns(10);
		
		ButtonSearch = new JButton("Search");
		ButtonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonSearch_actionPerformed(e);
			}
		});
		ButtonSearch.setBounds(231, 16, 70, 28);
		contentPane.add(ButtonSearch);
		
		Datechooser1 = new JDateChooser();
		Datechooser1.setBounds(500, 22, 89, 20);
		contentPane.add(Datechooser1);
		
		Datechooser2 = new JDateChooser();
		Datechooser2.setBounds(500, 69, 89, 20);
		contentPane.add(Datechooser2);
		
		ButtonSearchDate = new JButton("SearchDate");
		ButtonSearchDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ButtonSearchDate_actionPerformed(e);
			}
		});
		ButtonSearchDate.setBounds(500, 213, 90, 28);
		contentPane.add(ButtonSearchDate);
		
		JtextfieldCount = new JTextField();
		JtextfieldCount.setBounds(500, 112, 86, 20);
		contentPane.add(JtextfieldCount);
		JtextfieldCount.setColumns(10);
		
		JtextfieldSum = new JTextField();
		JtextfieldSum.setColumns(10);
		JtextfieldSum.setBounds(500, 160, 86, 20);
		contentPane.add(JtextfieldSum);
		
		JLabel lblNewLabel_1 = new JLabel("Count");
		lblNewLabel_1.setBounds(444, 115, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sum");
		lblNewLabel_1_1.setBounds(444, 163, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date 2");
		lblNewLabel_2.setBounds(444, 70, 46, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Date 1");
		lblNewLabel_2_1.setBounds(444, 23, 46, 14);
		contentPane.add(lblNewLabel_2_1);
		
		ComboBoxPayment = new JComboBox();
		ComboBoxPayment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ComboBoxPayment_actionPerFormed(e);
			}
		});
		ComboBoxPayment.setBounds(331, 19, 101, 23);
		contentPane.add(ComboBoxPayment);
		loadData();
		loadCategoryPaymentData();
	}
	public void ComboBoxPayment_actionPerFormed(ActionEvent e) {
		
		Category categorypayment = new Category();
		categorypayment = (Category) ComboBoxPayment.getSelectedItem();
		String payment_id = categorypayment.getId();
		
		InvoiceModel invoicemodel = new InvoiceModel();
		List<Invoice> invoices = new ArrayList<Invoice>();
		invoices = invoicemodel.findByTotal(payment_id);
		FillDataListTableInvoice(invoices);
		
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
	
	public void ButtonSearchDate_actionPerformed(ActionEvent e) {
		InvoiceModel invoicemodel = new InvoiceModel();
		Date date1 = Datechooser1.getDate();
		Date date2 = Datechooser2.getDate();
		List<Invoice>invoice = new ArrayList<Invoice>();
		invoice = invoicemodel.findByDates(date1, date2);
		System.out.println(invoice);
		int count = 0;
		double sum = 0;
		for(Invoice i : invoice) {
			sum = sum + i.getTotal();
			count++;
		}
		JtextfieldSum.setText(String.valueOf(sum));
		JtextfieldCount.setText(String.valueOf(count));
	}
	
	
	
	
	
	public void ButtonSearch_actionPerformed(ActionEvent e) {
		InvoiceModel invoicemodel = new InvoiceModel();
		int n = Integer.parseInt(JtextfieldNumber.getText());
		FillDataListTableInvoice(invoicemodel.limit(n));
	}
	
	
	public void Buttondeleted_actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null,"Confrim","Are you sure ?", JOptionPane.YES_NO_OPTION);
		if(result == JOptionPane.YES_OPTION) {
			int selectedRow = ListTableInvoice.getSelectedRow();
			int id = Integer.parseInt(ListTableInvoice.getValueAt(selectedRow,0).toString());
			InvoiceModel invoicemodel = new InvoiceModel();
			if(invoicemodel.deleted(id)) {
				JOptionPane.showMessageDialog(null,"Done");
				FillDataListTableInvoice(invoicemodel.findAll());
				}
			else {
				JOptionPane.showMessageDialog(null,"Failed");
			}
		}

	}
	
	public void ButtonEdit_actionPerformed(ActionEvent e) {
		
		int selectedRow = ListTableInvoice.getSelectedRow();
		int id = Integer.parseInt(ListTableInvoice.getValueAt(selectedRow,0).toString());
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id",id);
		Jframeeditinvoice jframeEdit = new Jframeeditinvoice(data);
		jframeEdit.setVisible(true);
		this.setVisible(false);
	}
	
	public void JtextfiedlAdd_actionPerformed(ActionEvent e) {
		Jframeaddinvoce addinvoice = new Jframeaddinvoce();
		addinvoice.setVisible(true);
		this.setVisible(false);
	}
	
	
	private void loadData() {
		InvoiceModel invoicemodel = new InvoiceModel();
		FillDataListTableInvoice(invoicemodel.findAll());
	}

	private void FillDataListTableInvoice(List<Invoice> invoices) {

		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.addColumn("id");
		defaultTableModel.addColumn("name");
		defaultTableModel.addColumn("payment");
		defaultTableModel.addColumn("total (1.000ð)");
		defaultTableModel.addColumn("created");

		for (Invoice invoice : invoices) {
			defaultTableModel.addRow(
					new Object[] { invoice.getId(), invoice.getName(), invoice.getPaymentId(),invoice.getTotal(), invoice.getCreated() });
		}
		ListTableInvoice.setModel(defaultTableModel);
		ListTableInvoice.getTableHeader().setReorderingAllowed(false);
	}
	
	public void ButtonDetail_actionPerformed(ActionEvent e){
		int selectedRow = ListTableInvoice.getSelectedRow();
		int id = Integer.parseInt(ListTableInvoice.getValueAt(selectedRow,0).toString());
		
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("id",id);
		
		Jframedetailinvoice jframedetail = new Jframedetailinvoice(data);
		jframedetail.setVisible(true);
	}
}
