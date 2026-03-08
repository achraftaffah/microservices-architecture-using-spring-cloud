package com.ajincodew.accountservice.controller;

import com.ajincodew.accountservice.model.Account;
import com.ajincodew.accountservice.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountRepository repository;

    @GetMapping
    @PreAuthorize("hasRole(T(com.ajincodew.accountservice.UserRoleEnum).CLIENT.roleName())")
    public List<Account> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        Account saved = repository.save(account);
        return ResponseEntity.created(URI.create("/api/v1/accounts/" + saved.getId())).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account input) {
        return repository.findById(id).map(existing -> {
            existing.setOwner(input.getOwner());
            existing.setBalance(input.getBalance());
            repository.save(existing);
            return ResponseEntity.ok(existing);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return repository.findById(id).map(existing -> {
            repository.deleteById(id);
            return ResponseEntity.noContent().<Void>build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
