package com.example.assignmentspringboot.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "categories")
public class Category {
    @Id
    private String id;
    private String name;
}
