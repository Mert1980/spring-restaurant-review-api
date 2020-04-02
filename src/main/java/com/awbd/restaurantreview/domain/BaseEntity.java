package com.awbd.restaurantreview.domain;

import java.util.UUID;
import javax.persistence.*;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @Id
    private UUID id;
}
