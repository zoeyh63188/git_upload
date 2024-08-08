package com.cathaybk.practice.nt50347.b;

public class Employee implements IWork {
	private String name;
	private String department;
	private int salary;

	// Constructor
	public Employee() {
	}

	public Employee(String name, String department, int salary) {
		this.name = name;
		this.department = department;
		this.salary = salary;
	}

	// 實作IWork
	@Override
	public void printInfo() {
		System.out.printf("薪資單\n姓名: %s 工作部門: %s\n月薪: %d\n", name, department, salary);
	}

	// getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

}
