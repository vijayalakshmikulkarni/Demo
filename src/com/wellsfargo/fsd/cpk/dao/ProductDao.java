package com.wellsfargo.fsd.cpk.dao;

import java.util.List;

import com.wellsfargo.fsd.cpk.entity.Product;
import com.wellsfargo.fsd.cpk.exception.CpkException;

public interface ProductDao {

	Product add(Product item) throws CpkException;
	Product save(Product item) throws CpkException;
	boolean deleteById(Integer id) throws CpkException;
	
	Product getById(Integer id) throws CpkException;
	List<Product> getAll() throws CpkException;
	
}