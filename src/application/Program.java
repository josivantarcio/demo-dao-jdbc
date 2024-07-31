package application;

import java.util.List;
import java.util.Locale;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("==== Test1 : seller findById =====");
		Seller vendedor = sellerDao.findById(4);
		System.out.println(vendedor);
		
		System.out.println("==== Test2 : seller findByDepartment =====");
		Department department = new Department(1, null);
		List<Seller> lista = sellerDao.findByDepartment(department);
		for (Seller seller : lista) {
			System.out.println(seller);
		}
		
	}

}
