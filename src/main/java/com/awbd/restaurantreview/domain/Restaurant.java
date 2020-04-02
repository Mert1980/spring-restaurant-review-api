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
public class Restaurant extends BaseEntity {
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<Address> addresses;

	@ManyToMany
	@JoinTable(name = "restaurant_category", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;

	private String name;

	@Lob
	private Byte[] logo;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<Review> reviews;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
	private Set<Ratings> rating;
}
