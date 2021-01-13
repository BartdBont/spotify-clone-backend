package com.bartdebont.spotifyclone.controller;

import com.bartdebont.spotifyclone.model.Customer;
import com.bartdebont.spotifyclone.model.request.AuthenticationRequest;
import com.bartdebont.spotifyclone.model.request.AuthenticationResponse;
import com.bartdebont.spotifyclone.model.request.RegisterRequest;
import com.bartdebont.spotifyclone.service.CustomerService;
import com.bartdebont.spotifyclone.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/spotify/v1/user")
@CrossOrigin(origins = "*")
public class CustomerController {

    private AuthenticationManager authenticationManager;
    private CustomerService customerService;
    private JwtUtil jwtUtil;

    @Autowired
    public CustomerController(AuthenticationManager authenticationManager, CustomerService customerService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username and password combination");
        }
        final Customer customer = customerService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(customer);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(@RequestBody RegisterRequest registerRequest) throws Exception {
        Customer customer;

        customer = customerService.registerNewCustomer(registerRequest);

        return ResponseEntity.ok(customer);
    }

    @GetMapping()
    public ResponseEntity<?> getUser(HttpServletRequest req) throws Exception {
        Customer user = customerService.getUserFromHttp(req);
        return ResponseEntity.ok(user);
    }
}
