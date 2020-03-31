package com.awbd.restaurantreview.domain;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Review extends BaseEntity {
	@ManyToOne
	private Restaurant restaurant;

	@ManyToOne
	private User user;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "review")
	private Set<Attachment> attachments;

	private String text;

	private Date createdAt;

	private Double batroomQuality;

	private Double staff;

	private Double cleanliness;

	private Double driveThru;

	private Double deliverySpeed;
}
