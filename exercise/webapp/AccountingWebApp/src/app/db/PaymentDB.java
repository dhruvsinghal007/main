package app.db;

import java.util.HashMap;
import java.util.Map;

import app.dto.Payment;

public class PaymentDB {
	private static Map<Integer, Payment> payments = new HashMap<>();

	public static Map<Integer, Payment> getPayments() {
		return payments;
	}
}
