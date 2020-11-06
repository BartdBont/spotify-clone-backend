package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.EmailExistsException;
import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.model.request.RegisterRequest;
import com.bartdebont.spotifyclone.repository.CustomerRepository;
import org.apache.commons.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Customer registerNewCustomer(RegisterRequest registerRequest) throws Exception {
        if (!isValidEmail(registerRequest.getEmail())){
            throw new Exception("Email is not valid");
        }
        if (emailExists(registerRequest.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email adress:" + registerRequest.getEmail()
            );
        }
        Customer customer = new Customer(
                registerRequest.getUsername(),
                registerRequest.getEmail(),
                passwordEncoder.encode(registerRequest.getPassword()),
                null,
                new ArrayList<>());
        return customerRepository.save(customer);
    }

    private boolean isValidEmail(String email) {
//        return EmailValidator.getInstance().isValid(email);
        return true;
    }

    private boolean emailExists(String email) {
        if (customerRepository.findByEmail(email) == null) return false;
        else return true;
    }

    @Override
    public Customer loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            return getUserByName(s);
        } catch (Exception e) {
            return null;
        }
    }

    private Customer getUserByName(String s) {
        return customerRepository.findByUsername(s);
    }
}
