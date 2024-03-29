package com.pranav.wishlist.securityConfigFiles;

import com.pranav.wishlist.model.config.User;
import com.pranav.wishlist.repository.config.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    private static User currentUser;

    //To get current login-user's details
    public static User getCurrentUser() {
        return currentUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user ==null)
        {
            throw new UsernameNotFoundException("Invalid username !!!");
        }

        currentUser=user;
        return new UserDetailsCreator(user);
    }
}
