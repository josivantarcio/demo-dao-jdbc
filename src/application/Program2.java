package application;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import db.DB;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

		System.out.println("==== Test1 : department findById =====");
		Department department = departmentDao.findById(3);
		System.out.println(department);

		System.out.println("\n==== Test2 : department findAll =====");
		List<Department> lista = departmentDao.findAll();
		for (Department department2 : lista) {
			System.out.println(department2);
		}

		System.out.println("\n==== Test3 : department Insert =====");
		department = new Department(null, "Informatica");
		departmentDao.insert(department);
		System.out.println(department);

		System.out.println("\n==== Test4 : department Update =====");
		department = departmentDao.findById(12);
		department.setName("Perfumaria");
		departmentDao.update(department);
		System.out.println(department);
		
		System.out.println("\n==== Test5 : department Delete =====");
		System.out.print("Digite o ID para excluir: ");
		int id = sc.nextInt();
		departmentDao.deleteById(id);
		System.out.println("Delete completed");

		sc.close();
		DB.closeConnection();
	}

}
