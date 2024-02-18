package com.pranav.wishlist.repository;

import com.pranav.wishlist.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepo extends JpaRepository<UserInformation,Integer> {
}
