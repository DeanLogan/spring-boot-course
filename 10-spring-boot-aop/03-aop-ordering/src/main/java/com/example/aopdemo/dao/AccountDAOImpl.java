package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // simulate an exception if tripWire is true
        if(tripWire) {
            throw new RuntimeException("Simulated exception");
        }

        List<Account> myAccounts = new ArrayList<>();

        Account account1 = new Account("Scott", "Silver");
        Account account2 = new Account("John", "platinum");
        Account account3 = new Account("Luca", "gold");

        myAccounts.add(account1);
        myAccounts.add(account2);
        myAccounts.add(account3);

        return myAccounts;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass()+" : DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() +": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() +": getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() +": setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() +": getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() +": setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
