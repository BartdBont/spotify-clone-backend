package com.bartdebont.spotifyclone.service;

import com.bartdebont.spotifyclone.exception.EmailExistsException;
import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.model.request.RegisterRequest;
import com.bartdebont.spotifyclone.repository.CustomerRepository;
import com.bartdebont.spotifyclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, JwtUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.jwtUtil = jwtUtil;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Customer registerNewCustomer(RegisterRequest registerRequest) throws Exception {
        if (!isValidEmail(registerRequest.getEmail())){
            throw new Exception("Email is not valid");
        }
        if (emailExists(registerRequest.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address:" + registerRequest.getEmail()
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
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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

    public Customer getUserFromHttp(HttpServletRequest req) {
        String token = req.getHeader("Authorization");
        token = token.substring(7);
        System.out.println(token);
        String username = jwtUtil.extractUsername(token);
        Customer user = loadUserByUsername(username);
        return user;
    }
}
