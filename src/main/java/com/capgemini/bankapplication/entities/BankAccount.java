package com.capgemini.bankapplication.entities;

import java.util.Objects;

public class BankAccount {

	private long accountId;
	private double balance;
	private String accountType;
	
	@Override
	public int hashCode() {
		return Objects.hash(accountId,accountType);
	}

	public BankAccount(long accountId, double balance, String accountType) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.accountType = accountType;
	}

	public BankAccount() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
}