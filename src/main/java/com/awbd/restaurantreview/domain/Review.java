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

    @Column(length = 150, nullable = false)
    private String text;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Column(nullable = false)
    private Integer batroomQuality;

    @Column(nullable = false)
    private Integer staff;

    @Column(nullable = false)
    private Integer cleanliness;

    @Column(nullable = false)
    private Integer driveThru;

    @Column(nullable = false)
    private Integer deliverySpeed;
}
