package com.cathaybk.practice.nt50347.b;

import java.util.Arrays;

public class RandomLotto {

	public static void main(String[] args) {
		int[] number = new int[6];
		System.out.print("排序前:");
		for (int i = 0; i < 6; i++) {
			int num = (int) (Math.random() * 50 + 1);
			if (!Arrays.asList(number).contains(num)) {
				number[i] = (int) (Math.random() * 50 + 1);
				System.out.print(number[i] + " ");
			}
		}

		Arrays.sort(number);
		System.out.print("\n排序後:");
		for (int n : number) {
			System.out.print(n + " ");
		}

	}

}
