package com.example.assignmentspringboot.api;

import com.example.assignmentspringboot.entity.Category;
import com.example.assignmentspringboot.entity.Product;
import com.example.assignmentspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping(path = "api/v1/categories")
@RestController
@CrossOrigin("*")
public class CategoryApi {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public
    Iterable<Category> findAll(){
        return categoryService.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, path = {"id"})
    public ResponseEntity<?> findById(@PathVariable String id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()){
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> save(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.save(category));
    }
    @RequestMapping(method = RequestMethod.DELETE, path = {"id"})
    public ResponseEntity<?> deleteById(@PathVariable String id){
        if (!categoryService.findById(id).isPresent()){
            ResponseEntity.badRequest().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @RequestMapping(method = RequestMethod.PUT, path = {"id"})
    public ResponseEntity<Category> update(@PathVariable String id, @RequestBody Category updateCategory) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Category existCategory = optionalCategory.get();

        existCategory.setName(updateCategory.getName());

        return ResponseEntity.ok(categoryService.save(existCategory));
    }
}
