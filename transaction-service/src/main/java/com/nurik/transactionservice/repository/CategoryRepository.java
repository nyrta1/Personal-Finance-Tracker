package com.nurik.transactionservice.repository;

import com.nurik.transactionservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository
        extends JpaRepository<Category, Long> {
}
