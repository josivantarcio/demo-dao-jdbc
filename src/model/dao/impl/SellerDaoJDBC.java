package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
		
		PreparedStatement st = null;
		try {
			
			st = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES"
					+ "(?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS
					);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
			st.setDouble(4, obj.getBaseSalary());
			st.setInt(5, obj.getDepartment().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while(rs.next()) {
					int i = rs.getInt(1);
					obj.setId(i);
				}
				DB.closeResoltSet(rs);
			}else{
				throw new DbException("ERROR : Nenhuma linha foi afetada");
			}
					
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement(
					"SELECT seller.* , department.Name as Depto " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? ");

			st.setInt(1, id);
			
			rs = st.executeQuery();

			if (rs.next()) {

				Department dep = instantiateDepartment(rs);
				Seller obj = instantiateSeller(rs, dep);

				return obj;
			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResoltSet(rs);
			DB.closeStatement(st);
		}

	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		Seller obj = new Seller();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setEmail(rs.getString("Email"));
		obj.setBaseSalary(rs.getDouble("BaseSalary"));
		obj.setBirthDate(rs.getDate("BirthDate"));
		obj.setDepartment(dep);

		return obj;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("DepartmentId"));
		dep.setName(rs.getString("Depto"));

		return dep;
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT s.*, d.Name as Depto " 
					+ "FROM seller s inner join department d "
					+ "ON s.DepartmentId = d.Id " 
					+ "ORDER BY Name;");
	
			rs = st.executeQuery();
			
			List<Seller> lista = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

			    Department dep = map.get(rs.getInt("DepartmentId"));
			    
			    if(dep == null) {
			        dep = instantiateDepartment(rs);
			        map.put(rs.getInt("DepartmentId"), dep);
			    }
			    
			    Seller obj = instantiateSeller(rs, dep);
			    lista.add(obj);
			}

			return lista;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResoltSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Seller> findByDepartment(Department d) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT s.*, d.Name as Depto " 
					+ "FROM seller s inner join department d "
					+ "ON s.DepartmentId = d.Id " 
					+ "WHERE d.Id = ? " 
					+ "ORDER BY Name;");

			st.setInt(1, d.getId());
			
			rs = st.executeQuery();
			
			/**
			 * Processes the {@code ResultSet} to create a list of {@code Seller} objects, 
			 * associating each seller with its corresponding {@code Department}.
			 * <p>
			 * The method initializes an empty {@code List} to store {@code Seller} objects and a 
			 * {@code Map} to cache {@code Department} objects, ensuring that each department is 
			 * instantiated only once. 
			 * <p>
			 * For each row in the {@code ResultSet}, the code checks whether the department 
			 * associated with the current seller has already been instantiated. 
			 * If not, it creates a new {@code Department} object, adds it to the {@code Map}, 
			 * and associates it with the current seller. 
			 * Finally, the {@code Seller} object is added to the {@code List}.
			 *
			 * @param lista The {@code List} that will contain all the {@code Seller} objects.
			 * @param map   The {@code Map} that stores {@code Department} objects, using the 
			 *              department ID as the key to avoid creating duplicate instances.
			 * @param rs    The {@code ResultSet} being processed, containing data about sellers 
			 *              and their associated departments.
			 * @throws SQLException If an SQL error occurs during the process of retrieving data 
			 *                      from the {@code ResultSet}.
			 * @see #instantiateDepartment(ResultSet)
			 * @see #instantiateSeller(ResultSet, Department)
			 */
			List<Seller> lista = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

			    Department dep = map.get(rs.getInt("DepartmentId"));
			    
			    if(dep == null) {
			        dep = instantiateDepartment(rs);
			        map.put(rs.getInt("DepartmentId"), dep);
			    }
			    
			    Seller obj = instantiateSeller(rs, dep);
			    lista.add(obj);
			}

			return lista;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResoltSet(rs);
			DB.closeStatement(st);
		}
	}

}
