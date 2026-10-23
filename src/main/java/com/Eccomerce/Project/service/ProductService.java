package com.Eccomerce.Project.service;

import com.Eccomerce.Project.model.Product;
import com.Eccomerce.Project.payloads.ProductDTO;
import com.Eccomerce.Project.payloads.ProductResponse;

public interface ProductService {

       ProductResponse getAllProducts();
       ProductDTO addProducts(Long category_id, Product product);

       ProductResponse getProductsById(Long productId);
}
