package com.Eccomerce.Project.controllers;



import com.Eccomerce.Project.model.Product;
import com.Eccomerce.Project.payloads.ProductDTO;
import com.Eccomerce.Project.payloads.ProductResponse;
import com.Eccomerce.Project.service.CategoryService;
import com.Eccomerce.Project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping("/public/product")
    private ResponseEntity<ProductResponse> getAllProducts(){

           ProductResponse productResponse =  productService.getAllProducts();

           return new ResponseEntity<>(productResponse,HttpStatus.OK);
    }
    @GetMapping("/public/product/{product_id}")
    private ResponseEntity<ProductResponse> getProductsById(@PathVariable Long product_id){

             ProductResponse productResponse = productService.getProductsById(product_id);

             return new ResponseEntity<>(productResponse ,HttpStatus.OK);
    }
    @PostMapping("/public/product/{category_id}")
    private ResponseEntity<ProductDTO> addProducts(@PathVariable Long category_id , @RequestBody Product product){

                        ProductDTO savedProduct = productService.addProducts(category_id,product);
                        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);

    }
}




