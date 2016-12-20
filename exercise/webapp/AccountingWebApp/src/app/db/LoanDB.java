package app.db;

import java.util.HashMap;
import java.util.Map;

import app.dto.Loan;

public class LoanDB {
	private static Map<Integer, Loan> loans = new HashMap<>();

	public static Map<Integer, Loan> getLoans() {
		return loans;
	}
}
