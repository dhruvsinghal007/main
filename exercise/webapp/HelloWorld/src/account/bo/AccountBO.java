package account.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import account.db.AccountDB;
import account.dto.Account;

public class AccountBO implements InitializingBean{

	private Map<Integer, Account> accounts = AccountDB.getStudents() ;
	
	public Account view(int id) {
		// TODO Auto-generated method stub
		return accounts.get(id);
	}
	
	public int getTotal() {
		// TODO Auto-generated method stub
		List<Account> list = new ArrayList<>(accounts.values());
		return list.size();
	}
	
	public List<Account> viewAll(){
		List<Account> list = new ArrayList<>(accounts.values());
		
		return list;
				
	}
	
	public List<Account> viewAllPaginated(int start, int limit) {
		//System.out.println(start + " " + limit);
		List<Account> list = new ArrayList<>(accounts.values());
		if(start + limit  >= list.size()){
			return list.subList(start, list.size());
		}
		return list.subList(start, start + limit);
	}

	public Account add(Account account) {
		// TODO Auto-generated method stub
		 List<Account> list = new ArrayList<>(accounts.values());
		 int large = 0;
		 for(int i = 0;i < list.size();i++){
			 if(list.get(i).getId() > large){
				 large = list.get(i).getId();
			 }
		 }
		account.setId(large+1);
		accounts.put(account.getId(), account);
		return account;
	}

	public Account update(Account account) {
		// TODO Auto-generated method stub
		
		if(account.getId() >= 0){
			accounts.put(account.getId(), account);
			return account;
		}
		
		return null;
	}

	public boolean delete(int accountId) {
		// TODO Auto-generated method stub
		if(accountId >= 0){
			accounts.remove(accountId);
			return true;
		}
		
		return false;
	}
	
	public void multiDelete(List<Account> accountsParams) {
		// TODO Auto-generated method stub
		for(Account st : accountsParams){
			accounts.remove(st.getId());
		}
	}
	
	public List<Account> multiUpdate(List<Account> studentsParam) {
		// TODO Auto-generated method stub
		for(Account st : studentsParam){
			if(st.getId() > 0){
				accounts.put(st.getId(),st);
			}
		}
		return new ArrayList<>(accounts.values());
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
		for(int i = 1 ; i <= 9 ; i++){
			Account acc = new Account();
			acc.setId(i);
			acc.setName("acc" + i);
			acc.setDescription("Desc"+i);
			acc.setTown("town" + i);
			acc.setCell(8463839566l);
			acc.setEmail("email" + i);
			acc.setDob("13021995");
			acc.setType("savings");
			
			accounts.put(acc.getId(), acc);
		}
	
	}


	

}
