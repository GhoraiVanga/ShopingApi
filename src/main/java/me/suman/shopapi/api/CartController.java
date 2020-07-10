package me.suman.shopapi.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import lombok.Setter;
import lombok.var;
import me.suman.shopapi.entity.Cart;
import me.suman.shopapi.entity.ProductInOrder;
import me.suman.shopapi.entity.User;
import me.suman.shopapi.form.ItemForm;
import me.suman.shopapi.repository.ProductInOrderRepository;
import me.suman.shopapi.service.CartService;
import me.suman.shopapi.service.ProductInOrderService;
import me.suman.shopapi.service.ProductService;
import me.suman.shopapi.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created By Zhu Lin on 3/11/2018.
 */
@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductInOrderService productInOrderService;
    @Autowired
    ProductInOrderRepository productInOrderRepository;

    @PostMapping("")
    public ResponseEntity<Cart> mergeCart(@RequestBody Collection<ProductInOrder> productInOrders, Principal principal) {
        User user = userService.findOne(principal.getName());
        System.out.println("user "+ user);
        try {
            cartService.mergeLocalCart(productInOrders, user);
        } catch (Exception e) {
            ResponseEntity.badRequest().body("Merge Cart Failed");
        }
        return ResponseEntity.ok(cartService.getCart(user));
    }

    @GetMapping("")
    public Cart getCart(Principal principal) {
        User user = userService.findOne(principal.getName());
        return cartService.getCart(user);
    }


    @PostMapping("/add")
    public boolean addToCart(@RequestBody ItemForm form, Principal principal) {
        var productInfo = productService.findOne(form.getProductId());
        System.out.println("productInfo" +productInfo );
        try {
            mergeCart(Collections.singleton(new ProductInOrder(productInfo, form.getQuantity())), principal);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PutMapping("/{itemId}")
    public ProductInOrder modifyItem(@PathVariable("itemId") String itemId, @RequestBody Integer quantity, Principal principal) {
        User user = userService.findOne(principal.getName());
         productInOrderService.update(itemId, quantity, user);
        return productInOrderService.findOne(itemId, user);
    }

    @DeleteMapping("/{itemId}")
    public void deleteItem(@PathVariable("itemId") String itemId, Principal principal) {
        User user = userService.findOne(principal.getName());
         cartService.delete(itemId, user);
         // flush memory into DB
    }


    @PostMapping("/checkout")
    public ResponseEntity checkout(Principal principal)
    {
    	
        User user = userService.findOne(principal.getName());// Email as username
        System.out.println("user "+ user);
        Cart cart= this.getCart(principal);
      
        
        Map<String, Object> model = new HashMap<>();
         Set<ProductInOrder>   pio   =cart.getProducts();
      //  model.put("Orders", cart.);
        System.out.println("oiiiii"  );
     Object value= cart.getProducts();
    String val=  value.toString();
   //  System.out.println(val.substring(42, 60));
        cartService.checkout(user);
        
        return ResponseEntity.ok(null);
    }


}
