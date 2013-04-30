package com.livestormer.accounts.completeness;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livestormer.accounts.Account;

@RequestMapping("/accountcompleteness")
@Controller
@RooWebJson(jsonObject = AccountCompleteness.class)
public class AccountCompletenessController {
    @RequestMapping(headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String> listJson(@PathVariable("accountId") Long accountId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        
        Account account = Account.findAccount(accountId);
        AccountCompleteness ac = AccountCompleteness.getAccountCopmpleteness(account);
        
        if (ac == null) {
        	return new ResponseEntity<String>(headers, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<String>(ac.toJson(), headers, HttpStatus.OK);
    }
}
