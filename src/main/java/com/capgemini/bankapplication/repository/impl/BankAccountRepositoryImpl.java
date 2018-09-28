package com.capgemini.bankapplication.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.capgemini.bankapplication.repository.BankAccountRepository;
@Repository
public class BankAccountRepositoryImpl implements BankAccountRepository {
	
	@Autowired
	public JdbcTemplate jdbcTemplate;
	
	@Override
	public double getBalance(long accountId) {
		double balance = jdbcTemplate.queryForObject("SELECT balance FROM bankaccounts WHERE accountId = ?",
				new Object[] { accountId }, Double.class);
		return balance;
	}

	@Override
	public boolean updateBalance(long accountId, double newBalance) {
		int count = jdbcTemplate.update("UPDATE bankaccounts SET balance = ? WHERE accountID =?", new Object[] {newBalance, accountId });
		return count != 0;
	

	}
	

}
