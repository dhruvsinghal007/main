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
import app.dto.Account;
import app.dto.Payment;
import app.dto.SalePurchase;
import app.dto.Self;

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
	
	@GET
	@Path("/viewAccount")
	@Produces(MediaType.APPLICATION_JSON)
	public Account viewAccount(@QueryParam("id") int id){
		Account account = appBO.viewAccount(id); 
		return account;
	}
	
	@GET
	@Path("/viewSelf")
	@Produces(MediaType.APPLICATION_JSON)
	public Self viewSelf(){
		Self self = appBO.getSelf(); 
		return self;
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
	@Path("/viewAllSalesPurchases")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllSPEntriesPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<SalePurchase> salePurchases = appBO.viewAllSPEntriesPaginated(start,limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(SalePurchase spEntry : salePurchases){
			array.add(Json.createObjectBuilder().add("SPId", spEntry.getSPId())
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
												.add("SPId", payment.getSPId())
												.add("mode", payment.getMode().toString())
												.add("amount", payment.getAmount()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", appBO.getTotalPayments())
													.build();
		
		return json.toString();
	}
	
	@GET
	@Path("/viewAllAccountsPaginated")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllAccountssPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<Account> accounts = appBO.viewAllAccountsPaginated(start, limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(Account account : accounts){
			array.add(Json.createObjectBuilder().add("accId", account.getAccId())
												.add("name", account.getName())
												.add("type", account.getType()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", appBO.getTotalAccounts())
													.build();
		
		return json.toString();
	}
	
	@GET
	@Path("/viewAllAccounts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> viewAllAccounts(){
		List<Account> accounts = appBO.viewAllAccounts();
		
		return accounts;
	}
	
	/*
	 * below methods for daily records of credit and debit
	 */
	
	@POST
	@Path("/addSalePurchase")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addSPEntry(SalePurchase spEntryParam){
		//System.out.println(spEntryParam);
		SalePurchase spEntry = appBO.addSPEntry(spEntryParam);
		JsonObject json = Json.createObjectBuilder().add("SPId", spEntry.getSPId())
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
													.add("SPId", payment.getSPId())
													.add("mode", payment.getMode())
													.add("amount", payment.getAmount())
													.build();
		return json.toString();
	}
	
	@POST
	@Path("/addAccount")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addAccount(Account accountParam){
		Account account =  appBO.addAccount(accountParam);
		JsonObject json = Json.createObjectBuilder().add("accId", account.getAccId())
													.add("name", account.getName())
													.add("type", account.getType())
													.build();
		return json.toString();
	}
	
	/*
	 * below methods for updating multiple db items. 
	 */
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
	
	@PUT
	@Path("/multiUpdateAccounts")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> multiUpdateAccounts(List<Account> accounts){
		return appBO.multiUpdateAccounts(accounts); 
	}
	
	/*
	 * below methods for deleting multiple db items. 
	 */
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
	
	@PUT
	@Path("/multiDeleteAccounts")
	@Consumes(MediaType.APPLICATION_JSON)
	public void multiDeleteAccounts(List<Account> accounts){
		appBO.multiDeleteAccounts(accounts); 
	}

}
