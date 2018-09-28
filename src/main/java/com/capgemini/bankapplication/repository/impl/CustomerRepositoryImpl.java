package com.capgemini.bankapplication.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapplication.entities.BankAccount;
import com.capgemini.bankapplication.entities.Customer;
import com.capgemini.bankapplication.repository.CustomerRepository;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
	@Autowired
	public JdbcTemplate jdbcTemplate;

	@Override
	public Customer authenticate(Customer customer) {
		//return jdbcTemplate.queryForObject("select * from customers where customer_id=? and password=?",new Object[] {customer.getCustomerId(),customer.getPassword()}, new CustomerRowMapper());
		return jdbcTemplate.queryForObject(
				"select * from customers inner join bankaccount on bankaccount.customer_id=customers.customer_id where customers.customer_id=? and customers.password=?",
				new Object[] { customer.getCustomerId(), customer.getPassword() }, new CustomerRowMapper());
	}

	@Override
	public Customer updateProfile(Customer customer) {
		System.out.println("sdfghnsjkhfuidf   "+customer);
		int count = jdbcTemplate.update(
				"update customers set address=?,email=? where customer_id=?",
				new Object[] { customer.getAddress(), customer.getEmail(),
						customer.getCustomerId() });
		if (count != 0) {
			return customer;
		} else {
			return getCustomer(customer.getCustomerId());
		}
	}

	private Customer getCustomer(int customerId) {
		return jdbcTemplate.queryForObject(
				"select * from customers inner join bankaccount on bankaccount.customer_id=customers.customer_id where customers.customer_id=?",
				new Object[] { customerId }, new CustomerRowMapper());
	}

	@Override
	public boolean updatePassword(Customer customer, String oldPassword, String newPassword) {
		int count = jdbcTemplate.update("UPDATE customers SET password=? WHERE customer_id=? AND password=?",
				new Object[] { newPassword, customer.getCustomerId(), oldPassword });
		return (count != 0) ? true : false;
	}

	private class CustomerRowMapper implements RowMapper<Customer> {
		@Override
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

			Customer customer = new Customer();
			customer.setCustomerName(rs.getString(1));
			customer.setCustomerId(rs.getInt(2));
			customer.setPassword(rs.getString(3));
			customer.setEmail(rs.getString(4));
			customer.setAddress(rs.getString(5));
			customer.setDateOfBirth(rs.getDate(6).toLocalDate());
			return customer;
		}

	}

	private class BankAccountRowMapper implements RowMapper<BankAccount> {

		@Override
		public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {

			BankAccount baccount = new BankAccount();
			baccount.setAccountId(rs.getLong(1));
			baccount.setBalance(rs.getDouble(2));
			baccount.setAccountType(rs.getString(3));

			return baccount;
		}

	}

}
// "select * from bankaccounts where accountId=?" "select * from customers where customer_Id=? and password=?"
