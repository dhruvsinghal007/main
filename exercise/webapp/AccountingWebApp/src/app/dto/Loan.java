package app.dto;

public class Loan {
	private int loanId,custId;
	private String mode,date;
	private int amount;
	
	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}
	
	public int getCustId() {
		return custId;
	}

	public void setCustId(int custId) {
		this.custId = custId;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Loan [LoanId=" + loanId + 
				", mode=" + mode + ", amount=" + amount + ", date= " + date + "]";
	}
}
