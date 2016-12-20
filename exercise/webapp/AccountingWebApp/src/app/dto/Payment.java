package app.dto;

public class Payment {
	private int trId,spId;
	private String mode;
	private int amount;
	
	public int getTrId() {
		return trId;
	}

	public void setTrId(int trId) {
		this.trId = trId;
	}

	public int getSPId() {
		return spId;
	}

	public void setSPId(int spId) {
		this.spId = spId;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		/*if(mode.equalsIgnoreCase("DEBIT")){
			this.mode = PaymentMode.DEBIT;
		}
		else if(mode.equalsIgnoreCase("CREDIT")){
			this.mode = PaymentMode.CREDIT;
		}
		else{
			System.out.println("Incorrect value... must be debit or credit");
		}*/
		this.mode = mode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Payment [transactionId=" + trId + ", SPId=" + spId + 
				", mode=" + mode + ", amount=" + amount + "]";
	}
}
