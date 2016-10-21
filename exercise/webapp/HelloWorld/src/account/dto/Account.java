package account.dto;

public class Account {
	private int id;
	private String name;
	private String desc;
	private String town;
	private long cell;
	private String email;
	private String dob;
	private String type;
	
	@Override
	public String toString() {
		return "Account [Id=" + id + ", name=" + name + ", description="
				+ desc + ", town=" + town + ", cell no.=" + cell 
				+ ", email=" + email + ", DOB=" + dob + ", type=" + type + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return desc;
	}

	public void setDescription(String description) {
		this.desc = description;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public long getCell() {
		return cell;
	}

	public void setCell(long cell) {
		this.cell = cell;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
