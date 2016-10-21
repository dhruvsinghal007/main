package account.db;

import java.util.HashMap;
import java.util.Map;

import account.dto.Account;

public class AccountDB {
	private static Map<Integer, Account> students = new HashMap<>();

	public static Map<Integer, Account> getStudents() {
		return students;
	}
}
