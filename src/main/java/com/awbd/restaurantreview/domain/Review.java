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

	private Integer batroomQuality;

	private Integer staff;

	private Integer cleanliness;

	private Integer driveThru;

	private Integer deliverySpeed;
}
