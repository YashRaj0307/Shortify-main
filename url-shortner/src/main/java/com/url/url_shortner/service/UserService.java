package com.url.url_shortner.service;

import com.url.url_shortner.dtos.LoginRequest;
import com.url.url_shortner.jwt.JwtAuthenticationResponse;
import com.url.url_shortner.jwt.JwtUtils;
import com.url.url_shortner.models.User;
import com.url.url_shortner.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public void registerUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public String authenticateUser(LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt= jwtUtils.generateToken(userDetails);
        return new JwtAuthenticationResponse(jwt).getToken();
    }

    public User findByUsername(String name) {
        return userRepository.findByUsername(name).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username: " + name)
        );
    }
}
