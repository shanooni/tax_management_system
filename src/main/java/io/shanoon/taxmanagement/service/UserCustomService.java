package io.shanoon.taxmanagement.service;

import io.shanoon.taxmanagement.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCustomService implements UserDetailsService {
    private final UserRepository userRepository;


    public UserCustomService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        io.shanoon.taxmanagement.model.User user =  userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid user "));

        return new User(user.getEmail(), user.getPassword(), List.of());
    }
}
