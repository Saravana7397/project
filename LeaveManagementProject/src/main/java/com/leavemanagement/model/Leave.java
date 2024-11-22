package com.leavemanagement.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.*;


@Entity
@Table(name="Leave")
public class Leave {
		// TODO Auto-generated method stub
		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name="Leave_id")
		private int leaveid;
//		@Column(name="User_id")
//		private int userid;
		@Column(name="StartDate")
		private LocalDate stDate;
		@Column(name="EndDate")
		private LocalDate eDate;
		@Column(name="LeaveCount")
		private int leavecount;
		@Column(name="BalanceLeave")
		private int balanceLeave;
		@Column(name="TotalLeaveDays")
		private int totalleave;
		
		@ManyToOne
		@JoinColumn(name="userid")
		private  Users user;
		
		@ManyToMany(cascade= CascadeType.ALL)
		@JoinTable(name="leave_holiday",
		joinColumns=@JoinColumn(name="leave_id"),
		inverseJoinColumns=@JoinColumn(name="holiday_id"))
		
		private List<Holiday> holiday;
		
		public List<Holiday> getHoliday() {
			return holiday;
		}
		public void setHoliday(List<Holiday> holiday) {
			this.holiday = holiday;
		}
		public int getLeaveid() {
			return leaveid;
		}
		public void setLeaveid(int leaveid) {
			this.leaveid = leaveid;
		}
//		public int getUserid() {
//			return userid;
//		}
//		public void setUserid(int userid) {
//			this.userid = userid;
//		}
		public LocalDate getStDate() {
			return stDate;
		}
		public void setStDate(LocalDate stDate) {
			this.stDate = stDate;
		}
		public LocalDate geteDate() {
			return eDate;
		}
		public void seteDate(LocalDate eDate) {
			this.eDate = eDate;
		}
		
		public Users getUser() {
			return user;
		}
		public void setUser(Users user) {
			this.user = user;
		}
		
		
		public int getLeavecount() {
			return leavecount;
		}
		public void setLeavecount(int leavecount) {
			this.leavecount = leavecount;
		}
		public int getBalanceLeave() {
			return balanceLeave;
		}
		public void setBalanceLeave(int balanceLeave) {
			this.balanceLeave = balanceLeave;
		}
		
		public int getTotalleave() {
			return totalleave;
		}
		public void setTotalleave(int totalleave) {
			this.totalleave = totalleave;
		}
		public Leave() {
			
		}
		
		
		

}
