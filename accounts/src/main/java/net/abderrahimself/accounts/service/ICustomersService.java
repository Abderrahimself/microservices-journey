package net.abderrahimself.accounts.service;

import net.abderrahimself.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {
    /**
     * Fetch customer details
     * @param mobileNumber
     * @return Customer details based on mobile number
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
