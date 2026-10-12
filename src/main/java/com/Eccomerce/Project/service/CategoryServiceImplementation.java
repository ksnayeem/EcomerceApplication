package com.Eccomerce.Project.service;

import com.Eccomerce.Project.JpaRepository.CategoryRepository;
import com.Eccomerce.Project.exceptionhandling.APIException;
import com.Eccomerce.Project.exceptionhandling.ResourceNotFoundException;
import com.Eccomerce.Project.model.Category;
import com.Eccomerce.Project.payloads.CategoryDTO;
import com.Eccomerce.Project.payloads.CategoryResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImplementation implements  CategoryService{




        @Autowired
        private ModelMapper modelMapper;
        @Autowired
        private CategoryRepository categoryRepository;
        @Override
        public  CategoryResponse getAllCategories(Integer pageNumber, Integer pageSize,String sortCategoriesBy,String sortOrder) {
            Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc")
                    ?Sort.by(sortCategoriesBy).ascending()
                    :Sort.by(sortCategoriesBy).descending();
            Pageable  pageDetails = PageRequest.of(pageNumber,pageSize,sortByAndOrder);
            Page<Category> categoryPage = categoryRepository.findAll(pageDetails);
            List<Category> findCategories =  categoryPage.getContent();

            if (findCategories.isEmpty()) {
                throw new APIException("Category not created till now");
            }

            List<CategoryDTO> categoryDTOS = findCategories.stream()
                    .map(category -> modelMapper.map(category, CategoryDTO.class))
                    .toList();

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setContent(categoryDTOS);
            categoryResponse.setPageNumber(categoryPage.getNumber());
            categoryResponse.setPageSize(categoryPage.getSize());
            categoryResponse.setTotalElements(categoryPage.getTotalElements());
            categoryResponse.setTotoalPages(categoryPage.getTotalPages());
            categoryResponse.setLastPage(categoryPage.isLast());
            return categoryResponse;
        }

        @Override
        public CategoryDTO  addCategories(CategoryDTO categoryDTO ){
            Category category = modelMapper.map(categoryDTO,Category.class);
            Category categoryFromDB = categoryRepository.findByName(category.getName());

            if(categoryFromDB!=null){

                 throw new APIException("Category Name with :" + category.getName() + " is already exists");
            }

            Category savedCategory =   categoryRepository.save(category);


            return modelMapper.map(savedCategory,CategoryDTO.class);

        }
    @Override
    public CategoryDTO deleteCategory(Long categoryId) {


           Category category = categoryRepository.findById(categoryId)
                   .orElseThrow(() ->new ResourceNotFoundException("Category","categoryId",categoryId));




          categoryRepository.delete(category);
           return  modelMapper.map(category,CategoryDTO.class);
    }

    @Override
    public CategoryDTO updateCategories(CategoryDTO categoryDTO, Long categoryId) {

        Category existingCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Category",
                                "categoryId",
                                categoryId
                        ));



        Category category=  modelMapper.map(categoryDTO,Category.class);

        existingCategory.setName(category.getName());

        Category savedCategory = categoryRepository.save(existingCategory);

        return modelMapper.map(savedCategory,CategoryDTO.class);
    }

}
