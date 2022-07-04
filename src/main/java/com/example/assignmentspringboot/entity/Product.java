package com.example.assignmentspringboot.entity;

import com.example.assignmentspringboot.entity.base.BaseEntity;
import com.example.assignmentspringboot.entity.enums.ProductSimpleStatus;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String description;
    private String detail; // text
    private String thumbnail; // nhiều ảnh cách nhau bởi dấu ,
    private BigDecimal price;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    Category category;

}
