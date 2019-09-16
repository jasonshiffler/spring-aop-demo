package com.example.springdemo.springaopdemo;

import com.example.springdemo.springaopdemo.aop.DemoLoggingAspect;
import com.example.springdemo.springaopdemo.dao.AccountDAO;
import com.example.springdemo.springaopdemo.dao.MembershipDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringAopDemoApplication {

	static Logger logger = LoggerFactory.getLogger(SpringAopDemoApplication.class);
	
	public static void main(String[] args) {

		//Grab the application context

		ApplicationContext ctx = SpringApplication.run(SpringAopDemoApplication.class, args);

		//Retrieve the DAO beans

		AccountDAO aDao = ctx.getBean("accountDAO", AccountDAO.class);
		MembershipDAO mDao = ctx.getBean("membershipDAO", MembershipDAO.class);


		//Call the methods so we can see the AOP methods execute.

		aDao.setServiceCode("123456");

		aDao.addAccount("Bob");
		mDao.addAccount();
		mDao.addMember();
		aDao.deleteAccount();

		logger.info("*********************Around Tester***********************");
		logger.info("*********************Around Tester***********************");

		aDao.addAccountAround();

		logger.info("*********************Around Exception Tester***********************");
		logger.info("*********************Around Exception Tester***********************");

		aDao.addAccountAroundException();


		logger.info("*********************Call Find Accounts***********************");
		logger.info("*********************Call Find Accounts***********************");

		aDao.findAccounts();

		logger.info("*********************AfterThrowing Demo***********************");
		logger.info("*********************AfterThrowing Demo***********************");

		aDao.findAccountsException();


	}

}
