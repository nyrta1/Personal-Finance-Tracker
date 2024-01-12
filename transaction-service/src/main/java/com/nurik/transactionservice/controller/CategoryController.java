package com.nurik.transactionservice.controller;

import com.nurik.transactionservice.model.Category;
import com.nurik.transactionservice.payload.request.CategoryRequest;
import com.nurik.transactionservice.service.categoryservice.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/create")
//    @PreAuthorize("MANAGER")
    public ResponseEntity<Category> createNewCategory(@RequestBody CategoryRequest categoryRequest) {
        Category category = categoryService.save(categoryRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(category);
    }
}
