package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entities.Category;
import model.ConnectDB;

public class CategoryPaymentModel {

	public List<Category> findAll() {
		List<Category> categorypayment = new ArrayList<Category>();
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from category_payment");
			ResultSet result = preparedstatement.executeQuery();
			while (result.next()) {
				Category payment = new Category();
				payment.setId(result.getString("id"));
				payment.setName(result.getString("name"));
				categorypayment.add(payment);
			}
		} catch (Exception e) {
			System.out.println("loi o day");
		} finally {
			ConnectDB.disconnect();
		}
		return categorypayment;
	}
	
	public Category findId(String id) {
		Category categorypayment = null;
		try {
			PreparedStatement preparedstatement = ConnectDB.connection()
					.prepareStatement("select * from category_payment where id = ?");
			preparedstatement.setString(1,id);
			ResultSet resultset = preparedstatement.executeQuery();
			while(resultset.next()) {
				categorypayment = new Category();
				categorypayment.setId(resultset.getString("id"));
				categorypayment.setName(resultset.getString("name"));	
				
			}	
		} catch (Exception e) {
			System.out.print("loi o day");
		}finally {
			ConnectDB.disconnect();
		}
		return categorypayment;
	}
}
