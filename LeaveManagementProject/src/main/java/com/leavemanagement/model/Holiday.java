package com.leavemanagement.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name="Holiday")
public class Holiday {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="Holiday_id")
	private int holidayid;
	@SuppressWarnings("deprecation")
	@Column(name="Date",nullable=false)
	@Temporal(TemporalType.DATE)
	private Date holidaydate;
	@Column(name="reason")
	private String reason;
	@ManyToMany(mappedBy="holiday")
	private List<Leave> leave;
	
	
	public Date getHolidaydate() {
		return holidaydate;
	}
	public void setHolidaydate(Date holidaydate) {
		this.holidaydate = holidaydate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Holiday(Date holidaydate, String reason) {
		super();
		this.holidaydate = holidaydate;
		this.reason = reason;
	}
	
	public Holiday() {
	}
	

}
