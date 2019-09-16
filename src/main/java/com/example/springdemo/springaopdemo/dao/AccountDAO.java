package com.example.springdemo.springaopdemo.dao;

import com.example.springdemo.springaopdemo.entities.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;

    Logger logger = LoggerFactory.getLogger(AccountDAO.class);

    public List<Account> findAccounts(){

        List<Account> myAccounts = new ArrayList<>();

        //create sample accounts

        Account account1 = new Account("Bill","Gold");
        Account account2 = new Account("Steve","Bronze");
        Account account3 = new Account("Vinay","Silver");

        myAccounts.add(account1);
        myAccounts.add(account2);
        myAccounts.add(account3);

        return myAccounts;
    }

    //We're using this method to test our @AfterThrowing Advice
    public List<Account> findAccountsException(){

        List<Account> myAccounts = new ArrayList<>();

        if(true)
            throw new RuntimeException("Exeception!");

        return myAccounts;
    }

    //We're using this method to test our @Around Advice
    public void addAccountAround(){

        logger.info(getClass() + "in addAccountAround()");
        try{
            sleep(1100);
        }catch (Exception e){
            logger.info(e.toString());
        }

    }

    public void addAccountAroundException(){

        logger.info(getClass() + "in addAccountAroundException()");
        try{
            sleep(1100);
        }catch (Exception e){
            logger.info(e.toString());
        }
        throw new RuntimeException("Exception in addAcountAroundException()");

    }

    public void addAccount(String Name){
        logger.info(getClass() + "in addAccount()");
        this.setName(Name);
    }

    public void deleteAccount(){
        logger.info(getClass() + "in deleteAccount()");
    }

    public String getName() {
        logger.info(getClass() + "in getName()");
        return name;
    }

    public void setName(String name) {
        logger.info(getClass() + "in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        logger.info(getClass() + "in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        logger.info(getClass() + "in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
