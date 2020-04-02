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
public class Category extends BaseEntity {
    @Column(length = 15, nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Restaurant> restaurants;
}
