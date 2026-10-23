package com.Eccomerce.Project.JpaRepository;

import com.Eccomerce.Project.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByCategoryCategoryId(Long categoryId);
}
