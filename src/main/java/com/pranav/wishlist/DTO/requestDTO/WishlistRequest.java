package com.pranav.wishlist.DTO.requestDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class WishlistRequest {

    String productName;

    Double price;
}
