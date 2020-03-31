package com.awbd.restaurantreview.domain;

import java.util.Date;
import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class RefreshToken extends BaseEntity {
	@ManyToOne
	private User user;

	private String token;

	private Date createdAt;

	private Date revokedAt;
}
