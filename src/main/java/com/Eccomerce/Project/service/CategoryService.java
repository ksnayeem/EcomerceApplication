package com.Eccomerce.Project.service;

import com.Eccomerce.Project.model.Category;
import com.Eccomerce.Project.payloads.CategoryDTO;
import com.Eccomerce.Project.payloads.CategoryResponse;

import java.util.*;

public interface CategoryService {

       CategoryResponse getAllCategories(Integer pageNumber,Integer pageSize,String sortCategoriesBy,String sortOrder);

       CategoryDTO addCategories(CategoryDTO categoryDTO);

       CategoryDTO deleteCategory(Long categoryId);

       CategoryDTO updateCategories(CategoryDTO categoryDTO, Long categoryId);
}
