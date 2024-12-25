package com.example.project3.Controller;

import com.example.project3.DTOin.Accountin;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;
    @PostMapping("/createAccount")
    public ResponseEntity createAccount(@AuthenticationPrincipal User user , Accountin accountin){
        accountService.createAccount(user.getId(),accountin);
        return ResponseEntity.ok().body("Account created successfully");
    }
    @GetMapping("/myAccounts")
    public ResponseEntity getMyAllAccounts(@AuthenticationPrincipal User user){
        return ResponseEntity.ok().body(accountService.getMyAllAccounts(user.getId()));
    }

    @PutMapping("/deposit/{amount}")
    public ResponseEntity deposit(@AuthenticationPrincipal User user , @PathVariable Double amount){
        accountService.deposit(user.getId(), amount);
        return ResponseEntity.ok().body("Deposited successfully");
    }

    @PutMapping("/withdraw/{amount}")
    public ResponseEntity withdraw(@AuthenticationPrincipal User user , @PathVariable Double amount){
        accountService.withdraw(user.getId(), amount);
        return ResponseEntity.ok().body("Withdrawn successfully");

    }

    @PutMapping("/transfer/{amount}")
    public ResponseEntity transferMoney(@AuthenticationPrincipal User user1 , @AuthenticationPrincipal User user2 , @PathVariable Double amount){
        accountService.transferMoney(user1.getId(), user2.getId(), amount);
        return ResponseEntity.ok().body("Transfered successfully");
    }




}
