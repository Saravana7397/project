package com.leavemanagement.main;

import java.text.ParseException;
import java.util.*;

import com.leavemanagement.dao.*;
import com.leavemanagement.model.Users;

import jakarta.transaction.SystemException;





public class LeaveMain {
	
	public static void main(String[] args) throws IllegalStateException, SystemException, ParseException {
		// TODO Auto-generated method stub
		Scanner input =new Scanner(System.in);
		System.out.println("Leave Management");
		System.out.println("Are you a Login  Or Singup ");
		String userType=input.next();
		HolidayDao holidaydao=new HolidayDao();
		int year=2024;
		holidaydao.sunday(year);
		
		if("signup".equalsIgnoreCase(userType)) {
			LeaveDao leaveDao=new LeaveDao();
			leaveDao.signup();
		}
		else if("login".equalsIgnoreCase(userType))
		{
			LeaveDao leaveDao=new LeaveDao();
			leaveDao.login();
		}
		else {
			System.out.println("Enter a Valid Option");
		}
		

	}
	

}