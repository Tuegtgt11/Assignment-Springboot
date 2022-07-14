package com.example.assignmentspringboot.service;

import com.example.assignmentspringboot.entity.Order;
import com.example.assignmentspringboot.entity.OrderDetail;
import com.example.assignmentspringboot.entity.Product;
import com.example.assignmentspringboot.entity.myenum.ProductStatus;
import com.example.assignmentspringboot.repository.OrderRepository;
import com.example.assignmentspringboot.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class OrderService{
    final OrderRepository orderRepository;
    final ProductRepository productRepository;


    public Order findShoppingCartByUserId(int userId){
       return orderRepository.getShoppingCart(userId);
    }

    public Order addShoppingCart(int userId, String productId, int quantity) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()){
            return null;
        }
        Order order = orderRepository.getShoppingCart(userId);
        Set<OrderDetail> orderDetails = order.getOrderDetails();
//        HashMap<Long, OrderDetail> map = new HashMap<Long, OrderDetail>();
//        //fill in map
//        for(OrderDetail entry : orderDetails)
//        {
//            map.put(entry.getProduct().getId(), entry);
//        }
//
//        for (int i =0 ; i < orderDetails.size() - 1 ;i++){
//            //neu chua co sp trong gio hang thi them vao
//            // con co roi thi cong don ok
//        }
        boolean exist = false;
        for(OrderDetail entry : orderDetails)
        {
            if(entry.getProduct().getId().equals(productId)){
                entry.setQuantity(entry.getQuantity() + quantity);
                exist = true;
            }
        }
        if(!exist){
            OrderDetail orderDetail = new OrderDetail();
            orderDetails.add(orderDetail);
        }
        //save
         return order;
    }
}
