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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.livestormer.accounts.bookmarks.Bookmark;
import com.livestormer.accounts.bookmarks.BookmarkType;
import com.livestormer.accounts.completeness.AccountCompleteness;
import com.livestormer.accounts.completeness.tasks.completeness.TaskCompleteness;

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
    
    @RequestMapping(value="/{accountId}/bookmark", headers = "Accept=application/json")
    @ResponseBody
    public ResponseEntity<String>getBookmarks(@PathVariable("accountId") Long accountId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        List<Bookmark> result = Bookmark.findAllBookmarksForAccount(accountId);
        return new ResponseEntity<String>(Bookmark.toJsonArray(result), headers, HttpStatus.OK);
    }
    
    @RequestMapping(value="/{accountId}/bookmark", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> addBookmark(@PathVariable("accountId") Long accountId, @RequestBody String json) {
        Bookmark bookmark = Bookmark.fromJsonToBookmark(json);
        
        bookmark.persist();
        
        Account account = Account.findAccount(accountId);
	    if (account != null) {
	    	AccountCompleteness ac = AccountCompleteness.findAccountCompletenessesByAccount(account).getSingleResult();
	    	
	    	ac.updateCompleteness("STANDARD", "BOOKMRK");
	    }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "/{accountId}/bookmark", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public ResponseEntity<String> removeBookmark(@PathVariable("accountId") Long accountId, 
    				@RequestParam("objectId") Long objectId, 
    				@RequestParam("objectType") BookmarkType objectType) {
        List<Bookmark> bookmarks = Bookmark.findAllBookmarksForAccount(accountId);
        
        if (!bookmarks.isEmpty()) {
	        for (Bookmark bookmark : bookmarks) {
	        	if (bookmark.getObjectId().equals(objectId) && bookmark.getObjectType().equals(objectType)) {
	        		bookmark.remove();
	        	}
	        }
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        return new ResponseEntity<String>(headers, HttpStatus.OK);
    }
}
