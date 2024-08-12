package com.cathaybk.practice.nt50347.b;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Database {
	public static final String CONN_URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	public static final String QUERY_CARS_SQL = "select * from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	public static final String INSERT_CARS_SQL = "insert into STUDENT.CARS (MANUFACTURER, TYPE, MIN_PRICE, PRICE) values (?, ?, ?, ?)";
	public static final String UPDATE_CARS_SQL = "update STUDENT.CARS set MIN_PRICE = ?, PRICE = ? where MANUFACTURER= ? and TYPE = ? ";
	public static final String DELETE_CARS_SQL = "delete from STUDENT.CARS where MANUFACTURER = ? and TYPE = ?";
	static PreparedStatement pstmt = null;

	public static void main(String[] args) throws Exception {
		print();
		try (Scanner input = new Scanner(System.in)) {
			Boolean reInput = true;
			while (reInput) {
				System.out.println("請選擇以下指令輸入: select、insert、update、delete");
				String order = input.next();

				switch (order) {
				case "insert":
					doInsert();
					reInput = false;
					break;
				case "update":
					doUpdate();
					reInput = false;
					break;
				case "select":
					doQuery();
					reInput = false;
					break;
				case "delete":
					doDelete();
					reInput = false;
					break;
				default:
					System.out.println("輸入錯誤!!");
				}
			}
		}
	}

	private static void doDelete() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				Scanner input = new Scanner(System.in);) {
			conn.setAutoCommit(false);
			System.out.print("請輸入製造商: ");
			String manufacturer = input.nextLine();
			System.out.print("請輸入類型: ");
			String type = input.nextLine();

			pstmt = conn.prepareStatement(DELETE_CARS_SQL);
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);
			pstmt.executeUpdate();

			System.out.println("刪除成功");
			conn.commit();
		} catch (Exception e) {
			System.out.println("刪除失敗， 原因: " + e.getMessage());
		}

	}

	private static void doQuery() {
		ResultSet resultSet = null;
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				Scanner input = new Scanner(System.in);) {

			System.out.print("請輸入製造商: ");
			String manufacturer = input.nextLine();
			System.out.print("請輸入類型: ");
			String type = input.nextLine();

			pstmt = conn.prepareStatement(QUERY_CARS_SQL);
			pstmt.setString(1, manufacturer);
			pstmt.setString(2, type);

			resultSet = pstmt.executeQuery();
			StringBuilder sb = new StringBuilder();

			while (resultSet.next()) {
				sb.append("製造商: ").append(resultSet.getString("MANUFACTURER")).append("，型號: ")
						.append(resultSet.getString("TYPE")).append("，底價: ").append(resultSet.getString("MIN_PRICE"))
						.append("，售價: ").append(resultSet.getString("PRICE"));
				System.out.println(sb.toString());
				sb.setLength(0);
			}
			System.out.println("查詢成功");

		} catch (Exception e) {
			System.out.println("Error in closing resultSet" + e.getMessage());
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private static void doUpdate() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				Scanner input = new Scanner(System.in)) {
			conn.setAutoCommit(false);
			Map<String, String> insertMap = new HashMap<>();
			conn.setAutoCommit(false);
			System.out.print("請輸入製造商: ");
			insertMap.put("MANUFACTURER", input.nextLine());
			System.out.print("請輸入類型: ");
			insertMap.put("TYPE", input.nextLine());
			System.out.print("請輸入底價: ");
			insertMap.put("MIN_PRICE", input.nextLine());
			System.out.print("請輸入售價: ");
			insertMap.put("PRICE", input.nextLine());

			pstmt = conn.prepareStatement(UPDATE_CARS_SQL);
			pstmt.setString(1, insertMap.get("MIN_PRICE"));
			pstmt.setString(2, insertMap.get("PRICE"));
			pstmt.setString(3, insertMap.get("MANUFACTURER"));
			pstmt.setString(4, insertMap.get("TYPE"));

			pstmt.executeUpdate();
			conn.commit();

			System.out.println("更新成功");

		} catch (SQLException e) {
			System.out.println("更新失敗， 原因: " + e.getMessage());
		}

	}

	private static void doInsert() {
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");
				Scanner input = new Scanner(System.in)) {
			Map<String, String> insertMap = new HashMap<>();

			System.out.print("請輸入製造商: ");
			insertMap.put("MANUFACTURER", input.nextLine());
			System.out.print("請輸入類型: ");
			insertMap.put("TYPE", input.nextLine());
			System.out.print("請輸入底價: ");
			insertMap.put("MIN_PRICE", input.nextLine());
			System.out.print("請輸入售價: ");
			insertMap.put("PRICE", input.nextLine());
			pstmt = conn.prepareStatement(INSERT_CARS_SQL);
			pstmt.setString(1, insertMap.get("MANUFACTURER"));
			pstmt.setString(2, insertMap.get("TYPE"));
			pstmt.setString(3, insertMap.get("MIN_PRICE"));
			pstmt.setString(4, insertMap.get("PRICE"));
			pstmt.executeUpdate();

			System.out.println("新增成功");

		} catch (Exception e) {
			System.out.println("新增失敗， 原因: " + e.getMessage());
		}
	}

	public static void print() {
		ResultSet resultSet = null;
		List<Map<String, String>> carList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(CONN_URL, "student", "student123456");) {

			PreparedStatement pstmt = conn.prepareStatement("select * from STUDENT.CARS");
			resultSet = pstmt.executeQuery();

			while (resultSet.next()) {
				Map<String, String> carMap = new HashMap<>();
				carMap.put("MANUFACTURER", resultSet.getNString("MANUFACTURER"));
				carMap.put("TYPE", resultSet.getString("TYPE"));
				carMap.put("MIN_PRICE", resultSet.getString("MIN_PRICE"));
				carMap.put("PRICE", resultSet.getString("PRICE"));
				carList.add(carMap);
			}

			for (Map<String, String> m : carList) {
				System.out.println(m);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e2) {
				System.out.println("Error in closing resultSet" + e2.getMessage());
			}
		}

	}
}
