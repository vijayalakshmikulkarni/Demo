package com.wellsfargo.fsd.cpk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;

public class ProductDaoJdbcImpl implements ProductDao {

	public static final String INS_ITEM_QRY = "INSERT INTO products (id,productName,cost,productDescription) values(?,?,?,?)";
	public static final String UPD_ITEM_QRY = "UPDATE products SET productName=?,cost=?,productDescription=? WHERE id=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM products WHERE id=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT id,productName,cost,productDescription FROM products WHERE id=?";
	public static final String SEL_ALL_ITEMS_QRY = "SELECT id,productName,cost,productDescription FROM products";
	
	public Product add(Product item) throws CpkException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				pst.setInt(1, item.getId());
				pst.setString(2, item.getProductName());
				
				pst.setDouble(3, item.getCost());
				pst.setString(4, item.getProductDescription());
				
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CpkException("Adding the item failed!");
			}
		}
		return item;
	}

	@Override
	public Product save(Product item) throws CpkException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

				
				pst.setString(1, item.getProductName());
				pst.setDouble(2, item.getCost());
				pst.setString(3, item.getProductDescription());
				pst.setInt(4, item.getId());
				

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CpkException("Saving the item failed!");
			}
		}
		return item;
	}

	@Override
	public boolean deleteById(Integer id) throws CpkException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY)) {

			pst.setInt(1, id);

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Deleting the item failed!");
		}

		return isDeleted;
	}

	@Override
	public Product getById(Integer id) throws CpkException {
		Product item = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {

			pst.setInt(1, id);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				item = new Product();
				item.setId(rs.getInt(1));
				item.setProductName(rs.getString(2));
				item.setCost(rs.getInt(3));
				item.setProductDescription(rs.getString(4));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Retrival the item failed!");
		}

		return item;
	}

		@Override
	public List<Product> getAll() throws CpkException {
		List<Product> items = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product item = new Product();
				item.setId(rs.getInt(1));
				item.setProductName(rs.getString(2));
				item.setCost(rs.getInt(3));
				item.setProductDescription(rs.getString(4));
								
				items.add(item);
			}
			
			if(items.isEmpty()) {
				items=null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Retrival the item failed!");
		}
		return items;
	}
		
}
