package com.livestormer.accounts.owners;

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

import com.livestormer.accounts.Account;

@RequestMapping("/owner/**")
@Controller
@RooWebJson(jsonObject = Owner.class)
public class OwnerController {

    @RequestMapping(method = RequestMethod.POST, value = "{id}")
    public void post(@PathVariable Long id, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
    }

    @RequestMapping
    public String index() {
        return "owner/index";
    }
    
    @RequestMapping(value="authenticate", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> authenticate(@RequestBody String json) {
        Owner owner = Owner.fromJsonToOwner(json);
        
        owner = Owner.getOwner(owner.getUsername(), owner.getPassword());
        
        if (owner == null) {
        	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        
        Account account = Account.getAccount(owner);
        
        if (account == null) {
        	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        
        return new ResponseEntity<String>(account.toJson(), headers, HttpStatus.OK);
    }
}
