package com.accounts.SpringBootGraphQL_DGS.domain;

public record Client(String id, Integer accountId, String firstname, String middleName, String lastname) {
}
