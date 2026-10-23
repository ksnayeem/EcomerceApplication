package com.Eccomerce.Project.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;

    private String productName;
    private String productDescription;

    private double productDiscount;

    private String productImage;

    private Double productPrice;

    private int productQuantity;

    private Double productSpecialPrice;

}
