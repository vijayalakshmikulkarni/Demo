  
package com.wellsfargo.fsd.cpk.service;

import java.util.List;

import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;

public interface ProductService {

	Product validateAndAdd(Product item) throws CpkException;

	Product validateAndSave(Product item) throws CpkException;

	boolean deleteItem(int id) throws CpkException;

	Product getItemById(int id) throws CpkException;

	List<Product> getAllItems() throws CpkException;
}