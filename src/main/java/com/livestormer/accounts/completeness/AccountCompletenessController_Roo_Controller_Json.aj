// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.livestormer.accounts.completeness;

import com.livestormer.accounts.Account;
import com.livestormer.accounts.completeness.AccountCompleteness;
import com.livestormer.accounts.completeness.AccountCompletenessController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

privileged aspect AccountCompletenessController_Roo_Controller_Json {
    
    @RequestMapping(value = "/{id}", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> AccountCompletenessController.showJson(@PathVariable("id") Long id) {
        AccountCompleteness accountCompleteness = AccountCompleteness.findAccountCompleteness(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        if (accountCompleteness == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(accountCompleteness.toJson(), headers, HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> AccountCompletenessController.createFromJson(@RequestBody String json) {
        AccountCompleteness accountCompleteness = AccountCompleteness.fromJsonToAccountCompleteness(json);
        accountCompleteness.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> AccountCompletenessController.createFromJsonArray(@RequestBody String json) {
        for (AccountCompleteness accountCompleteness: AccountCompleteness.fromJsonArrayToAccountCompletenesses(json)) {
            accountCompleteness.persist();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> AccountCompletenessController.updateFromJson(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        AccountCompleteness accountCompleteness = AccountCompleteness.fromJsonToAccountCompleteness(json);
        if (accountCompleteness.merge() == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/jsonArray", method = RequestMethod.PUT, headers = "Accept=application/json")
    public ResponseEntity<String> AccountCompletenessController.updateFromJsonArray(@RequestBody String json) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        for (AccountCompleteness accountCompleteness: AccountCompleteness.fromJsonArrayToAccountCompletenesses(json)) {
            if (accountCompleteness.merge() == null) {
                return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> AccountCompletenessController.deleteFromJson(@PathVariable("id") Long id) {
        AccountCompleteness accountCompleteness = AccountCompleteness.findAccountCompleteness(id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        if (accountCompleteness == null) {
            return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        accountCompleteness.remove();
        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
    
    @RequestMapping(params = "find=ByAccount", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> AccountCompletenessController.jsonFindAccountCompletenessesByAccount(@RequestParam("account") Account account) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<String>(AccountCompleteness.toJsonArray(AccountCompleteness.findAccountCompletenessesByAccount(account).getResultList()), headers, HttpStatus.OK);
    }
    
}
