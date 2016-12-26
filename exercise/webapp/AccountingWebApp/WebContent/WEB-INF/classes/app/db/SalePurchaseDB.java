package app.db;

import java.util.HashMap;
import java.util.Map;

import app.dto.SalePurchase;

public class SalePurchaseDB {
	private static Map<Integer, SalePurchase> spItems = new HashMap<>();

	public static Map<Integer, SalePurchase> getSPItems() {
		return spItems;
	}
}
