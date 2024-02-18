package com.pranav.wishlist.model;

import com.pranav.wishlist.model.config.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int productId;

    String productName;

    Double price;

    @ManyToOne
    @JoinColumn
    UserInformation userInformation;
}
