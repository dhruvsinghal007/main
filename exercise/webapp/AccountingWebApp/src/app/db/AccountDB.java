package app.db;

import java.util.HashMap;
import java.util.Map;

import app.dto.Account;

public class AccountDB {
	private static Map<Integer, Account> accounts = new HashMap<>();

	public static Map<Integer, Account> getAccounts() {
		return accounts;
	}
}
