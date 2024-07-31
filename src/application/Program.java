package application;

import java.time.LocalDate;
import java.util.Locale;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Department depto = new Department(1, "IT");
		
		Seller seller = new Seller(1, "josevan", "josevan@email.com", LocalDate.now(), 8000.00, depto);
		
		System.out.println(seller);

	}

}
