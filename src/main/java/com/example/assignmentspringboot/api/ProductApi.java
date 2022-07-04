package com.example.assignmentspringboot.api;

import com.example.assignmentspringboot.entity.Category;
import com.example.assignmentspringboot.entity.Product;
import com.example.assignmentspringboot.service.CategoryService;
import com.example.assignmentspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(path = "api/v1/products")
public class ProductApi {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> getList() {
        return ResponseEntity.ok(productService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<Product> delete(@PathVariable String id) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product product, @PathVariable String cateId) {
        Optional<Product> optionalProduct = productService.findById(id);
        Optional<Category> optionalCategory = categoryService.findById(cateId);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Product existProducts = optionalProduct.get();
        existProducts.setId(product.getId());
        existProducts.setName(product.getName());
        existProducts.setDescription(product.getDescription());
        existProducts.setDetail(product.getDetail());
        existProducts.setThumbnail(product.getThumbnail());
        existProducts.setCategory(optionalCategory.get());
        return ResponseEntity.ok(productService.save(existProducts));
    }
}
