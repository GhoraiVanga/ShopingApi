package me.suman.shopapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import me.suman.shopapi.entity.ProductInOrder;

/**
 * Created By Zhu Lin on 3/14/2018.
 */
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

}
