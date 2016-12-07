package app.dto;

public class Customer {
	private int custId;
	private String name;
	private String address;
	private long contact;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCustId() {
		return custId;
	}
	public void setCustId(int custId) {
		this.custId = custId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + custId + ", name=" + name + ", address="
				+ address + ", contact=" + contact + "]";
	}
}
