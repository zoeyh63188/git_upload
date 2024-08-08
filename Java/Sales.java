package com.cathaybk.practice.nt50347.b;

public class Sales extends Employee {
	private int bonus;
	private int payment;

	public Sales(String name, String department, int salary, int sales) {
		super(name, department, salary);
		this.bonus = (int) (sales * 0.05);
		this.payment = salary + bonus;
	}

	public void printInfo() {
		super.printInfo();
		System.out.printf("業績獎金: %d\n總計: %d\n", bonus, payment);
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public int getPayment() {
		return payment;
	}

	public void setPayment(int payment) {
		this.payment = payment;
	}

}
