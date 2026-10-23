package com.Eccomerce.Project.service;


import com.Eccomerce.Project.JpaRepository.CategoryRepository;
import com.Eccomerce.Project.JpaRepository.ProductRepository;
import com.Eccomerce.Project.exceptionhandling.ResourceNotFoundException;
import com.Eccomerce.Project.model.Category;
import com.Eccomerce.Project.model.Product;
import com.Eccomerce.Project.payloads.ProductDTO;
import com.Eccomerce.Project.payloads.ProductResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImplementation implements  ProductService{


     @Autowired
     private ModelMapper modelMapper;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ProductDTO  addProducts(Long category_id, Product product) {


        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "category_id", category_id));

        product.setProductImage("image.png1");
        product.setCategory(category);

        double productSpecialPrice = product.getProductPrice() - ((product.getProductDiscount() * 0.01) * product.getProductPrice());

        product.setProductSpecialPrice(productSpecialPrice);

        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }
     @Override
     public ProductResponse getAllProducts(){

           List<Product> products =  productRepository.findAll();

           List<ProductDTO> productDTOS = products.stream().map(product -> modelMapper.map(product,ProductDTO.class))
                   .collect(Collectors.toList());


           ProductResponse productResponse  = new ProductResponse();

           productResponse.setContent(productDTOS);

           return productResponse;


    }
    @Override
    public ProductResponse getProductsById(Long category_id){

         Category category  = categoryRepository.findById(category_id)
                .orElseThrow(() -> new ResourceNotFoundException("Category","category_id",category_id));

        List<Product> products =  productRepository.findByCategoryCategoryId(category_id);

        List<ProductDTO> productDTOS = products.stream().map(product ->  modelMapper.map(product,ProductDTO.class)).collect(Collectors.toList());

        ProductResponse productResponse  = new ProductResponse();

        productResponse.setContent(productDTOS);

        return productResponse;

    }
}
