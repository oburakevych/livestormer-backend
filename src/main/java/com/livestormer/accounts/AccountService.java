package com.livestormer.accounts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.livestormer.accounts.activities.Activity;
import com.livestormer.accounts.completeness.AccountCompleteness;
import com.livestormer.accounts.completeness.tasks.Task;
import com.livestormer.accounts.completeness.tasks.completeness.TaskCompleteness;
import com.livestormer.accounts.owners.Owner;

@Service
public class AccountService {
	@Transactional
	public Account createAccount(Account account) {
        if (account.getOwner() == null || account.getOwner().getUsername() == null) {
        	// TODO: throw an exception
        }
        
        account.getOwner().setUsername(account.getOwner().getUsername().toLowerCase());
        
        account.persist();
        
        Owner owner = Owner.getOwner(account.getOwner().getUsername(), account.getOwner().getPassword());
        
        if (owner == null) {
        	return null;
        }
        
        account = Account.getAccount(owner);
        
        afterAccountCreated(account);
        
        return account;
	}
	
	private void afterAccountCreated(Account account) {
		if (account.getId() != null) {
			List<Task> tasks = Task.findTasksByType(account.getType());
			AccountCompleteness ac = generateAccountCompleteness(account, tasks);
			
			ac.persist();
			
			generateActivity(account);
		}
	}
	
	private AccountCompleteness generateAccountCompleteness(Account account, List<Task> tasks) {
		AccountCompleteness ac = new AccountCompleteness();
		ac.setAccount(account);
		ac.setVersion(0);
		
		List<TaskCompleteness> taskCompletenesses = new ArrayList<TaskCompleteness>();
		
		for (Task task : tasks) {
			TaskCompleteness tc = new TaskCompleteness();
			tc.setTask(task);
			tc.setIsCompleted(false);
			tc.setVersion(0);
			
			taskCompletenesses.add(tc);
		}
		
		ac.setTaskCompletenesses(taskCompletenesses);

		return ac;
	}
	
	private void generateActivity(Account account) {
		Activity.addActivity(account.getId(), "Account created");
	}
}
