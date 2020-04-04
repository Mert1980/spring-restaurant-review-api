package com.awbd.restaurantreview.domain;

import java.util.Set;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class User extends BaseEntity {
    @Column(length = 25, nullable = false, unique = true)
    private String email;

    @Column(length = 60, nullable = false)
    private String passwordHash;

    @Column(length = 25, nullable = false)
    private String firstName;

    @Column(length = 25, nullable = false)
    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private RoleType type;

    @Lob
    private byte[] profilePicture;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<RefreshToken> refreshTokens;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Review> reviews;
}
