package com.capgemini.bankapplication.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
	
	private String customerName;
	private int customerId;
	private String password;
	private String email;
	private String Address;
	private LocalDate dateOfBirth;
	private BankAccount account;
	@Override
	public int hashCode() {
		return Objects.hash(customerId,email);
	}
	public Customer(String customerName, int customerId, String password, String email, String address,
			LocalDate dateOfBirth, BankAccount account) {
		super();
		this.customerName = customerName;
		this.customerId = customerId;
		this.password = password;
		this.email = email;
		Address = address;
		this.dateOfBirth = dateOfBirth;
		this.account = account;
	}
	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Customer [customerName=" + customerName + ", customerId=" + customerId + ", password=" + password
				+ ", email=" + email + ", Address=" + Address + ", dateOfBirth=" + dateOfBirth + ", account=" + account
				+ "]";
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public BankAccount getAccount() {
		return account;
	}
	public void setAccount(BankAccount account) {
		this.account = account;
	}


}