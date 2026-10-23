package com.Eccomerce.Project.model;

import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)


    private Long categoryId;


    @Size(min =  5, message = "Minimum size of a message is 5")
    private String name;


    @OneToMany(mappedBy = "category")

    private List<Product> product;


}