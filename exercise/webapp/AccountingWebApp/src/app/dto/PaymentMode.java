package app.dto;

public enum PaymentMode {
	CREDIT("CREDIT"),
	DEBIT("DEBIT");
	
	private String key;
	
	private PaymentMode(String key){
		this.setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
