package com.example.project3.Controller;

import com.example.project3.DTOin.CustomerDTOin;
import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerService customerService;
    private final AuthService authService;

    @GetMapping("/myCustomer")
    public ResponseEntity findMyCustomer(@AuthenticationPrincipal User user){
        return ResponseEntity.ok().body(customerService.findMyCustomer(user.getId()));
    }

    @DeleteMapping("/deleteCustomer")
    public ResponseEntity deleteCustomer(@AuthenticationPrincipal User user){
        customerService.deleteCustomer(user.getId());
        return ResponseEntity.ok().body("Customer deleted successfully");
    }
    @PutMapping("/updateCustomer")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user ,@RequestBody@Valid CustomerDTOin customerDTOin){
        customerService.updateCustomer(user.getId() , customerDTOin );
        return ResponseEntity.ok().body("Customer updated successfully");
    }




}
