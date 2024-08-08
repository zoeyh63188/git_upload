package com.cathaybk.practice.nt50347.b;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HRMain {

	public static void main(String[] args) throws IOException {
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Sales("張志城", "信用卡部", 35000, 6000));
		employeeList.add(new Sales("林大鈞", "保代部", 38000, 4000));
		employeeList.add(new Supervisor("李中白", "資訊部", 65000));
		employeeList.add(new Supervisor("林小中", "理財部", 80000));
		for (Employee employee : employeeList) {
			employee.printInfo();
		}

		File file = new File("C:\\Users\\Admin\\Documents\\output.csv");

		try (BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(
				new FileOutputStream("C:\\Users\\Admin\\Documents\\output.csv"), StandardCharsets.UTF_8)));) {
			if (!file.exists()) {
				file.createNewFile();
			}

			for (int i = 0; i < employeeList.size(); i++) {
				if (employeeList.get(i) instanceof Sales) {
					bufferedWriter.write(
							employeeList.get(i).getName() + ", " + ((Sales) employeeList.get(i)).getPayment() + "\n");
				} else {
					bufferedWriter.write(employeeList.get(i).getName() + ", "
							+ ((Supervisor) employeeList.get(i)).getPayment() + "\n");
				}

			}
			System.out.println("File written Successfully");

		} catch (IOException e) {
			System.out.println("File written Failed");
			e.printStackTrace();
			return;
		}
	}

}
