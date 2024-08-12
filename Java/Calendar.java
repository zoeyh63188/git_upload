package com.cathaybk.practice.nt50347.b;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Scanner;

public class Calendar {

	public static void main(String[] args) {
		try (Scanner input = new Scanner(System.in)) {
			System.out.print("輸入介於1-12的整數m: ");
			int month = input.nextInt();
			if (month < 1 || month > 12) {
				System.out.println("輸入錯誤! 數字介於1 - 12");
				return;
			}
			input.close();

			YearMonth yearMonth = YearMonth.of(2024, month);
			LocalDate firstDateOfMonth = yearMonth.atDay(1);
			LocalDate endDateOfMonth = yearMonth.atEndOfMonth();

			System.out.printf("        2024年%s月\n", month);
			System.out.printf("---------------------------\n");
			System.out.printf("日   一   二   三  四   五  六 \n");
			System.out.printf("===========================\n");

			int firstDay = firstDateOfMonth.getDayOfWeek().getValue();
			if (firstDay != 7) {
				for (int i = 0; i < firstDay; i++) {
					System.out.print("    ");
				}
			}

			for (int day = 1; day < endDateOfMonth.getDayOfMonth() + 1; day++) {
				System.out.printf("%2d  ", day);

				if ((day + firstDay) % 7 == 0) {
					System.out.println();
				}

			}

		}

	}

}
