package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import entities.Invoice;
import model.ConnectDB;

public class InvoiceModel {
	
	public List<Invoice> findByDates(Date star, Date end){
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM invoice where created >= ? and created <= ?");
			
			preparedstatement.setDate(1,new java.sql.Date(star.getTime()));
			preparedstatement.setDate(2,new java.sql.Date(end.getTime()));
			
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				
				Invoice invoice = new Invoice();
				invoice.setId(resultset.getInt("id"));
				invoice.setName(resultset.getString("name"));
				invoice.setPaymentId(resultset.getString("payment"));
				invoice.setTotal(resultset.getDouble("total"));
				invoice.setCreated(resultset.getDate("created"));
				invoices.add(invoice);
			}
			
		} catch (Exception e) {
			System.out.println("loi o day" +e.toString());
			
		} finally {
			ConnectDB.disconnect();
		}
		return invoices;
	}
	
	
	
	public List<Invoice> findByTotal(String payment_id){
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM invoice WHERE payment = ?");
	
			preparedstatement.setString(1, payment_id);
			
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(resultset.getInt("id"));
				invoice.setName(resultset.getString("name"));
				invoice.setPaymentId(resultset.getString("payment"));
				invoice.setTotal(resultset.getDouble("total"));
				invoice.setCreated(resultset.getDate("created"));
				invoices.add(invoice);
			}
			
		} catch (Exception e) {
			System.out.println("loi o day" +e.toString());
			
		} finally {
			ConnectDB.disconnect();
		}
		return invoices;
	}
	
	
	public List<Invoice> limit(int n){
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("SELECT * FROM invoice order by id desc limit ?");
			preparedstatement.setInt(1, n);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(resultset.getInt("id"));
				invoice.setName(resultset.getString("name"));
				invoice.setPaymentId(resultset.getString("payment"));
				invoice.setTotal(resultset.getDouble("total"));
				invoice.setCreated(resultset.getDate("created"));
				invoices.add(invoice);
			}
			
		} catch (Exception e) {
			System.out.println("loi o day" +e.toString());
			
		} finally {
			ConnectDB.disconnect();
		}
		return invoices;
	}
	
	
	
	public List<Invoice> findAll() {
		List<Invoice> invoices = new ArrayList<Invoice>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from invoice");
			ResultSet result = preparedstatement.executeQuery();
			while (result.next()) {
				Invoice invoice = new Invoice();
				invoice.setId(result.getInt("id"));
				invoice.setName(result.getString("name"));
				invoice.setPaymentId(result.getString("payment"));
				invoice.setTotal(result.getDouble("total"));
				invoice.setCreated(result.getDate("created"));
				invoices.add(invoice);
			}
		} catch (Exception e) {
			System.out.println("loi o day");
		} finally {
			ConnectDB.disconnect();
		}
		return invoices;
	}

	public Invoice FindID(int id) {
		Invoice invoice = null;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from invoice where id = ?");
			preparedstatement.setInt(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				invoice = new Invoice();
				invoice.setId(resultset.getInt("id"));
				invoice.setName(resultset.getString("name"));
				invoice.setPaymentId(resultset.getString("payment"));
				invoice.setTotal(resultset.getDouble("total"));
				invoice.setCreated(resultset.getDate("created"));
				
				
			}	
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return invoice;
	}
	
	public boolean Create(Invoice invoice) {
		boolean result = true;
		try {
			PreparedStatement preparestatement = ConnectDB.connection()
					.prepareStatement("insert into invoice (name, payment, total,created) values (?,?,?,?)");
			preparestatement.setString(1,invoice.getName());
			preparestatement.setString(2,invoice.getPaymentId());
			preparestatement.setDouble(3, invoice.getTotal());
			preparestatement.setDate(4,new java.sql.Date(invoice.getCreated().getTime()));
			
			result = preparestatement.executeUpdate() > 0;
					} catch (Exception e) {
						System.out.print("loi o day");
			return false;
		}
		finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public boolean update(Invoice invoice) {
		boolean result = true;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("update invoice set name = ?, payment = ?, total = ?, created =? where id =?");
			preparedstatement.setString(1,invoice.getName());
			preparedstatement.setString(2,invoice.getPaymentId());
			preparedstatement.setDouble(3,invoice.getTotal());
			preparedstatement.setDate(4,new java.sql.Date(invoice.getCreated().getTime()));
			preparedstatement.setInt(5,invoice.getId());
			System.out.println(preparedstatement);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
	
	public boolean deleted(int id) {
		boolean result = true;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("delete from invoice where id =?");
			preparedstatement.setInt(1,id);
			result = preparedstatement.executeUpdate() > 0;
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return result;
	}
}
