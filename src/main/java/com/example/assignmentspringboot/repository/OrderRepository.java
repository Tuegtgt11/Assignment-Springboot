package com.example.assignmentspringboot.repository;

import com.example.assignmentspringboot.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,String> {
    @Query(nativeQuery = true, value = "select  * from orders where user_id = :userId and is_shopping_cart = 1")
    Order getShoppingCart(@Param("userId") int userId);
}
