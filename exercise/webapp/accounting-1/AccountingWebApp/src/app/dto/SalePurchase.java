package app.dto;

public class SalePurchase {
	private int itemId,spId;
	private int quantity;
	private String date;
	private String mode;
	
	public int getSPId() {
		return spId;
	}
	public void setSPId(int spId) {
		this.spId = spId;
	}
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "SalePurchase [SPId =" + spId + ", itemId=" + itemId + ", date=" + date + ", "
				+ "quantity=" + quantity + ", mode=" + mode + "]";
	}
}
