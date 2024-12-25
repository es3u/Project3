package com.example.project3.Service;

import com.example.project3.ApiResponse.ApiException;
import com.example.project3.DTOin.CustomerDTOin;
import com.example.project3.DTOin.EmplyeeDTOIn;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;


    public void registerCustomer(CustomerDTOin customerDTOIn) {
        Customer customer = new Customer();
        customer.setPhone(customerDTOIn.getPhone());

        User user = new User();
        user.setUsername(customerDTOIn.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(customerDTOIn.getPassword());
        user.setPassword(hashPassword);
//        user.setPassword(customerDTOIn.getPassword());
        user.setEmail(customerDTOIn.getEmail());
        user.setName(customerDTOIn.getName());
        user.setRole("CUSTOMER");

        customer.setUser(user);
        authRepository.save(user);
        customerRepository.save(customer);


    }


    public void registerEmployee(EmplyeeDTOIn emplyeeDTOIn) {
        Employee employee = new Employee();
        employee.setPosition(emplyeeDTOIn.getPosition());
        employee.setSalary(emplyeeDTOIn.getSalary());

        User user = new User();
        user.setUsername(emplyeeDTOIn.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(emplyeeDTOIn.getPassword());
        user.setPassword(hashPassword);
//        user.setPassword(emplyeeDTOIn.getPassword());
        user.setEmail(emplyeeDTOIn.getEmail());
        user.setName(emplyeeDTOIn.getName());
        user.setRole("EMPLOYEE");

        employee.setUser(user);
        authRepository.save(user);
        employeeRepository.save(employee);
    }

    public List<User> getAllUsers(Integer id) {
        return authRepository.findAll();
    }

    public void block(Integer id){
        Account account = accountRepository.findAccountById(id);
        if (account == null) {throw new ApiException("Wrong account id");}

        account.setIsActive(false);
        accountRepository.save(account);
    }

    public void activeAccount(Integer index) {

        Account account = accountRepository.findAccountById(index);
        if (account == null) {throw new ApiException("Wrong account id");}

        account.setIsActive(true);
        accountRepository.save(account);
    }

}
