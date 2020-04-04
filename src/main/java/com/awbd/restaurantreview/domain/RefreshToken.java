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

    @Column(length = 38, nullable = false)
    private String token;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date revokedAt;

    public RefreshToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.createdAt = new Date();
    }
}
