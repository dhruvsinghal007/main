package app.db;

import java.util.HashMap;
import java.util.Map;

import app.dto.Customer;

public class CustomerDB {
	private static Map<Integer, Customer> customers = new HashMap<>();

	public static Map<Integer, Customer> getCustomers() {
		return customers;
	}
}
