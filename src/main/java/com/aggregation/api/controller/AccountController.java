package com.aggregation.api.controller;

import com.aggregation.api.authentication.CustomUserPrincipal;
import com.aggregation.api.model.Account;
import com.aggregation.api.model.User;
import com.aggregation.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAccounts() {
        User currentUser = getCurrentUser();
        if (currentUser == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<Account> accounts = accountService.findAccountsByUserId(currentUser.getId());
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal customUserPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        return customUserPrincipal != null ? customUserPrincipal.getUser() : null;
    }
}
