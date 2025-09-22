package net.abderrahimself.accounts.service.Impl;

import lombok.AllArgsConstructor;
import net.abderrahimself.accounts.dto.AccountsDto;
import net.abderrahimself.accounts.dto.CardsDto;
import net.abderrahimself.accounts.dto.CustomerDetailsDto;
import net.abderrahimself.accounts.dto.LoansDto;
import net.abderrahimself.accounts.entity.Accounts;
import net.abderrahimself.accounts.entity.Customer;
import net.abderrahimself.accounts.exception.ResourceNotFoundException;
import net.abderrahimself.accounts.mapper.AccountMapper;
import net.abderrahimself.accounts.mapper.CustomerMapper;
import net.abderrahimself.accounts.repository.AccountsRepository;
import net.abderrahimself.accounts.repository.CustomerRepository;
import net.abderrahimself.accounts.service.ICustomersService;
import net.abderrahimself.accounts.service.client.CardsFeignClient;
import net.abderrahimself.accounts.service.client.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomersServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;


    /**
     * Fetch customer details based on mobile number
     * @param mobileNumber
     * @return Customer details based on mobile number
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString()));
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(mobileNumber);
        customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(mobileNumber);
        customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());

        return customerDetailsDto;
    }
}
