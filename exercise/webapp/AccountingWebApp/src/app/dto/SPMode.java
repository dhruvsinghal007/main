package app.dto;

public enum SPMode {
	SALE("SALE"),
	PURCHASE("PURCHASE");
	
	private String key;
	
	private SPMode(String key){
		this.setKey(key);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
