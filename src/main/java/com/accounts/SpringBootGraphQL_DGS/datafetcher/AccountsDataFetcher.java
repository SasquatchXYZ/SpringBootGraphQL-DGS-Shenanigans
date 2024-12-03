package com.accounts.SpringBootGraphQL_DGS.datafetcher;

import com.accounts.SpringBootGraphQL_DGS.dataloader.ClientDataLoader;
import com.accounts.SpringBootGraphQL_DGS.domain.Account;
import com.accounts.SpringBootGraphQL_DGS.domain.Client;
import com.accounts.SpringBootGraphQL_DGS.service.BankService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsQuery;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@DgsComponent
@Slf4j
public class AccountsDataFetcher {
    @Autowired
    BankService accountsService;

    @DgsQuery
    public List<Account> accounts() {
        log.info("Getting accounts");
        return accountsService.accounts();
    }

    @DgsData(parentType = "Account", field = "client")
    public CompletableFuture<Client> client(DgsDataFetchingEnvironment dfe) {
        DataLoader<Integer, Client> clientsDataLoader = dfe.getDataLoader(ClientDataLoader.class);

        // Because the client field is on Account, the getSource() method will return the Account instance.
        Account account = dfe.getSource();
        log.info("Get Clients for Account " + account.id());

        return clientsDataLoader.load(account.id());
    }
}
