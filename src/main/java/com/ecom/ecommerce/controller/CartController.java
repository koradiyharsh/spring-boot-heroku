package com.ecom.ecommerce.controller;

import com.ecom.ecommerce.entities.CartItem;
import com.ecom.ecommerce.entities.Product;
import com.ecom.ecommerce.service.CartService;
import com.ecom.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @GetMapping("/shoppingCart/{sid}")
    public List<CartItem> shoppingCart(@PathVariable Integer sid) {
        return cartService.getProductsInCart(sid);
    }

    @PostMapping("/shoppingCart/addProduct/{sid}/{productId}")
    public CartItem addProductToCart(@PathVariable("sid") int sId, @PathVariable("productId") Integer productId) {
        Optional<Product> product = productService.findProductById(productId);
        return cartService.addProduct(sId, product.get());
    }

    @PostMapping("/shoppingCart/removeProduct/{sid}/{productId}")
    public String removeProductFromCart(@PathVariable("sid") int sId, @PathVariable("productId") Integer productId) {
        Optional<Product> product = productService.findProductById(productId);
        return String.valueOf(cartService.removeProduct(sId, product.get()));
    }

}
