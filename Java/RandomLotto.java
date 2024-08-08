package com.cathaybk.practice.nt50347.b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomLotto {

	public static void main(String[] args) {
		List<Integer> number = new ArrayList<>();
		System.out.print("排序前:");
		while (number.size() < 6) {
			int num = (int) (Math.random() * 48 + 1);

			number.add(num);

		}
		for (Integer n : number) {
			System.out.print(n + " ");
		}

		Collections.sort(number);
		System.out.print("\n排序後:");
		for (int n : number) {
			System.out.print(n + " ");
		}

	}

}
