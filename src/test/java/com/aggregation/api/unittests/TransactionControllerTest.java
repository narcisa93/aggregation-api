package com.aggregation.api.unittests;

import com.aggregation.api.controller.TransactionController;
import com.aggregation.api.model.Account;
import com.aggregation.api.model.Transaction;
import com.aggregation.api.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SecurityContextHolder.class)
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Test
    public void testGetTransactions() {
        when(transactionService.findAllTransactions()).thenReturn(mockListOfTransactions());

        ResponseEntity<List<Transaction>> getTransactionsResponse = transactionController.getTransactions();
        assertEquals(200, getTransactionsResponse.getStatusCodeValue());
        assertEquals(2, getTransactionsResponse.getBody().size());
    }


    private List<Transaction> mockListOfTransactions() {
        Account account = new Account();
        account.setId("13232");
        account.setName("account-ionescu");
        account.setProduct("VISA Gold");

        Transaction transaction1 = new Transaction();
        transaction1.setId("tr1");
        transaction1.setAmount(456.9);
        transaction1.setDescription("Mc Donalds Amsterdam transaction");
        transaction1.setCurrency("EUR");
        transaction1.setStatus("BOOKED");
        transaction1.setAccount(account);

        Transaction transaction2 = new Transaction();
        transaction2.setId("tr2");
        transaction2.setAmount(200.9);
        transaction2.setDescription("KFC Amsterdam transaction");
        transaction2.setCurrency("EUR");
        transaction2.setStatus("BOOKED");
        transaction2.setAccount(account);
        return Arrays.asList(transaction1, transaction2);
    }
}
