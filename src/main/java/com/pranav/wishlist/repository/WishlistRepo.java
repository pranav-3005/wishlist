package com.pranav.wishlist.repository;

import com.pranav.wishlist.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishlistRepo extends JpaRepository<Wishlist,Integer> {

    List<Wishlist> findByUserInformationId(int id);

    Wishlist findByProductName(String name);
}
