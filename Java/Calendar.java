package com.cathaybk.practice.nt50347.b;

import java.time.LocalDate;
import java.time.Year;
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
			int year = Year.now().getValue();
			YearMonth yearMonth = YearMonth.of(year, month);
			LocalDate firstDateOfMonth = yearMonth.atDay(1);
			LocalDate endDateOfMonth = yearMonth.atEndOfMonth();

			System.out.printf("        %s年%s月\n", year, month);
			System.out.printf("---------------------------\n");
			System.out.printf("日   一   二   三  四   五  六 \n");
			System.out.printf("===========================\n");

			int firstDay = firstDateOfMonth.getDayOfWeek().getValue();
			if (firstDay != 7) {
				for (int i = 0; i < firstDay; i++) {
					System.out.print("    ");
				}
			}

			for (int j = 1; j < endDateOfMonth.getDayOfMonth() + 1; j++) {
				System.out.printf("%2d  ", j);

				if ((j + firstDay) % 7 == 0) {
					System.out.println();
				}

			}

		}

	}

}
