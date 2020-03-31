package com.awbd.restaurantreview.domain;

import javax.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Attachment extends BaseEntity {
	private String fileName;

	@Enumerated(value = EnumType.STRING)
	private AttachmentType type;

	@Lob
	private Byte[] content;

	@ManyToOne
	private Review review;
}
