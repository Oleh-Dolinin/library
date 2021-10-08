package com.example.library.serviceImpl;

import com.example.library.repos.UserRepo;
import com.example.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepo.findByUsername(username) == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userRepo.findByUsername(username);
    }
}
