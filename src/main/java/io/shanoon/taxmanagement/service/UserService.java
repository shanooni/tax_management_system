package io.shanoon.taxmanagement.service;


import io.shanoon.taxmanagement.model.User;
import io.shanoon.taxmanagement.repository.UserRepository;
import io.shanoon.taxmanagement.utility.dto.UserDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void registerUser(UserDTO user){
        User newUser = new User();
        newUser.setEmail(user.email());
        newUser.setPassword(passwordEncoder.encode(user.password()));
        userRepository.save(newUser);
    }

    public void loginUser(UserDTO user){
       User dbUser = userRepository.findByEmail(user.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                dbUser.getEmail(), dbUser.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid user"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
