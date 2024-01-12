package com.nurik.transactionservice.service.categoryservice;

import com.nurik.transactionservice.model.Category;
import com.nurik.transactionservice.payload.request.CategoryRequest;
import com.nurik.transactionservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category save(CategoryRequest categoryRequest) {
        Category category = Category.builder()
                .categoryName(categoryRequest.getCategoryName())
                .build();

        categoryRepository.saveAndFlush(category);

        return category;
    }
}
