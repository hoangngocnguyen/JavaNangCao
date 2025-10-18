package com.hoang.shopapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "category")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Category {
    @Column(name = "category_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(name = "category_name", unique = true)
    private String categoryName;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "slug", unique = true, nullable = false)
    private String slug;
}
