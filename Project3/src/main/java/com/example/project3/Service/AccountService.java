package com.example.project3.Service;

import com.example.project3.ApiResponse.ApiException;
import com.example.project3.DTOin.Accountin;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final AuthRepository authRepository;
    
    public void createAccount(Integer id ,Accountin accountin){
        Customer customer = customerRepository.findCustomerById(id);
        Account account = new Account();
        if (customer == null){
            throw new ApiException("Customer not found");
        }
        account.setAccountNumber(accountin.getAccountNumber());
        account.setBalance(accountin.getBalance());
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public List<Account> getMyAllAccounts(Integer id){
        User user = authRepository.findUserById(id);
        if (user == null){
            throw new ApiException("Wrong Customer id");
        }
        List<Account> accounts = accountRepository.findAllByCustomerId(user.getId());
        return accounts;
    }


    public void deposit(Integer id , Double deposit){
        Account account = accountRepository.findAccountById(id);
        account.setBalance(account.getBalance() + deposit);
        accountRepository.save(account);
    }

    public void withdraw(Integer id , Double withdraw){
        Account account = accountRepository.findAccountById(id);
        if(account.getBalance() < withdraw){
            throw new ApiException("Not enough balance");
        }
        account.setBalance(account.getBalance() - withdraw);
        accountRepository.save(account);
    }




    public void transferMoney(Integer firstCustomer,Integer secondCustomer, Double amount){
        Account firstAccount = accountRepository.findAccountById(firstCustomer);
        Account secondAccount = accountRepository.findAccountById(secondCustomer);
        if (firstAccount == null || secondAccount == null) {throw new ApiException("Wrong account id");}
        if(firstAccount.getIsActive() == false){throw new ApiException("first account Not active enough balance");}
        if(secondAccount.getIsActive() == false){throw new ApiException("second account Not active c  enough balance");}
        if (firstAccount.getBalance() < amount) {throw new ApiException("Not enough balance");}

        User user = authRepository.findUserById(firstAccount.getCustomer().getUser().getId());
        User user2 = authRepository.findUserById(secondAccount.getCustomer().getUser().getId());
        if (user == null || user2 == null){throw new ApiException("Wrong account id");}

        firstAccount.setBalance(firstAccount.getBalance() - amount);
        secondAccount.setBalance(secondAccount.getBalance() + amount);
        accountRepository.save(firstAccount);
        accountRepository.save(secondAccount);
    }


    
    

   
    
}
