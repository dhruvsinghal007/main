package account.svc;

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

import account.bo.AccountBO;
import account.dto.Account;

@Path("/")
@Component
public class AccountService {

	private AccountBO AccountBO;

	@Required
	public void setAccountBO(AccountBO accountBO) {
		this.AccountBO = accountBO;
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String sayHello(@QueryParam("name") String name) {
		
		// http://localhost:8080/HelloSpring/student/test?name=dhruv
			
		return "Hello " + name;
	}
	
	
	@GET
	@Path("/view")
	@Produces(MediaType.APPLICATION_JSON)
	public Account view(@QueryParam("id") int accountId){

		// http://localhost:8080/HelloSpring/student/view?id=1
		
		Account account = AccountBO.view(accountId); 
		return account;
	}
	
	@GET
	@Path("/totalCount")
	@Produces(MediaType.TEXT_PLAIN)
	public int getTotal(){

		// http://localhost:8080/HelloSpring/student/view?id=1
		return AccountBO.getTotal();
	}
	
//	@GET
//	@Path("/viewAll")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Student> viewAll(){
//		List<Student> student = studentBO.viewAll(); 
//		return student;
//	}
	
	@GET
	@Path("/viewAll")
	@Produces(MediaType.TEXT_PLAIN)
	public String viewAllPaginated(@QueryParam("start") int start,
											@QueryParam("limit") int limit){
		List<Account> accounts = AccountBO.viewAllPaginated(start,limit);
		
		JsonArrayBuilder array = Json.createArrayBuilder();
		
		for(Account account : accounts){
			array.add(Json.createObjectBuilder().add("id", account.getId())
													.add("name", account.getName())
													.add("desc", account.getDescription())
													.add("town", account.getTown())
													.add("cell", account.getCell())
													.add("email", account.getEmail())
													.add("dob", account.getDob())
													.add("type", account.getType()));
		}
		
		JsonObject json = Json.createObjectBuilder().add("data", array)
													.add("total", getTotal()).build();
		
		return json.toString();
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Account add(Account account){
		System.out.println(account);
		return AccountBO.add(account); 
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Account update(Account account){
		return AccountBO.update(account); 
	}
	
	@GET
	@Path("/delete")
	public boolean delete(@QueryParam("id") int accountId) {
		System.out.println("passed id in service = "+accountId);
		return AccountBO.delete(accountId);
	}

	@PUT
	@Path("/multiUpdate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Account> multiUpdate(List<Account> accounts){
		return AccountBO.multiUpdate(accounts); 
	}
	
	@PUT
	@Path("/multiDelete")
	@Consumes(MediaType.APPLICATION_JSON)
	public void multiDelete(List<Account> accounts){
		AccountBO.multiDelete(accounts); 
	}
	
	// TODO - Add methods for add, delete, update

}
