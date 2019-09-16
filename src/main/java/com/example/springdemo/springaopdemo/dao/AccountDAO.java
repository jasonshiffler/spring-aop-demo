package com.example.springdemo.springaopdemo.dao;

import com.example.springdemo.springaopdemo.entities.Account;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

@Component
public class AccountDAO {

    private String name;
    private String serviceCode;


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

        System.out.println(getClass() + "in addAccountAround()");
        try{
            sleep(1100);
        }catch (Exception e){
            System.out.println(e);
        }

    }

    public void addAccountAroundException(){

        System.out.println(getClass() + "in addAccountAroundException()");
        try{
            sleep(1100);
        }catch (Exception e){
            System.out.println(e);
        }
        throw new RuntimeException("Exception in addAcountAroundException()");

    }

    public void addAccount(String Name){
        System.out.println(getClass() + "in addAccount()");
        this.setName(Name);
    }

    public void deleteAccount(){
        System.out.println(getClass() + "in deleteAccount()");
    }

    public String getName() {
        System.out.println(getClass() + "in getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + "in setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + "in getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + "in setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
