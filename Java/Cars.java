package com.cathaybk.practice.nt50347.b;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Cars {
	static String[] title = new String[4];
	public static void main(String[] args) throws IOException {
		List<HashMap<String, String>> carList = new ArrayList<>();
		
		try (BufferedReader bufferedReader = new BufferedReader(
				new FileReader("C:\\Users\\Admin\\Documents\\cars.csv"))) {
			String line = bufferedReader.readLine();
			title = line.split(",");
			while ((line = bufferedReader.readLine()) != null) {
				String[] parts = line.split(",");
				HashMap<String, String> map = new HashMap<>();
				map.put(title[0], parts[0]);
				map.put(title[1], parts[1]);
				map.put(title[2], parts[2]);
				map.put(title[3], parts[3]);
				carList.add(map);
			}
			carList.remove(0);
			Collections.sort(carList, new Comparator<HashMap<String, String>>() {

				@Override
				public int compare(HashMap<String, String> map1, HashMap<String, String> map2) {
					String price1 = map1.get(title[3]);
					String price2 = map2.get(title[3]);
					return price2.compareTo(price1);
				}

			});

			File file = new File("C:\\Users\\Admin\\Documents\\cars2.csv");
			try (BufferedWriter bufferedWriter = new BufferedWriter(
					(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)));) {
				if (!file.exists()) {
					file.createNewFile();
				}

				StringBuilder sb = new StringBuilder();
				bufferedWriter.write(sb.append("MANUFACTURER").append(", ").append("TYPE").append(", ")
						.append("MIN_PRICE").append(", ").append("PRICE").append("\n").toString());
				sb.setLength(0);
				for (HashMap<String, String> c : carList) {

					bufferedWriter.write(sb.append(c.get("MANUFACTURER")).append(", ").append(c.get("TYPE"))
							.append(", ").append(c.get("MIN_PRICE")).append(", ").append(c.get("PRICE")).append("\n")
							.toString());
					sb.setLength(0);
				}
				System.out.println("File written Successfully");

			} catch (IOException e) {
				System.out.println("File written Failed");
				e.printStackTrace();
				return;
			}
		} catch (IOException e) {
			e.printStackTrace();

		}

		Collections.sort(carList, new Comparator<HashMap<String, String>>() {

			@Override
			public int compare(HashMap<String, String> map1, HashMap<String, String> map2) {
				String manufacturer1 = map1.get(title[0]);
				String manufacturer2 = map2.get(title[0]);

				return manufacturer1.compareTo(manufacturer2);
			}
		});

		HashMap<String, String> previousMap = new HashMap<>();
		BigDecimal Price = BigDecimal.ZERO;
		BigDecimal MinPrice = BigDecimal.ZERO;
		BigDecimal sumPrice = BigDecimal.ZERO;
		BigDecimal sumMinPrice = BigDecimal.ZERO;
		BigDecimal totalMinPrice = BigDecimal.ZERO;
		BigDecimal totalPrice = BigDecimal.ZERO;
		StringBuilder sb = new StringBuilder();
		sb.append(title[0]).append(",").append(title[1]).append(",").append(title[2]).append(",").append(title[3]);
		System.out.println(sb.toString());
		sb.setLength(0);
		for (HashMap<String, String> currentMap : carList) {
			Price = new BigDecimal(currentMap.get(title[3]));
			MinPrice = new BigDecimal(currentMap.get(title[2]));
			if (!previousMap.isEmpty()) {
				String previousManufacturer = previousMap.get(title[0]);
				String currentManufacturer = currentMap.get(title[0]);

				if ((!previousManufacturer.equals(currentManufacturer))) {
					sumUp(sumMinPrice, sumPrice);

					sumMinPrice = BigDecimal.ZERO;
					sumPrice = BigDecimal.ZERO;
				}
			}

			sumMinPrice = sumMinPrice.add(MinPrice);
			sumPrice = sumPrice.add(Price);
			totalMinPrice = totalMinPrice.add(MinPrice);
			totalPrice = totalPrice.add(Price);

			sb.append(String.format("%-13s   ", currentMap.get(title[0])))
					.append(String.format("%-10s   ", currentMap.get(title[1])))
					.append(String.format("%-6s      ", MinPrice.toPlainString()))
					.append(String.format("%-6s", Price.toPlainString()));
			System.out.println(sb.toString());
			sb.setLength(0);

			previousMap = new HashMap<String, String>(currentMap);
		}
		sumUp(sumMinPrice, sumPrice);

		sb.append("合計　").append(String.format("%25s", " "))
				.append(String.format("%-6s      ", totalMinPrice.toPlainString()))
				.append(String.format("%-6s", totalPrice.toPlainString()));
		System.out.println(sb.toString());
		sb.setLength(0);

	}

	private static void sumUp(BigDecimal sumMinPrice, BigDecimal sumPrice) {
		StringBuilder sb = new StringBuilder();
		sb.append("小計　").append(String.format("%25s", " "))
				.append(String.format("%-6s      ", sumMinPrice.toPlainString()))
				.append(String.format("%-6s      ", sumPrice.toPlainString()));
		System.out.println(sb.toString());
		sb.setLength(0);
	}

}
