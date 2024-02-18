package com.pranav.wishlist.transformer;

import com.pranav.wishlist.DTO.requestDTO.WishlistRequest;
import com.pranav.wishlist.DTO.responseDTO.WishlistResponse;
import com.pranav.wishlist.model.Wishlist;

public class WishlistTransformer {

    public static Wishlist wishlistRequestToWishlist(WishlistRequest wishlistRequest)
    {
        return Wishlist.builder()
                .productName(wishlistRequest.getProductName())
                .price(wishlistRequest.getPrice())
                .build();
    }

    public static WishlistResponse wishlistTowishlistResponse(Wishlist wishlist)
    {
        return WishlistResponse.builder()
                .productName(wishlist.getProductName())
                .price(wishlist.getPrice())
                .build();
    }
}
