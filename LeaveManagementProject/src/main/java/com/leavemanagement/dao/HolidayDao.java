package com.leavemanagement.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.SimpleDateFormat;
import java.util.*;

import com.leavemanagement.model.Holiday;
import com.leavemanagement.util.LeaveUtil;

import jakarta.transaction.SystemException;
//import jakarta.transaction.Transaction;

public class HolidayDao {

	public void sunday(int year) throws IllegalStateException, SystemException  {
		// TODO Auto-generated method stub
		Session session =LeaveUtil.buildSessionFactory().getCurrentSession();
		Transaction transaction=null;
		
		try {
			transaction= session.beginTransaction();
			 
			 Calendar calendar=Calendar.getInstance();
			 calendar.set(year,Calendar.JANUARY,1);
			// SimpleDateFormat date= new SimpleDateFormat("YYYY-MM-DD");
			 
			 while(calendar.get(Calendar.YEAR)==year) {
				 
				 if(calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
					 Holiday holiday=new Holiday();
					 holiday.setHolidaydate(calendar.getTime());
					 holiday.setReason("Sunday");
					 
					 session.persist(holiday);
				 }
				 // move to next day
				 calendar.add(Calendar.DAY_OF_YEAR, 1);
			 }
			//session.getTransaction().commit(); 
			System.out.println("holiday add sucessfully");
			
		}
		catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		
		finally {
			session.close();
		}
		
	}
	

}
