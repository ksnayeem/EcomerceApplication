

package com.Eccomerce.Project.category;
import java.util.*;

import com.Eccomerce.Project.config.AppConstant;
import com.Eccomerce.Project.model.Category;
import com.Eccomerce.Project.payloads.CategoryDTO;
import com.Eccomerce.Project.payloads.CategoryResponse;
import com.Eccomerce.Project.service.CategoryService;
import com.Eccomerce.Project.service.CategoryServiceImplementation;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CategoryController {


    private final ModelMapper modelMapper;
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }


       @GetMapping("/public/categories")
//     @RequestMapping(value = "/api/public/categories",method = RequestMethod.GET)
        public ResponseEntity<CategoryResponse> getAllCategories(@RequestParam(name = "pageNumber",defaultValue = AppConstant.PAGE_NUMBER,required = false) Integer pageNumber,
                                                                 @RequestParam(name = "pageSize",defaultValue =  AppConstant.PAGE_SIZE,required = false) Integer pageSize,
                                                                 @RequestParam(name = "sortBy",defaultValue =  AppConstant.SORT_CATEGORIES_BY,required = false) String sortCategoriesBy,
                                                                 @RequestParam(name = "sortBy",defaultValue =   AppConstant.SORT_ORDER,required = false)String sortOrder){


              CategoryResponse categoryResponse = (CategoryResponse) categoryService.getAllCategories(pageNumber,pageSize,sortCategoriesBy,sortOrder);

             return new ResponseEntity<>(categoryResponse,HttpStatus.OK);
        }

        @PostMapping("/public/categories")
//        @RequestMapping(value = "/api/public/categories",method = RequestMethod.POST)
        public ResponseEntity<CategoryDTO>  addCategories(@Valid  @RequestBody CategoryDTO categoryDTO ){


                CategoryDTO  savedCategoryDTO = categoryService.addCategories(categoryDTO);

                return  new ResponseEntity<>(savedCategoryDTO,HttpStatus.CREATED);
        }
        @DeleteMapping("/public/categories/{categoryId}")

//        @RequestMapping(value = "/api/public/categories/{categoryId}",method = RequestMethod.DELETE)
        public ResponseEntity<CategoryDTO>  deleteCategories(@PathVariable Long categoryId){




                     CategoryDTO  status = categoryService.deleteCategory(categoryId);
//                       return ResponseEntity.ok(status);
//                       return ResponseEntity.status(HttpStatus.OK).body(status);
                     return new ResponseEntity<>(status, HttpStatus.OK);

        }

        @PutMapping("/public/categories/{categoryId}")
//        @RequestMapping(value = "/api/public/categories/{categoryId}",method = RequestMethod.PUT)
        public ResponseEntity<CategoryDTO> updateCategories(@Valid @RequestBody CategoryDTO categoryDTO,@PathVariable Long categoryId){



                        CategoryDTO savedCategoryDTO = categoryService.updateCategories(categoryDTO,categoryId);

                        return new ResponseEntity<>( savedCategoryDTO,HttpStatus.OK);


        }





}
