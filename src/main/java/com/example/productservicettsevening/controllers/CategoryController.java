package com.example.productservicettsevening.controllers;

import com.example.productservicettsevening.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private CategoryService categoryService;
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }
    @GetMapping("/products/categories")
    public String getAllCategories(){
        return "getting all categories";
    }
    @GetMapping("/products/categories/{categoryId}")
    public String getProductsInCategory(@PathVariable ("categoryId") Long categoryId){
        return "getting product in category:" +categoryId;
    }
}
