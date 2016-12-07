package app.svc;

import java.util.List;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import app.bo.AppBO;
import app.dto.Customer;
import app.dto.Item;
import app.dto.Payment;
import app.dto.SalePurchase;

@Path("/")
@Component
public class AppService {

	private AppBO appBO;
	
	@Required
	public void setAppBO(AppBO appBO) {
		this.appBO = appBO;
	}

	/*
	 * testing the application if running properly
	 */
	
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@QueryParam("name") String name) {
		
		// http://localhost:8080/HelloSpring/student/test?name=dhruv
			
		return "Hello " + name;
	}
	
	/*
	 * below methods for viewing particular entry in db
	 */
	@GET
	@Path("/viewCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer viewCustomer(@QueryParam("id") int custId){

		// http://localhost:8080/HelloSpring/student/view?id=1
		
		Customer customer = appBO.viewCustomer(custId); 
		return customer;
	}
	
	@GET
	@Path("/viewItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Item viewItem(@QueryParam("id") int itemId){
		Item item = appBO.viewItem(itemId); 
		return item;
	}
	
	@GET
	@Path("/viewSalePurchase")
	@Produces(MediaType.APPLICATION_JSON)
	public SalePurchase viewSPEntry(@QueryParam("id") int spId){
		SalePurchase salePurchase = appBO.viewSPEntry(spId); 
		return salePurchase;
	}
	
	@GET
	@Path("/viewPayment")
	@Produces(MediaType.APPLICATION_JSON)
	public Payment viewPayment(@QueryParam("id") int trId){
		Payment payment = appBO.viewPayment(trId); 
		return payment;
	}
	
	/*@GET
	@Path("/totalCount")
	@Produces(MediaType.TEXT_PLAIN)
	public int getTotal(){

		// http://localhost:8080/HelloSpring/student/view?id=1
		return appBO.getTotal();
	}
	*/
	
	/*
	 * below methods for viewing all db entries in paginated fashion. Takes start and limit
	 * as parameters
	 */
	@GET
	@Path("/viewAllCustomers")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllCustomersPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<Customer> customers = appBO.viewAllCustomersPaginated(start,limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(Customer customer : customers){
			array.add(Json.createObjectBuilder().add("custId", customer.getCustId())
													.add("name", customer.getName())
													.add("address", customer.getAddress())
													.add("contact", customer.getContact()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", appBO.getTotalCustomers())
													.build();
		
		return json.toString();
	}
	
	@GET
	@Path("/viewAllItems")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllItemsPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<Item> items = appBO.viewAllItemsPaginated(start,limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(Item item : items){
			array.add(Json.createObjectBuilder().add("itemId", item.getItemId())
												.add("name", item.getName())
												.add("netQuantity", item.getNetQuantity())
												.add("cost", item.getCost()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", appBO.getTotalItems())
													.build();
		
		return json.toString();
	}
	
	@GET
	@Path("/viewAllSalesPurchases")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllSPEntriesPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<SalePurchase> salePurchases = appBO.viewAllSPEntriesPaginated(start,limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(SalePurchase spEntry : salePurchases){
			array.add(Json.createObjectBuilder().add("spId", spEntry.getSPId())
												.add("itemId", spEntry.getItemId())
												.add("date", spEntry.getDate())
												.add("quantity", spEntry.getQuantity())
												.add("mode", spEntry.getMode().toString()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", appBO.getTotalSPEntries())
													.build();
		
		return json.toString();
	}
	
	@GET
	@Path("/viewAllPayments")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllPaymentsPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<Payment> payments = appBO.viewAllPaymentsPaginated(start, limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(Payment payment : payments){
			array.add(Json.createObjectBuilder().add("trId", payment.getTrId())
												.add("spId", payment.getSpId())
												.add("custId", payment.getCustId())
												.add("mode", payment.getMode().toString())
												.add("amount", payment.getAmount()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", appBO.getTotalPayments())
													.build();
		
		return json.toString();
	}
	
	/*
	 * below methods for adding data to db
	 */
	@POST
	@Path("/addCustomer")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addCustomer(Customer customer){
		Customer cust = appBO.addCustomer(customer);
		JsonObject json = Json.createObjectBuilder().add("custId", cust.getCustId())
													.add("name", cust.getName())
													.add("address", cust.getAddress())
													.add("contact", cust.getContact())
													.build();
		return json.toString();
	}
	
	@POST
	@Path("/addItem")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addItem(Item itemParam){
		Item item = appBO.addItem(itemParam);
		JsonObject json = Json.createObjectBuilder().add("itemId", item.getItemId())
													.add("name", item.getName())
													.add("netQuantity", item.getNetQuantity())
													.add("cost", item.getCost())
													.build();
		return json.toString();
	}
	
	@POST
	@Path("/addSalePurchase")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSPEntry(SalePurchase spEntryParam){
		//System.out.println(spEntryParam);
		SalePurchase spEntry = appBO.addSPEntry(spEntryParam);
		JsonObject json = Json.createObjectBuilder().add("spId", spEntry.getSPId())
													.add("itemId", spEntry.getItemId())
													.add("date", spEntry.getDate())
													.add("quantity", spEntry.getQuantity())
													.add("mode", spEntry.getMode())
													.build();
		return json.toString();
	}
	
	@POST
	@Path("/addPayment")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addPayment(Payment paymentParam){
		Payment payment =  appBO.addPayment(paymentParam);
		JsonObject json = Json.createObjectBuilder().add("trId", payment.getTrId())
													.add("spId", payment.getSpId())
													.add("custId", payment.getCustId())
													.add("mode", payment.getMode())
													.add("amount", payment.getAmount())
													.build();
		return json.toString();
	}
	
	/*
	 * below methods for updating multiple db items. 
	 */
	@PUT
	@Path("/multiUpdateCustomers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> multiUpdateCustomers(List<Customer> customers){
		return appBO.multiUpdateCustomers(customers); 
	}
	
	@PUT
	@Path("/multiUpdateItems")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Item> multiUpdateItems(List<Item> items){
		return appBO.multiUpdateItems(items); 
	}
	
	@PUT
	@Path("/multiUpdateSalesPurchases")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SalePurchase> multiUpdateSPEntries(List<SalePurchase> spEntries){
		return appBO.multiUpdateSPEntries(spEntries); 
	}
	
	@PUT
	@Path("/multiUpdatePayments")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Payment> multiUpdatePayments(List<Payment> payments){
		return appBO.multiUpdatePayments(payments); 
	}
	
	/*
	 * below methods for deleting multiple db items. 
	 */
	@PUT
	@Path("/multiDeleteCustomers")
	@Consumes(MediaType.APPLICATION_JSON)
	public void multiDeleteCustomers(List<Customer> customers){
		appBO.multiDeleteCustomers(customers); 
	}
	
	@PUT
	@Path("/multiDeleteItems")
	@Consumes(MediaType.APPLICATION_JSON)
	public void multiDeleteItems(List<Item> items){
		appBO.multiDeleteItems(items); 
	}
	
	@PUT
	@Path("/multiDeleteSalesPurchases")
	@Consumes(MediaType.APPLICATION_JSON)
	public void multiDeleteSPEntries(List<SalePurchase> spEntries){
		appBO.multiDeleteSPEntries(spEntries); 
	}
	
	@PUT
	@Path("/multiDeletePayments")
	@Consumes(MediaType.APPLICATION_JSON)
	public void multiDeletePayments(List<Payment> payments){
		appBO.multiDeletePayments(payments); 
	}

}
