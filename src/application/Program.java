package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter full file path: ");
		String path = sc.nextLine();

		try (BufferedReader br = new BufferedReader(new FileReader(path))) {
			List<Employee> emp = new ArrayList<>();

			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				emp.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
				line = br.readLine();
			}
			System.out.println();
			System.out.print("Enter the especified salary: ");
			double n = sc.nextDouble();
			System.out.println();
			System.out.println("Email of people whose salary is more than " + String.format("%.2f", n) + ": ");

			List<String> list = emp.stream().filter(x -> x.getSalary() > n).map(x -> x.getEmail()).sorted().collect(Collectors.toList());

			list.forEach(System.out::println);

			List<Employee> newList = emp.stream().filter(x -> x.getName().charAt(0) == 'M').collect(Collectors.toList());
			double sum = 0;
			for (Employee a : newList) {
				sum = sum + a.getSalary();
			}
			System.out.println();
			System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));

		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}

		sc.close();

	}

}
