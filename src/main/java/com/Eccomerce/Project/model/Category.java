package com.Eccomerce.Project.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "categories")

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)


    private Long categoryId;


    @Size(min =  5, message = "Minimum size of a message is 5")
    private String name;

    public Category() {
    }

    public Category(Long categoryId, String name) {
        this.categoryId = categoryId;
        this.name = name;
    }
    public Long getCategoryId() {

        return categoryId;
    }

    public void setCategoryId(Long Id) {

        this.categoryId = Id;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}