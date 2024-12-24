package com.example.project3.Service;

import com.example.project3.ApiResponse.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;



    public void addAccount(Integer customerId, Account account) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer==null){
            throw  new ApiException("Invalid customer ID");
        }
                account.setCustomer(customer);
                accountRepository.save(account);
    }


    public void updateAccount(Integer accountId, Account ac) {
        Account account1 = accountRepository.findAccountById(accountId);
        if (account1==null){
               throw  new ApiException("Invalid account ID");
        }
               account1.setAccountNumber(ac.getAccountNumber());
               account1.setBalance(ac.getBalance());
               account1.setIsActive(ac.getIsActive());
               accountRepository.save(account1);
    }


    public void transferFunds(Integer fromAccountId, Integer toAccountId, Double amount) {
        Account fromAccount = accountRepository.findAccountById(fromAccountId);
        if (fromAccount==null){
                throw  new ApiException("Invalid from account ID");
        }
        Account toAccount = accountRepository.findAccountById(toAccountId);
        if (toAccount==null) {
            throw new ApiException("Invalid to account ID");
        }
        if (fromAccount.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }


    public void depositMoney(Integer accountId, Double amount) {
        Account account = accountRepository.findAccountById(accountId);
        if (account==null) {
                throw  new ApiException("Invalid account ID");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdrawMoney(Integer accountId, Double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ApiException("Invalid account ID"));

        if (account.getBalance() < amount) {
            throw new ApiException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }



}





