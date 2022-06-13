package net.genspark.restaurantbackend.controllers;

import net.genspark.restaurantbackend.jwt.JwtRequest;
import net.genspark.restaurantbackend.jwt.JwtResponse;
import net.genspark.restaurantbackend.jwt.JwtTokenUtil;
import net.genspark.restaurantbackend.services.AuthenticationService;
import net.genspark.restaurantbackend.services.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class AuthenticateController {

    private AuthenticationService authenticationService;
    private JwtTokenUtil jwtTokenUtil;
    private CustomUserDetailsService userDetailsService;

    public AuthenticateController(AuthenticationService authenticationService, JwtTokenUtil jwtTokenUtil,
                                  CustomUserDetailsService userDetailsService) {

        this.authenticationService = authenticationService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticationService.authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
