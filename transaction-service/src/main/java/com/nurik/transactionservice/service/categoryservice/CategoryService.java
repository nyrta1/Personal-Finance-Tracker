package com.nurik.transactionservice.service.categoryservice;

import com.nurik.transactionservice.model.Category;
import com.nurik.transactionservice.payload.request.CategoryRequest;

public interface CategoryService {
    Category save(CategoryRequest categoryRequest);
}
