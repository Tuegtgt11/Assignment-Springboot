package com.example.assignmentspringboot.entity;

import com.example.assignmentspringboot.entity.base.BaseEntity;
import com.example.assignmentspringboot.entity.myenum.ProductStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {
    @Id
    private String id;
    private String name;
    private BigDecimal price;
    private String description;
    @Lob
    private String detail; // text
    private String thumbnails;
    @Enumerated(EnumType.ORDINAL)
    private ProductStatus status;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonManagedReference
    private Category category;

}
