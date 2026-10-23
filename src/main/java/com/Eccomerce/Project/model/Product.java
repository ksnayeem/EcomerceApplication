package com.Eccomerce.Project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private Long productId;

       private String productName;
       private String productDescription;

       private double productDiscount;

       private String productImage;

       private Double productPrice;

       private int productQuantity;

       private Double productSpecialPrice;

       @ManyToOne
       @JoinColumn(name = "category_id")
       private Category category;


}
