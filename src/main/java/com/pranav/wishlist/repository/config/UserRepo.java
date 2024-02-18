package com.pranav.wishlist.repository.config;

import com.pranav.wishlist.model.config.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
