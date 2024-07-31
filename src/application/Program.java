package application;

import java.time.LocalDate;
import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Department depto = new Department(1, "IT");

		Seller seller = new Seller(1, "josevan", "josevan@email.com", LocalDate.now(), 8000.00, depto);

		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println(seller);

	}

}
