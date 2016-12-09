package app.db;

import java.util.HashMap;
import java.util.Map;

import app.dto.Item;

public class ItemDB {
	private static Map<Integer, Item> items = new HashMap<>();

	public static Map<Integer, Item> getItems() {
		return items;
	}
}
