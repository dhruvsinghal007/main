package app.dto;

public class Item {
	private int itemId;
	private String name;
	private int netQuantity;
	private int cost;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public int getNetQuantity() {
		return netQuantity;
	}
	public void setNetQuantity(int netQuantity) {
		this.netQuantity = netQuantity;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", name=" + name + ", netQuantity="
				+ netQuantity + ", cost per unit=" + cost + "]";
	}
}
