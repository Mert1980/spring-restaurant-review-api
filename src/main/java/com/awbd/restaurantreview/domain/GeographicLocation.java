package com.awbd.restaurantreview.domain;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class GeographicLocation extends BaseEntity {
    private Double latitude;

    private Double longitude;

    @OneToOne
    private Address location;
}
