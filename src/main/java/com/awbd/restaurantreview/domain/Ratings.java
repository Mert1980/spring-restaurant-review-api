package com.awbd.restaurantreview.domain;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(indexes = {
    @Index(name = "IDX_RESTAURANTID_TYPE", columnList = "restaurant_id, type"),
    @Index(name = "IDX_RESTAURANT_ID", columnList = "restaurant_id")
})
public class Ratings extends BaseEntity {
    @Enumerated(value = EnumType.STRING)
    private RatingType type;

    private int oneStarCount;

    private int twoStarCount;

    private int threeStarCount;

    private int fourStarCount;

    private int fiveStarCount;

    @ManyToOne
    private Restaurant restaurant;
}
