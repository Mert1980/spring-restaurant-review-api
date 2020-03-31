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

	private String country;

	private String city;

	private String street;

	private String number;

	@ManyToOne
	private Restaurant restaurant;
}
