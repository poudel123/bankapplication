package com.capgemini.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.capgemini.bankapplication.repository.BankAccountRepository;
import com.capgemini.bankapplication.sevice.BankAccountService;


@Service
public class BankAccountServiceImpl implements BankAccountService {
	@Autowired
	private BankAccountRepository bankAccountRepository;

	
	/*
	 * public void setBankAccountRepository(BankAccountRepository
	 * bankAccountRepository) { this.bankAccountRepository = bankAccountRepository;
	 * }
	 */

	@Override
	public double getBalance(long accountId) {
		return bankAccountRepository.getBalance(accountId);
	}

	@Override
	public double withdraw(long accountId, double amount)  {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			if (balance - amount >= 0) {
				bankAccountRepository.updateBalance(accountId, balance - amount);
				return bankAccountRepository.getBalance(accountId);
			}
		}
		return balance;
	}
	

	@Override
	public double deposit(long accountId, double amount) {
		double balance = bankAccountRepository.getBalance(accountId);
		if (balance != -1) {
			bankAccountRepository.updateBalance(accountId, balance + amount);
			return bankAccountRepository.getBalance(accountId);
		}
		return balance;
	}

	@Override
	public boolean fundTransfer(long fromAccount, long toAccount, double amount)  {
		double balance = withdraw(fromAccount, amount);
		if (balance != -1) {
			if (deposit(toAccount, amount) == -1) {
				deposit(fromAccount, amount);
				return false;
			}
			return true;
		}
		return false;
	}

}

