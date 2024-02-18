package com.pranav.wishlist;

import com.pranav.wishlist.DTO.requestDTO.UserRequest;
import com.pranav.wishlist.DTO.requestDTO.WishlistRequest;
//import io.swagger.v3.oas.annotations.parameters.RequestBody; //returning null values
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    //contains list of all APIs

    @Autowired
    Service service;

    @PostMapping("/signup")
    public ResponseEntity addUser(@RequestParam String userName,@RequestParam String password)
    {
        return service.addUser(userName,password);
    }

    @PostMapping("/wishlists")
    public ResponseEntity addWishlistItem(@RequestBody WishlistRequest wishlistRequest)
    {
        return service.addWishlistItem(wishlistRequest);
    }

    @GetMapping("/wishlists")
    public ResponseEntity getWishList()
    {
        return service.getWishList();
    }

    @DeleteMapping("/deleteWishlist/{Id}")
    public ResponseEntity deleteWishlistItem(@PathVariable("Id") int wishlistId)
    {
        return service.deleteWishlistItem(wishlistId);
    }
}
