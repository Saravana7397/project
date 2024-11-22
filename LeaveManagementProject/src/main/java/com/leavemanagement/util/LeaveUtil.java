package com.leavemanagement.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.leavemanagement.model.Holiday;
import com.leavemanagement.model.Leave;
import com.leavemanagement.model.Users;



public class LeaveUtil {
	public static SessionFactory sessionFactory;
	public static SessionFactory  buildSessionFactory() {
		Configuration configuration =new Configuration();
		configuration.configure("Leave.cfg.xml");
		configuration.addAnnotatedClass(Holiday.class);
		configuration.addAnnotatedClass(Leave.class);
		configuration.addAnnotatedClass(Users.class);
		
		System.out.println("hibernate congfiguration loaded Sucessfully");
		
		ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration. buildSessionFactory(serviceRegistry);
		return sessionFactory;
		
	}
	public static SessionFactory getSessionFactory() {
		if(sessionFactory == null)
			sessionFactory= buildSessionFactory();
		return sessionFactory;
	}

}
