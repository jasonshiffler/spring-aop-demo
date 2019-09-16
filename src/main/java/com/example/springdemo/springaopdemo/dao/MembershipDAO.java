package com.example.springdemo.springaopdemo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {

    Logger logger = LoggerFactory.getLogger(MembershipDAO.class);

    public void addAccount(){
        logger.info("Adding an Account");
    }

    public void addMember(){
        logger.info("Adding a Member");
    }

}
