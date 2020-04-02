package com.awbd.restaurantreview.domain;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Address extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    private GeographicLocation geographicLocation;

    @Column(length = 25, nullable = false)
    private String country;

    @Column(length = 25, nullable = false)
    private String city;

    @Column(length = 30, nullable = false)
    private String street;

    @Column(length = 5, nullable = false)
    private String number;

    @ManyToOne
    private Restaurant restaurant;
}
