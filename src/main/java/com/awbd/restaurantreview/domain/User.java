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
	private String email;

	private String passwordHash;

	private String firstName;

	private String lastName;

	@Lob
	private Byte[] profilePicture;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<RefreshToken> refreshTokens;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Review> reviews;
}
