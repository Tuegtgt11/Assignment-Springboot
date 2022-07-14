package com.example.assignmentspringboot.repository;

import com.example.assignmentspringboot.entity.Product;
import com.example.assignmentspringboot.entity.myenum.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Page<Product> findAllByStatusEquals(ProductStatus status, Pageable pageable);

}
