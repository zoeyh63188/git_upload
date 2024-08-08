package com.cathaybk.practice.nt50347.b;

public class NineNineTable {

	public static void main(String[] args) {
		for (int i = 1; i < 10; i++) {
			for (int j = 2; j < 10; j++) {
				int product = j * i;
				if (j == 9) {
					System.out.printf("%d*%d=%2d \n", j, i, product);
				} else {
					System.out.printf("%d*%d=%2d ", j, i, product);
				}

			}

		}

	}

}
