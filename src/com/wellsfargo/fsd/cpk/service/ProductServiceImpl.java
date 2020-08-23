package com.wellsfargo.fsd.cpk.service;


import java.util.ArrayList;
import java.util.List;

import com.wellsfargo.fsd.cpk.dao.ProductDao;
import com.wellsfargo.fsd.cpk.dao.ProductDaoJdbcImpl;
import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;

public class ProductServiceImpl implements ProductService {

	ProductDao itemDao;

	public ProductServiceImpl() {
		itemDao = new ProductDaoJdbcImpl();
	}

	private boolean isIdValid(Integer id) {
		return id > 0;
	}

	private boolean isProductNameValid(String productName) {
		return productName != null && (productName.length() >= 3 || productName.length() <= 20);
	}

	private boolean isCostValid(Integer cost) {
		return cost != null;
	}

	

	private boolean isProductDescriptionValid(String productDescription) {
		return productDescription != null;
	}

	

	private boolean isValidItem(Product item) throws CpkException {
		List<String> errMsg = new ArrayList<>();

		boolean isValid = true;

		if (!isIdValid(item.getId())) {
			isValid = false;
			errMsg.add("Icode can not be null or negative or zero");
		}

		if (!isProductNameValid(item.getProductName())) {
			isValid = false;
			errMsg.add("Product Name can not be blank, title must be of 3 to 20 chars in length");
		}

		
		if (!isCostValid(item.getCost())) {
			isValid = false;
			errMsg.add("Cost can not be null");
		}

		

		if (!isProductDescriptionValid(item.getProductDescription())) {
			isValid = false;
			errMsg.add("product description can not be zero or negative");
		}

		

		if (!isValid) {
			throw new CpkException(errMsg.toString());
		}

		return isValid;
	}

	@Override
	public Product validateAndAdd(Product item) throws CpkException {
		if(item!=null) {
			if(isValidItem(item)) {
				itemDao.add(item);
			}
		}
		return item;
	}

	@Override
	public Product validateAndSave(Product item) throws CpkException {
		if(item!=null) {
			if(isValidItem(item)) {
				itemDao.save(item);
			}
		}
		return item;
	}

	@Override
	public boolean deleteItem(int id) throws CpkException {
		return itemDao.deleteById(id);
	}

	@Override
	public Product getItemById(int id) throws CpkException {
		return itemDao.getById(id);
	}

	@Override
	public List<Product> getAllItems() throws CpkException {
		return itemDao.getAll();
	}

}