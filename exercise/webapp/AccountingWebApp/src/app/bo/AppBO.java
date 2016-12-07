package app.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import app.db.CustomerDB;
import app.db.ItemDB;
import app.db.PaymentDB;
import app.db.SalePurchaseDB;
import app.dto.Customer;
import app.dto.Item;
import app.dto.Payment;
import app.dto.SalePurchase;

public class AppBO implements InitializingBean{

	private Map<Integer, Item> items = ItemDB.getItems() ;
	private Map<Integer, Customer> customers = CustomerDB.getCustomers() ;
	private Map<Integer, SalePurchase> salePurchaseEntries = SalePurchaseDB.getSPItems();
	private Map<Integer, Payment> payments = PaymentDB.getPayments(); 
	
	/*
	 * below methods for getting total number of items 
	 */
	public int getTotalCustomers() {
		List<Customer> list = new ArrayList<>(customers.values());
		return list.size();
	}
	
	public int getTotalItems() {
		List<Item> list = new ArrayList<>(items.values());
		return list.size();
	}
	
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
	public Item viewItem(int id) {
		return items.get(id);
	}
	
	public Customer viewCustomer(int id) {
		return customers.get(id);
	}
	
	public Payment viewPayment(int id) {
		return payments.get(id);
	}
	
	public SalePurchase viewSPEntry(int id) {
		return salePurchaseEntries.get(id);
	}
	
	/*
	 * below methods for viewing all elements from db
	 */
	public List<Customer> viewAllCustomers(){
		List<Customer> list = new ArrayList<>(customers.values());
		return list;
	}
	
	public List<Item> viewAllItems(){
		List<Item> list = new ArrayList<>(items.values());
		return list;
	}
	
	public List<SalePurchase> viewAllSPEntries(){
		List<SalePurchase> list = new ArrayList<>(salePurchaseEntries.values());
		return list;
	}
	
	public List<Payment> viewAllPayments(){
		List<Payment> list = new ArrayList<>(payments.values());
		return list;
	}
	
	/*
	 * below methods for viewing all elements from db in paginated fashion (takes start and 
	 * limit)
	 */
	public List<Item> viewAllItemsPaginated(int start, int limit) {
		//System.out.println(start + " " + limit);
		List<Item> list = new ArrayList<>(items.values());
		if(start + limit  >= list.size()){
			return list.subList(start, list.size());
		}
		return list.subList(start, start + limit);
	}

	public List<Customer> viewAllCustomersPaginated(int start, int limit) {
		//System.out.println(start + " " + limit);
		List<Customer> list = new ArrayList<>(customers.values());
		if(start + limit  >= list.size()){
			return list.subList(start, list.size());
		}
		return list.subList(start, start + limit);
	}
	
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
	 * below methods for adding elements
	 */
	public Customer addCustomer(Customer customer) {
		 List<Customer> list = new ArrayList<>(customers.values());
		 int large = 0;
		 for(int i = 0;i < list.size();i++){
			 if(list.get(i).getCustId() > large){
				 large = list.get(i).getCustId();
			 }
		 }
		customer.setCustId(large+1);
		customers.put(customer.getCustId(), customer);
		return customer;
	}
	
	public Item addItem(Item item) {
		 List<Item> list = new ArrayList<>(items.values());
		 int large = 0;
		 for(int i = 0;i < list.size();i++){
			 if(list.get(i).getItemId() > large){
				 large = list.get(i).getItemId();
			 }
		 }
		item.setItemId(large+1);
		items.put(item.getItemId(), item);
		return item;
	}
	
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
		return speEntry;
	}
	
	public Payment addPayment(Payment payment) {
		 List<Payment> list = new ArrayList<>(payments.values());
		 int large = 0;
		 for(int i = 0;i < list.size();i++){
			 if(list.get(i).getTrId() > large){
				 large = list.get(i).getCustId();
			 }
		 }
		payment.setTrId(large+1);
		payments.put(payment.getTrId(), payment);
		return payment;
	}

	/*
	 * below methods for deleting multiple db items
	 */
	public void multiDeleteCustomers(List<Customer> customersParam) {
		for(Customer st : customersParam){
			customers.remove(st.getCustId());
		}
	}
	
	public void multiDeleteItems(List<Item> itemsParam) {
		for(Item st : itemsParam){
			items.remove(st.getItemId());
		}
	}
	
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
	public List<Customer> multiUpdateCustomers(List<Customer> customersParam) {
		for(Customer st : customersParam){
			if(st.getCustId() > 0){
				customers.put(st.getCustId(),st);
			}
		}
		return new ArrayList<>(customers.values());
	}
	
	public List<Item> multiUpdateItems(List<Item> itemsParam) {
		for(Item st : itemsParam){
			if(st.getItemId() > 0){
				items.put(st.getItemId(),st);
			}
		}
		return new ArrayList<>(items.values());
	}
	
	public List<SalePurchase> multiUpdateSPEntries(List<SalePurchase> spEntriesParam) {
		for(SalePurchase st : spEntriesParam){
			if(st.getSPId() > 0){
				salePurchaseEntries.put(st.getSPId(),st);
			}
		}
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
		
	}

}
