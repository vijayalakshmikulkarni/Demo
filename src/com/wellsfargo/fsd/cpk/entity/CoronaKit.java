package com.wellsfargo.fsd.cpk.entity;

import java.time.LocalDate;


public class CoronaKit {

	private  int id;
	private  String personName;
	private  String email;
	private  String contactNumber;
	private  int totalAmount;
	private  String deliveryAddress;
	private  LocalDate orderDate;
	private  boolean orderFinalized;
	
	public CoronaKit() {
		// TODO Auto-generated constructor stub
	}

	
	public CoronaKit(int id, String personName, String email, String contactNumber, int totalAmount,
			String deliveryAddress, LocalDate orderDate, boolean orderFinalized) {
		super();
		this.id = id;
		this.personName = personName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.totalAmount = totalAmount;
		this.deliveryAddress = deliveryAddress;
		this.orderDate = orderDate;
		this.orderFinalized = orderFinalized;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public boolean isOrderFinalized() {
		return orderFinalized;
	}

	public void setOrderFinalized(boolean orderFinalized) {
		this.orderFinalized = orderFinalized;
	}

	@Override
	public String toString() {
		return "CoronaKit [id=" + id + ", personName=" + personName + ", email=" + email + ", contactNumber="
				+ contactNumber + ", totalAmount=" + totalAmount + ", deliveryAddress=" + deliveryAddress
				+ ", orderDate=" + orderDate + ", orderFinalized=" + orderFinalized + "]";
	}

			
	

}
