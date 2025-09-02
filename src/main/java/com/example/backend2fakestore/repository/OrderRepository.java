package com.example.backend2fakestore.repository;

import com.example.backend2fakestore.models.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Integer>{
}
