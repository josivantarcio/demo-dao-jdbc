package application;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SellerDao sellerDao = DaoFactory.createSellerDao();

		System.out.println("==== Test1 : seller findById =====");
		Seller vendedor = sellerDao.findById(4);
		System.out.println(vendedor);

		System.out.println("\n==== Test2 : seller findByDepartment =====");
		Department department = new Department(1, null);
		List<Seller> lista = sellerDao.findByDepartment(department);
		for (Seller seller : lista) {
			System.out.println(seller);
		}

		System.out.println("\n==== Test3 : seller findAll =====");
		lista = sellerDao.findAll();
		for (Seller seller : lista) {
			System.out.println(seller);
		}

		System.out.println("\n==== Test4 : seller Insert =====");
		Seller seller = new Seller(null, "Joaquim Greg", "joaquimg@email.com", new Date(), 6500.00, department);
		sellerDao.insert(seller);
		System.out.println(seller);

		System.out.println("\n==== Test5 : seller Update =====");
		seller = sellerDao.findById(1);
		seller.setName("Jacob");
		sellerDao.update(seller);
		System.out.println(seller);

		System.out.println("\n==== Test6 : seller Delete =====");
		System.out.print("Digite o ID para excluir: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed");
		
		
		sc.close();
		DB.closeConnection();
	}
}
