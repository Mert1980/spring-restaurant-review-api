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
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @ManyToMany
    @JoinTable(name = "restaurant_category", joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    @Column(length = 25, nullable = false, unique = true)
    private String name;

    @Lob
    private byte[] logo;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "restaurant")
    private Set<Ratings> ratings;
}
