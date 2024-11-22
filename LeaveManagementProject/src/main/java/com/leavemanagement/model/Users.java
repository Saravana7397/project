package com.leavemanagement.model;

import java.util.Set;

import jakarta.persistence.*;


@Entity
@Table(name="users")
public class Users {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="userid")
	private int userid;
	@Column(name="name")
	private String name;
	@Column(name="Age")
	private int age;
	@Column(name="gender")
	private String gender;
	
	private String mobileNumber;
	private String password;
	private String email;
	
//	Mapping concept
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private Set<Leave> leave;
	
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Set<Leave> getLeave() {
		return leave;
	}
	public void setLeave(Set<Leave> leave) {
		this.leave = leave;
	
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Users(String name, int age, String gender,String mobileNumber, String password, String email) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.email = email;
	}
	
	public Users() {
		
	}
	
	
	
	

}
