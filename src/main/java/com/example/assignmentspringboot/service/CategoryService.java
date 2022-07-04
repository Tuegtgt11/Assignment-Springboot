package com.example.assignmentspringboot.service;

import com.example.assignmentspringboot.entity.Category;
import com.example.assignmentspringboot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteById(String id) {
        categoryRepository.deleteById(id);
    }
}
