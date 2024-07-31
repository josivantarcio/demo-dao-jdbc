package application;

import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller vendedor = sellerDao.findById(13);
		System.out.println(vendedor);

	}

}
