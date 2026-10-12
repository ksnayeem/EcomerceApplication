package com.Eccomerce.Project.JpaRepository;

import com.Eccomerce.Project.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {


    Category findByName(String name);

    List<Category> getAllByName(String name);
}
