package com.hao.app.commons.entity;

import java.io.Serializable;

public class DemoBean implements Serializable{
	
	private static final long serialVersionUID = 3661640798211651911L;

	private String id;
	
	private String name;
	
	private int age;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + "]";
	}
	
	

}
