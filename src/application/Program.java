package application;

import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("==== Test1 : seller findById =====");
		Seller vendedor = sellerDao.findById(4);
		System.out.println(vendedor);

	}

}
