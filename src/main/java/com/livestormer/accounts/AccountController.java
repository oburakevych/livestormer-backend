package com.livestormer.accounts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.livestormer.accounts.owners.Owner;

@RequestMapping("/account/**")
@Controller
@RooWebJson(jsonObject = Account.class)
public class AccountController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "account/index";
    }
    
    @RequestMapping(value="signup", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> signup(@RequestBody String json) {
        Account account = Account.fromJsonToAccount(json);
        
        if (account.getOwner() == null || account.getOwner().getUsername() == null) {
        	// TODO: throw an exception
        }
        
        account.getOwner().setUsername(account.getOwner().getUsername().toLowerCase());
        
        account.persist();
        
        Owner owner = Owner.getOwner(account.getOwner().getUsername(), account.getOwner().getPassword());
        
        if (owner != null) {
        	account = Account.getAccount(owner);
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        
        return new ResponseEntity<String>(account.toJson(), headers, HttpStatus.CREATED);
    }
}
