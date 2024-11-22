package com.leavemanagement.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.leavemanagement.main.LeaveMain;
import com.leavemanagement.model.Holiday;
import com.leavemanagement.model.Leave;
import com.leavemanagement.model.Users;
import com.leavemanagement.util.LeaveUtil;


public class LeaveDao {
	Scanner input =new Scanner(System.in);
	//private Object name;
		public void signup() throws ParseException 
		{
			Scanner input =new Scanner(System.in);
			System.out.println("Enter a Username :");
			String name=input.next();
			
			System.out.println("Enter a gender :");
			String gender=input.next();
			
			System.out.println("Enter a gmail :");
			String email=input.next();
			
			System.out.println("Enter a mobilenumber :");
			String mobilenumber=input.next();
			
			System.out.println("Enter a age :");
			int age=input.nextInt();
			
			System.out.println("Enter a password :");
			String pass=input.next();
			
			Session session =LeaveUtil.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			LeaveMain leavemain=new LeaveMain();
			Leave leaves=new Leave();
			Holiday holidays=new Holiday();	
			
			Query query= session.createQuery("From Users u Where u.email=:email");
			query.setParameter("email", email);
			Users existinguser=(Users) query.uniqueResult();
			if(existinguser==null)
			{
				// create a new user
				Users user=new Users();
				user.setName(name);
				user.setPassword(pass);
				user.setEmail(email);
				user.setMobileNumber(mobilenumber);
				user.setAge(age);
				user.setGender(gender);
				session.persist(user);
				session.getTransaction().commit();
				System.out.println("User singup Sucessfully");
				LeaveDao leaveDao=new LeaveDao();
				leaveDao.login();
			
			}
			else {
				System.out.println("Use Different Gmail");
				LeaveDao leaveDao=new LeaveDao();
				leaveDao.signup();
			}
			
			
		}
		
		public void login() throws ParseException {
			
			// TODO Auto-generated method stub
			
			Scanner input = new Scanner(System.in);

	        System.out.println("Enter your Email:");
	        String email1 = input.next();

	        System.out.println("Enter your Password:");
	        String password1 = input.next();

			
			
			Session session =LeaveUtil.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			
			@SuppressWarnings("deprecation")
			Query query=session.createQuery("From Users u where u.email=:email AND u.password= :password");
			query.setParameter("email", email1);
			query.setParameter("password", password1);
			Users user=(Users)query.uniqueResult();
			session.getTransaction().commit();
			
			if(user!=null) {
				System.out.println("Login Sucessful");
				System.out.println("UserId"+user.getUserid());
				
				
				leaveEntry();
				printdetails(email1);
			//	return user;
				
			}
			else {
				System.out.println("invalid username or password");
				//LeaveDao leaveDao=new LeaveDao();
				login();
			}
			//return null;
			
		}
		
		
		
