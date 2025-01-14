package com.accounts.SpringBootGraphQL_DGS.service;

import com.accounts.SpringBootGraphQL_DGS.domain.Account;
import com.accounts.SpringBootGraphQL_DGS.domain.Client;
import com.accounts.SpringBootGraphQL_DGS.domain.Currency;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankService {

    private static final List<Account> bankAccounts = Arrays.asList(
            new Account(100, "C100", Currency.USD, 1500.00f, "Active"),
            new Account(101, "C101", Currency.CAD, 3000.00f, "Active"),
            new Account(102, "C102", Currency.EUR, 2500.00f, "Inactive"),
            new Account(103, "C103", Currency.USD, 5000.00f, "Active"),
            new Account(104, "C104", Currency.EUR, 7500.00f, "Active")
    );

    private static final List<Client> clients = Arrays.asList(
            new Client("C100", 100, "John", "T.", "Doe"),
            new Client("C101", 101, "Emma", "B.", "Smith"),
            new Client("C102", 102, "James", "R.", "Brown"),
            new Client("C103", 103, "Olivia", "s.", "Johnson"),
            new Client("C104", 104, "William", "K.", "Jones")
    );

    public List<Account> accounts() {
        return bankAccounts;
    }

    public Map<Integer, Client> getClients(List<Integer> accountIds) {
        Map<Integer, Client> accountToClients = new HashMap<>();

        for (Integer accountId : accountIds) {
            // Search for clients with the matching account Id and add them to the list
            for (Client client : clients) {
                if (client.accountId().intValue() == accountId.intValue()) {
                    accountToClients.put(accountId, client);
                }
            }
        }

        return accountToClients;
    }
}
