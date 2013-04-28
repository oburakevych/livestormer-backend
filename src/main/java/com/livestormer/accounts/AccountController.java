package com.livestormer.accounts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livestormer.accounts.completeness.AccountCompleteness;

@RequestMapping("/account/**")
@Controller
public class AccountController {
	@Autowired
	private AccountService accountService;
    
    @RequestMapping(value="signup", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> signup(@RequestBody String json) {
        Account account = Account.fromJsonToAccount(json);
        
        account = accountService.createAccount(account);
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        
        return new ResponseEntity<String>(account.toJson(), headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/{accountId}/completeness", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson(@PathVariable("accountId") Long accountId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        
        Account account = Account.findAccount(accountId);
        
        AccountCompleteness ac = AccountCompleteness.findAccountCompletenessesByAccount(account).getSingleResult();
        
        return new ResponseEntity<String>(ac.toJson(), headers, HttpStatus.OK);
    }
}
