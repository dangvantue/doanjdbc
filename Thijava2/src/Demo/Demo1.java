package Demo;

import model.ConnectDB;

public class Demo1 {

	public static void main(String[] args) {
		// kiem tra xem da ket noi database duoc chua
		if (ConnectDB.connection() != null) {
			System.out.println("Done");
		} else {
			System.out.println("Failed");
		}
	}
}
