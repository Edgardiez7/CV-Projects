package com.uva.users.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import com.uva.users.exceptions.LoginException;
import com.uva.users.model.User;
import com.uva.users.repository.UserRepository;

import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class UserManagement {

    private final UserRepository repository;

    UserManagement(UserRepository repository) {
        this.repository = repository;
    }

    //If a user with provided email and password exists in the DB, JWT token is provided
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String loginUser(@RequestBody User json, HttpServletResponse response) {
        String token;
        try {
            if (repository.findUserByEmailAndPassword(json.getEmail(), json.getPassword()) != null) {
                token = getJWTToken(json.getEmail());
                return token;
            } else{
                // Si no existe se establece error 403
                response.setStatus(403);
                return null;
            }                
        } catch (Exception e) {
            throw new LoginException("Error in login.");
        }
    }

    //Generate and return JWT token
    private String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        return Jwts
                .builder()
                .setId("JWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                //TODO expiration date = 1 min CHANGE AFTER DEVELOPMENT!
                .setExpiration(new Date(System.currentTimeMillis() + 60000)) 
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes())
                .compact();
    }
}
