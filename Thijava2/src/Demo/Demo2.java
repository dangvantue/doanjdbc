package Demo;

import java.util.List;

import entities.Category;
import model.CategoryPaymentModel;

public class Demo2 {

	public static void main(String[] args) {
		CategoryPaymentModel categorymodel = new CategoryPaymentModel();
		List<Category> payments = categorymodel.findAll();
		System.out.println(payments.size());
		for (Category payment : payments) {
			System.out.println("id: " + payment.getId());
			System.out.println("name: " + payment.getName());
		}

	}

}
