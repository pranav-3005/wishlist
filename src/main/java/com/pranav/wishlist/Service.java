package com.pranav.wishlist;

import com.pranav.wishlist.DTO.requestDTO.UserRequest;
import com.pranav.wishlist.DTO.requestDTO.WishlistRequest;
import com.pranav.wishlist.DTO.responseDTO.WishlistResponse;
import com.pranav.wishlist.configFiles.CustomUserDetailsService;
import com.pranav.wishlist.model.UserInformation;
import com.pranav.wishlist.model.Wishlist;
import com.pranav.wishlist.model.config.User;
import com.pranav.wishlist.repository.UserInformationRepo;
import com.pranav.wishlist.repository.WishlistRepo;
import com.pranav.wishlist.repository.config.UserRepo;
import com.pranav.wishlist.transformer.WishlistTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    //Provides business logics for APIs

    @Autowired
    UserRepo userRepo;

    @Autowired
    WishlistRepo wishlistRepo;

    @Autowired
    UserInformationRepo userInformationRepo;

    //to store current login-user details
    private static UserInformation currentUserInformation;

    public UserInformation getCurrentUserInformation() {
        return currentUserInformation;
    }

    public void setCurrentUserInformation(UserInformation currentUserInformation) {
        Service.currentUserInformation = currentUserInformation;
    }

    //API's business logics

    public ResponseEntity addUser(String userName, String password) {

        //1. Exceptional handling
        Optional<User> user1= Optional.ofNullable(userRepo.findByUserName(userName));
        if(user1.isPresent())
            return new ResponseEntity<>("This username is taken,try different username",HttpStatus.NOT_ACCEPTABLE);


        //2. Business logic
        //-- Encode password, set role as customer, save in user and userInfo table
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

        User user= User.builder()
                // id-> auto generated
                .userName(userName)
                .password(passwordEncoder.encode(password))
                .role("ROLE_CUSTOMER")
                .build();

        User savedUser=userRepo.save(user);

        UserInformation userInformation= UserInformation.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .build();

        userInformationRepo.save(userInformation);

        return new ResponseEntity<>("User account created successfully,\n Now login to access the application", HttpStatus.CREATED);

    }

    public ResponseEntity addWishlistItem(WishlistRequest wishlistRequest) {

        //1. get current login-user
        currentUserInformation=userInformationRepo.getById(CustomUserDetailsService.getCurrentUser().getId())  ;

        //2.Exceptional handling
        for(Wishlist wishlist:currentUserInformation.getWishlists())  //duplicate entry
        {
            if(wishlist.getProductName().equals(wishlistRequest.getProductName()) && wishlist.getPrice()==wishlistRequest.getPrice())
                return new ResponseEntity<>("Item is already present in your wishlist",HttpStatus.NOT_ACCEPTABLE);
        }

        //3. business logic
        //-- DTO->model class, store the model in table
        Wishlist wishlist= WishlistTransformer.wishlistRequestToWishlist(wishlistRequest);
        wishlist.setUserInformation(currentUserInformation);

        currentUserInformation.getWishlists().add(wishlist);

        userInformationRepo.save(currentUserInformation);
        wishlistRepo.save(wishlist);

        return new ResponseEntity<>("Item added successfully",HttpStatus.CREATED);
    }

    public ResponseEntity getWishList() {

        //1. get current login-user
        currentUserInformation=userInformationRepo.getById(CustomUserDetailsService.getCurrentUser().getId())  ;

        //2. business logic
        //--look for current user's wishlists and return the list
        List<WishlistResponse> wishlistResponseList=new ArrayList<>();

        for(Wishlist wishlist:wishlistRepo.findByUserInformationId(currentUserInformation.getId()))
        {
            wishlistResponseList.add(WishlistTransformer.wishlistTowishlistResponse(wishlist));
        }

        return new ResponseEntity<>(wishlistResponseList,HttpStatus.FOUND);
    }

    public ResponseEntity deleteWishlistItem(int wishlistId) {

        //1. get current login-user
        currentUserInformation=userInformationRepo.getById(CustomUserDetailsService.getCurrentUser().getId())  ;

        //2.Exceptional handling
        if(!currentUserInformation.getWishlists().contains(wishlistRepo.getById(wishlistId)))
            return new ResponseEntity<>("Item not found in your wishlist",HttpStatus.NOT_FOUND);

        //3. business logic
        //-- delete from wishlist table and update the foreign references in userInformation table
        currentUserInformation.getWishlists().remove(wishlistRepo.getById(wishlistId));

        userInformationRepo.save(currentUserInformation);

        wishlistRepo.deleteById(wishlistId);

        return new ResponseEntity<>("Item deleted successfully",HttpStatus.ACCEPTED);
    }
}
