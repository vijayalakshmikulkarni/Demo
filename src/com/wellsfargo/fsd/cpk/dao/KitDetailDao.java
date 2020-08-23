package com.wellsfargo.fsd.cpk.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import com.wellsfargo.fsd.cpk.entity.CoronaKit;
import com.wellsfargo.fsd.cpk.entity.KitDetail;
import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;

public class KitDetailDao  {

	public static final String INS_ITEM_QRY = "INSERT INTO kitDetails (id,coronaKitId,productId,quantity,amount) values(?,?,?,?,?)";
	public static final String UPD_ITEM_QRY= "UPDATE kitDetails SET quantity=?,amount=? WHERE productId=?";
	public static final String DEL_ITEM_QRY = "DELETE FROM kitDetails WHERE productId=?";
	public static final String SEL_ITEM_QRY_BY_ID = "SELECT id,coronaKitId,productId,quantity,amount FROM kitDetails WHERE productId=?";
	public static final String SEL_ALL_ITEMS_QRY = "select k.productId,p.productName, p.cost,k.quantity, k.amount from products p join kitDetails k on p.id=k.productId";
	public static final String SEL_ITEM_TOTAL_AMOUNT = "select SUM(amount) as totalAmount from kitDetails";
	public static final String DEL_ALL_QRY = "DELETE FROM kitDetails";
	
	public KitDetail add(KitDetail item) throws CpkException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(INS_ITEM_QRY)) {

				pst.setInt(1, item.getId());
				pst.setInt(2, item.getCoronaKitId());
				pst.setInt(3, item.getProductId());
				pst.setInt(4, item.getQuantity());
				pst.setInt(5, item.getAmount());
			
				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CpkException("Adding the item to kit failed!");
			}
		}
		return item;
	}

	
	public KitDetail save(KitDetail item) throws CpkException {
		if (item != null) {
			try (Connection con = ConnectionFactory.getConnection();
					PreparedStatement pst = con.prepareStatement(UPD_ITEM_QRY)) {

				
				pst.setInt(1, item.getQuantity());
				pst.setInt(2, item.getAmount());
				pst.setInt(3, item.getProductId());
				

				pst.executeUpdate();

			} catch (SQLException | NamingException exp) {
				exp.printStackTrace();
				throw new CpkException("Saving the item to kit failed!");
			}
		}
		return item;
	}

	
	public boolean deleteById(Integer productId) throws CpkException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ITEM_QRY)) {

			pst.setInt(1, productId);
			

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Deleting the item from kit failed!");
		}

		return isDeleted;
	}

	
	public KitDetail getById(Integer productId) throws CpkException {
		KitDetail item = null;

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_QRY_BY_ID)) {

			pst.setInt(1, productId);

			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				item = new KitDetail();
				item.setId(rs.getInt(1));
				item.setCoronaKitId(rs.getInt(2));
				item.setProductId(rs.getInt(3));
				item.setQuantity(rs.getInt(4));
				item.setAmount(rs.getInt(5));
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Retrival the item failed!");
		}

		return item;
	}

		
	public List<KitDetail> getAll() throws CpkException {
		List<KitDetail> items = new ArrayList<>();

		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ALL_ITEMS_QRY)) {

			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				KitDetail item = new KitDetail();
				item.setProductId(rs.getInt(1));
				item.setProductName(rs.getString(2));
				item.setCost(rs.getInt(3));
				item.setQuantity(rs.getInt(4));
				item.setAmount(rs.getInt(5));
				
								
				items.add(item);
			}
			
			if(items.isEmpty()) {
				items=null;
			}
		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Retrival the item from kit failed!");
		}
		return items;
	}
	
	
	public int getSumAmount() throws CpkException {
		
		int sumAmount =0;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(SEL_ITEM_TOTAL_AMOUNT)) {

					
			ResultSet rs = pst.executeQuery();

			if (rs.next()) {
				
				sumAmount=rs.getInt(1);
				
				
			}

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Total amount retrieval failed!");
		}

		return sumAmount;
	}

	public boolean deleteAll() throws CpkException {
		boolean isDeleted = false;
		try (Connection con = ConnectionFactory.getConnection();
				PreparedStatement pst = con.prepareStatement(DEL_ALL_QRY)) {

					

			int rowsCount = pst.executeUpdate();

			isDeleted = rowsCount > 0;

		} catch (SQLException | NamingException exp) {
			exp.printStackTrace();
			throw new CpkException("Deleting All the item from kit failed!");
		}

		return isDeleted;
	}
		
}
