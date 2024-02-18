package com.pranav.wishlist.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class UserInformation {

    @Id
    int id;

    @Column(unique = true)
    String userName;

    @OneToMany(mappedBy = "userInformation",cascade = CascadeType.ALL)
    List<Wishlist> wishlists=new ArrayList<>();
}