		private void leaveEntry() throws ParseException {
			// TODO Auto-generated method stub
			System.out.println("Are you Take a Leave for Enter Leave Or View a LeaveRecord for press Details");
			String userinput=input.next();
			if("leave".equalsIgnoreCase(userinput)) {
			Session session =LeaveUtil.buildSessionFactory().getCurrentSession();
			session.beginTransaction();
			int totaldaysofleave=20;
			System.out.println("Leave Apply Form");
			System.out.println("Enter a UserId");
			int userid=input.nextInt();
			System.out.println("StartDate YYYY-MM-DD");
			System.out.println("enter the year");
			int year=input.nextInt();
			System.out.println("enter the month");
			int moth=input.nextInt();
			System.out.println("enter the date");
			int date=input.nextInt();
			System.out.println("EndDate YYYY-MM-DD");
			System.out.println("enter the year");
			int yeare=input.nextInt();
			System.out.println("enter the month");
			int mothe=input.nextInt();
			System.out.println("enter the date");
			int datee=input.nextInt();
//			String eDate=input.next();
//			
//			
//			
//			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD"); 
//			SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");  
//			Date startDate = sdf.parse(stDate); 
//			System.out.println(startDate);
//			
//			Date endDate = sdf.parse(eDate);
//			System.out.println(endDate);
//			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		        // Parse the dates
//		        LocalDate startDate1 = LocalDate.parse(stDate, formatter);
//		        LocalDate endDate1 = LocalDate.parse(eDate, formatter);
//			
//			
//			
//			
//
//			long diffInMillis = startDate.getTime() - endDate.getTime();
//			long diffInMillis = endDate.getTime() - startDate.getTime();
//            long diffInDays = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
//            diffInDays += 1;
//            int leavecount=(int) diffInDays;
//            System.out.println("leave count"+diffInDays);
//            int balanceleave = totaldaysofleave - leavecount;
//            System.out.println("balance "+balanceleave);
//			 
			LocalDate sd=LocalDate.of(year, moth, date);
			LocalDate sd1=LocalDate.of(yeare, mothe, datee);
			
	        // Retrieve the user from the database
	        Users user = session.get(Users.class, userid);
	        if (user == null) {
	            System.out.println("User not found.");
	            return;
	        }
	        // Get holidays within the leave period
	        Query<Holiday> holidayQuery = session.createQuery(
	            "FROM Holiday h WHERE h.holidaydate BETWEEN :startDate1 AND :endDate1", Holiday.class);
	        holidayQuery.setParameter("startDate1", sd);
	        holidayQuery.setParameter("endDate1", sd1);
	        List<Holiday> holidays = holidayQuery.list();
	        
	        System.out.println("total leave"+holidays.size());
	      
	        // Calculate the number of days between the dates, including the start and end dates
	        long leaveDays = ChronoUnit.DAYS.between(sd, sd1) + 1;
	        int leavecount=(int)leaveDays-holidays.size();
	        int balanceleave=totaldaysofleave-leavecount;
	        System.out.println("Number of leave days taken: " + leavecount);
	        
	        Query<Leave> leaveQuery = session.createQuery("FROM Leave l WHERE l.user.userid = :userId", Leave.class);
	        leaveQuery.setParameter("userId", userid);
	        Leave existingLeave = leaveQuery.setMaxResults(1).uniqueResult();
		    if(existingLeave != null) {
			Leave leave =new Leave();
//			leave.seteDate(null);
			
			int currentBalanceLeave = existingLeave.getBalanceLeave();
		    int currentLeaveCount = existingLeave.getLeavecount();
		    
		    int newBalanceLeave = currentBalanceLeave - leavecount;
		    int newtotalleave =currentLeaveCount +leavecount;
			leave.setStDate(LocalDate.of(year, moth, date));
			leave.seteDate(LocalDate.of(yeare, mothe, datee));
			leave.setLeavecount(newtotalleave);
			leave.setTotalleave(totaldaysofleave);
//			balanceleave=leave.getBalanceLeave()-balanceleave;
			leave.setBalanceLeave(newBalanceLeave);
			leave.setUser(user);
			session.persist(leave);
			session.getTransaction().commit();
			System.out.println("Leave applied successfully.");
			}
		    
		    
		    else {
				Leave leave =new Leave();
//				leave.seteDate(null);
				
//				int currentBalanceLeave = existingLeave.getBalanceLeave();
//			    int currentLeaveCount = existingLeave.getLeavecount();
			    
//			    int newBalanceLeave = currentBalanceLeave - leavecount;
//			    int newtotalleave =currentLeaveCount +leavecount;
				leave.setStDate(LocalDate.of(year, moth, date));
				leave.seteDate(LocalDate.of(yeare, mothe, datee));
				leave.setLeavecount(leavecount);
				leave.setTotalleave(totaldaysofleave);
//				balanceleave=leave.getBalanceLeave()-balanceleave;
				leave.setBalanceLeave(balanceleave);
				leave.setUser(user);
				session.persist(leave);
				session.getTransaction().commit();
				System.out.println("Leave applied successfully.");
				}
		    
		    
			}
			else if("details".equalsIgnoreCase(userinput)) {
				
			}
			
			
		}
		
		public void printdetails(String email) {
		    Session session = LeaveUtil.buildSessionFactory().getCurrentSession();
		    session.beginTransaction();

		    // Fetch the user by email
		    Query q = session.createQuery("FROM Users u WHERE u.email = :email");
		    q.setParameter("email", email);
		    Users user = (Users) q.uniqueResult();

		    if (user != null) {
		        // Print user details
		        System.out.println("User Details:");
		        System.out.println("Name: " + user.getName());
		        System.out.println("ID: " + user.getUserid());
		        System.out.println("Email: " + user.getEmail());

		        // Fetch all leaves for the user
		        Query<Leave> leaveQuery = session.createQuery("FROM Leave l WHERE l.user.userid = :userid ORDER BY l.stDate DESC", Leave.class);
		        leaveQuery.setParameter("userid", user.getUserid());
		        List<Leave> leaveHistory = leaveQuery.list();

		        if (leaveHistory.isEmpty()) {
		            System.out.println("No leave history found for this user.");
		        } else {
		            // Print leave history
		            System.out.println("Leave History:");
		            for (Leave leave : leaveHistory) {
		                System.out.println("Start Date: " + leave.getStDate() + 
		                                   " | End Date: " + leave.geteDate() +
		                                   " | Leave Count: " + leave.getLeavecount() + 
		                                   " | Balance Leave: " + leave.getBalanceLeave());
		            }
		        }
		    } else {
		        System.out.println("User not found with email: " + email);
		    }

		    session.getTransaction().commit();
		}

		

		
		

		
}
