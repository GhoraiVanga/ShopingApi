package me.suman.shopapi.service;



import java.util.Collection;

import me.suman.shopapi.entity.Cart;
import me.suman.shopapi.entity.ProductInOrder;
import me.suman.shopapi.entity.User;

/**
 * Created By Zhu Lin on 3/10/2018.
 */
public interface CartService {
    Cart getCart(User user);

    void mergeLocalCart(Collection<ProductInOrder> productInOrders, User user);

    void delete(String itemId, User user);

    void checkout(User user);
}
