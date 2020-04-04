package com.awbd.restaurantreview.domain;

import java.util.UUID;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.*;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    @Id
    private UUID id;
}
