package com.example.assignmentspringboot.seeder;

import com.example.assignmentspringboot.entity.Category;
import com.example.assignmentspringboot.entity.Product;
import com.example.assignmentspringboot.entity.enums.ProductSimpleStatus;
import com.example.assignmentspringboot.repository.ProductRepository;
import com.example.assignmentspringboot.util.NumberUtil;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class ProductSeeder {
    public static List<Product> products;
    public static final int NUMBER_OF_PRODUCT = 100;

    @Autowired
    ProductRepository productRepository;

    public void generate() {
        log.debug("------------Seeding product-------------");
        Faker faker = new Faker();
        products = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_PRODUCT; i++) {
            Product product = new Product();
            int randomCateIndex = NumberUtil.getRandomNumber(0, CategorySeeder.categories.size() - 1);
            Category category = CategorySeeder.categories.get(randomCateIndex);
            product.setId(UUID.randomUUID().toString());
            product.setName(faker.name().name());
            product.setDescription(faker.lorem().sentence());
            product.setDetail(faker.lorem().sentence());
            product.setPrice(new BigDecimal(NumberUtil.getRandomNumber(100, 1000) * 1000));
            product.setThumbnail(faker.avatar().image());
            product.setStatus(ProductSimpleStatus.ACTIVE);
            product.setCategory(category);
            products.add(product);
        }
        productRepository.saveAll(products);
        log.debug("--------------End of seeding product-------------");
    }
}

