package com.aggregation.api.unittests;

import com.aggregation.api.authentication.CustomUserPrincipal;
import com.aggregation.api.controller.AccountController;
import com.aggregation.api.model.Account;
import com.aggregation.api.model.User;
import com.aggregation.api.service.AccountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
public class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;


    @Before
    public void setUp() {
        mockStatic(SecurityContextHolder.class);

        when(SecurityContextHolder.getContext()).thenReturn(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    public void testGetAccounts() {
        when(authentication.getPrincipal()).thenReturn(mockCustomerUserPrincipal());
        when(accountService.findAccountsByUserId(1L)).thenReturn(mockListOfAccounts());

        ResponseEntity<List<Account>> getAccountsResponse = accountController.getAccounts();
        assertEquals(200, getAccountsResponse.getStatusCodeValue());
        assertEquals(2, getAccountsResponse.getBody().size());
    }

    @Test
    public void testGetAccountsNoLoggedInUser() {
        when(authentication.getPrincipal()).thenReturn(null);

        ResponseEntity<List<Account>> getAccountsResponse = accountController.getAccounts();
        assertEquals(401, getAccountsResponse.getStatusCodeValue());
    }

    private List<Account> mockListOfAccounts() {
        return Arrays.asList(new Account("id1", "Gold account", 23.4),
                new Account("id2", "Silver account", 25.4));
    }

    private CustomUserPrincipal mockCustomerUserPrincipal() {
        User user = new User();
        user.setId(1L);
        user.setUsername("test");
        return new CustomUserPrincipal(user);
    }

}
