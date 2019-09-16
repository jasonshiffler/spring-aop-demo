package com.example.springdemo.springaopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {


    public void addAccount(){
        System.out.println("Adding an Account");
    }

    public void addMember(){
        System.out.println("Adding a Member");
    }

}
