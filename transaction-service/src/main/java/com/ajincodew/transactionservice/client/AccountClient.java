package com.ajincodew.transactionservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "account-service")
public interface AccountClient {

    @GetMapping("/api/v1/accounts/{id}")
    Map<String, Object> getAccount(@PathVariable String id);
}
