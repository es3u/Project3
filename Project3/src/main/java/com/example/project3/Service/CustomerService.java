package com.example.project3.Service;

import com.example.project3.DTOin.CustomerDTOin;
import com.example.project3.DTOout.AccountDTOout;
import com.example.project3.DTOout.CustomerDTOout;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

private final AuthRepository authRepository;
private final CustomerRepository customerRepository;


    public CustomerDTOout findMyCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);

        if (customer == null) {
            throw new RuntimeException("Customer not found");
        }
        CustomerDTOout customerDTOout = new CustomerDTOout();
        customerDTOout.setUsername(customer.getUser().getUsername());
        customerDTOout.setName(customer.getUser().getName());
        customerDTOout.setEmail(customer.getUser().getEmail());
        customerDTOout.setPhone(customer.getPhone());
        customerDTOout.setAccounts(customer.getAccounts().stream().map(account -> new AccountDTOout(account.getAccountNumber() , account.getBalance())).collect(Collectors.toSet()));
        
        return customerDTOout;
    }


    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        User user = authRepository.findUserById(customer.getUser().getId());
        if (user == null || customer == null){
            throw new RuntimeException("Customer not found");
        }
        authRepository.delete(user);
        customerRepository.delete(customer);
    }
    
    public void updateCustomer(Integer id ,CustomerDTOin customerDTOin) {
        Customer customer = customerRepository.findCustomerById(id);
        customer.setPhone(customerDTOin.getPhone());
        
        User user = new User();
        user.setUsername(customerDTOin.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTOin.getPassword());
        user.setPassword(hashPassword);
        user.setEmail(customerDTOin.getEmail());
        user.setName(customerDTOin.getName());
        
        customer.setUser(user);
        authRepository.save(user);
        customerRepository.save(customer);
    }




}
