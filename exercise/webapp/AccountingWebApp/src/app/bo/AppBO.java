package app.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import app.db.AccountDB;
import app.db.PaymentDB;
import app.db.SalePurchaseDB;
import app.dto.Account;
import app.dto.Payment;
import app.dto.SalePurchase;

public class AppBO implements InitializingBean{

	private Map<Integer, SalePurchase> salePurchaseEntries = SalePurchaseDB.getSPItems();
	private Map<Integer, Payment> payments = PaymentDB.getPayments(); 
	private Map<Integer, Account> accounts = AccountDB.getAccounts();
	
	/*
	 * below methods for getting total number of items 
	 */
	
	public int getTotalSPEntries() {
		List<SalePurchase> list = new ArrayList<>(salePurchaseEntries.values());
		return list.size();
	}
	
	public int getTotalPayments() {
		List<Payment> list = new ArrayList<>(payments.values());
		return list.size();
	}
	
	/*
	 * below methods for viewing a particular element from db
	 */
	public Payment viewPayment(int id) {
		return payments.get(id);
	}
	
	public SalePurchase viewSPEntry(int id) {
		return salePurchaseEntries.get(id);
	}
	
	public Account viewAccount(int id) {
		return accounts.get(id);
	}
	
	/*
	 * below methods for viewing all elements from db
	 */
	public List<SalePurchase> viewAllSPEntries(){
		List<SalePurchase> list = new ArrayList<>(salePurchaseEntries.values());
		return list;
	}
	
	public List<Payment> viewAllPayments(){
		List<Payment> list = new ArrayList<>(payments.values());
		return list;
	}
	
	public List<Account> viewAllAccounts(){
		List<Account> list = new ArrayList<>(accounts.values());
		return list;
	}

	
	/*
	 * below methods for viewing all elements from db in paginated fashion (takes start and 
	 * limit)
	 */
	public List<SalePurchase> viewAllSPEntriesPaginated(int start, int limit) {
		//System.out.println(start + " " + limit);
		List<SalePurchase> list = new ArrayList<>(salePurchaseEntries.values());
		if(start + limit  >= list.size()){
			return list.subList(start, list.size());
		}
		return list.subList(start, start + limit);
	}
	
	public List<Payment> viewAllPaymentsPaginated(int start, int limit) {
		//System.out.println(start + " " + limit);
		List<Payment> list = new ArrayList<>(payments.values());
		if(start + limit  >= list.size()){
			return list.subList(start, list.size());
		}
		return list.subList(start, start + limit);
	}
	
	/*
	 * below methods for credit and debit list for salepurchase per date
	 */
	public Map<SalePurchase, Payment> viewCreditEntries(String date){
		Map<SalePurchase, Payment> map = new HashMap<>();
		List<SalePurchase> spList = new ArrayList<>(salePurchaseEntries.values());
		List<Payment> paymentList = new ArrayList<>(payments.values());
		for(SalePurchase sp : spList){
			if(sp.getDate().equals(date) && sp.getMode().equals("Sale")){
				for(Payment p : paymentList){
					if(p.getSPId() == sp.getSPId()){
						map.put(sp, p);
					}
				}
			}
		}
		return map;
	}
	
	public Map<SalePurchase, Payment> viewDebitEntries(String date){
		Map<SalePurchase, Payment> map = new HashMap<>();
		List<SalePurchase> spList = new ArrayList<>(salePurchaseEntries.values());
		List<Payment> paymentList = new ArrayList<>(payments.values());
		for(SalePurchase sp : spList){
			if(sp.getDate().equals(date) && sp.getMode().equals("Purchase")){
				for(Payment p : paymentList){
					if(p.getSPId() == sp.getSPId()){
						map.put(sp, p);
					}
				}
			}
		}
		return map;
	}
	
	/*
	 * below methods for adding elements
	 */
	
	public SalePurchase addSPEntry(SalePurchase speEntry) {
		 List<SalePurchase> list = new ArrayList<>(salePurchaseEntries.values());
		 int large = 0;
		 for(int i = 0;i < list.size();i++){
			 if(list.get(i).getSPId() > large){
				 large = list.get(i).getSPId();
			 }
		 }
		speEntry.setSPId(large+1);
		salePurchaseEntries.put(speEntry.getSPId(), speEntry);
		//System.out.println(salePurchaseEntries);
		return speEntry;
	}
	
	public Payment addPayment(Payment payment) {
		 List<Payment> list = new ArrayList<>(payments.values());
		 int large = 0;
		 for(int i = 0;i < list.size();i++){
			 if(list.get(i).getTrId() > large){
				 large = list.get(i).getTrId();
			 }
		 }
		payment.setTrId(large+1);
		payments.put(payment.getTrId(), payment);
		return payment;
	}
	
	/*
	 * below methods for deleting multiple db items
	 */
	public void multiDeleteSPEntries(List<SalePurchase> spEntriesParam) {
		for(SalePurchase st : spEntriesParam){
			salePurchaseEntries.remove(st.getSPId());
		}
	}
	
	public void multiDeletePayments(List<Payment> paymentsParam) {
		for(Payment st : paymentsParam){
			payments.remove(st.getTrId());
		}
	}
	
	/*
	 * below methods for updating multiple db items
	 */
	public List<SalePurchase> multiUpdateSPEntries(List<SalePurchase> spEntriesParam) {
		for(SalePurchase st : spEntriesParam){
			if(st.getSPId() > 0){
				salePurchaseEntries.put(st.getSPId(),st);
			}
		}
		//System.out.println(spEntriesParam);
		/*System.out.println(salePurchaseEntries);*/
		return new ArrayList<>(salePurchaseEntries.values());
	}
	
	public List<Payment> multiUpdatePayments(List<Payment> paymentsParam) {
		for(Payment st : paymentsParam){
			if(st.getTrId() > 0){
				payments.put(st.getTrId(),st);
			}
		}
		return new ArrayList<>(payments.values());
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		for(int i = 1 ; i <= 6 ; i++){
			SalePurchase sp = new SalePurchase();
			sp.setSPId(i);
			sp.setItemId(i);
			sp.setDate("10122016");
			sp.setMode("Sale");
			sp.setQuantity(1);
			salePurchaseEntries.put(sp.getSPId(), sp);
			
			Payment payment = new Payment();
			payment.setTrId(i);
			payment.setSPId(i);
			payment.setMode("Debit");
			payment.setAmount(i*100);
			payments.put(payment.getTrId(), payment);
			
		}
		int id = 1;
		for(int i = 1 ; i < 6 ; i++){
			Account acc = new Account();
			acc.setAccId(id++);
			acc.setName("Coomodity_" + i);
			acc.setType("Commodity");
			accounts.put(acc.getAccId(), acc);
		}
		for(int i = 1 ; i < 6 ; i++){
			Account acc = new Account();
			acc.setAccId(id++);
			acc.setName("Person_" + i);
			acc.setType("Person");
			accounts.put(acc.getAccId(), acc);
		}
	}

}
