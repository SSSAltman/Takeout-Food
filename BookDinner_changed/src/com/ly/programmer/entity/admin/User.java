package com.ly.programmer.entity.admin;

import org.springframework.stereotype.Component;
/**
 * 用户实体
 * @author LiYing
 *
 */
@Component
public class User {
	private Long id;//设置自增
	private String username;
	private String password;
	private String Sex;
	private Long roleId;//所属角色id
	private Integer age;//0未知，1男，2女
	private String address;
	private String photo;//url
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", Sex=" + Sex + ", age=" + age
				+ ", address=" + address + ", photo=" + photo + "]";
	}
	
}
