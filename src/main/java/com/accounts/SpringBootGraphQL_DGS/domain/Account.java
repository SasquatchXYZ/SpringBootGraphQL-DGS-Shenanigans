package com.accounts.SpringBootGraphQL_DGS.domain;

public record Account(
        Integer id,
        String clientId,
        Currency currency,
        Float balance,
        String status
) {
}
