package app.dto;

public class SalePurchase {
	private int itemId,spId;
	private int quantity;
	private String date;
	private String mode;
	
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
	
	public int getSPId() {
		return itemId;
	}
	public void setSPId(int spId) {
		this.itemId = spId;
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
		/*if(mode.equalsIgnoreCase("SALE")){
			this.mode = SPMode.SALE;
		}
		else if(mode.equalsIgnoreCase("PURCHASE")){
			this.mode = SPMode.PURCHASE;
		}
		else{
			System.out.println("Incorrect value... must be sale or purchase");
		}*/
		this.mode = mode;
	}
	@Override
	public String toString() {
		return "SalePurchase [SPId =" + spId + "itemId=" + itemId + ", date=" + date + ", "
				+ "quantity=" + quantity + ", mode=" + mode + "]";
	}
}
