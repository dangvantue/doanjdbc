package entities;

import java.util.Date;

public class Invoice {
	private int id;
	private String name;
	private String payment;
	private double total;
	private Date created;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPaymentId() {
		return payment;
	}

	public void setPaymentId(String payment) {
		this.payment = payment;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
