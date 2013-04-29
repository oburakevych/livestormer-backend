package com.livestormer.accounts.owners;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.livestormer.accounts.Account;

@RequestMapping("/owner/**")
@Controller
@RooWebJson(jsonObject = Owner.class)
public class OwnerController {    
    @RequestMapping(value="authenticate", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> authenticate(@RequestBody String json) {
        Owner owner = Owner.fromJsonToOwner(json);
        
        owner = Owner.getOwner(owner.getUsername(), owner.getPassword());
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        
        if (owner == null) {
        	return new ResponseEntity<String>("errorMessage: 'Invalid e-mail or password'", headers, HttpStatus.NOT_FOUND);
        }
        
        Account account = Account.getAccount(owner);
        
        if (account == null) {
        	return new ResponseEntity<String>("errorMessage: 'Invalid e-mail or password'", headers, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<String>(account.toJson(), headers, HttpStatus.OK);
    }
}
