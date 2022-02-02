package com.ecom.ecommerce.service;

import com.ecom.ecommerce.dao.CartDao;
import com.ecom.ecommerce.entities.CartItem;
import com.ecom.ecommerce.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CartService {

    static Map<Product, Integer> products = new HashMap<>();
    @Autowired
    CartDao cartDao;

    public List<CartItem> getProductsInCart(Integer sid)
    {
        return cartDao.findCartItem(sid);
    }
    public CartItem addProduct(Integer sId, Product product) {
        CartItem cartItem = new CartItem();
        CartItem existCartItemQty = cartDao.findIdByProduct(product.getId());
        if (existCartItemQty==null) {
            cartItem.setSessionid(sId);
            cartItem.setProduct(product);
            cartItem.setQty(1);
        } else {
            cartItem.setCartid(existCartItemQty.getCartid());
            cartItem.setSessionid(existCartItemQty.getSessionid());
            cartItem.setProduct(existCartItemQty.getProduct());
            cartItem.setQty(existCartItemQty.getQty() + 1);
        }
        return cartDao.save(cartItem);
    }

    public CartItem removeProduct(Integer sId, Product product) {
        CartItem cartItem = new CartItem();
        CartItem existCartItemQty = cartDao.findIdByProductAndSId(product.getId(),sId);
        if (existCartItemQty.getQty()>1) {
            cartItem.setSessionid(existCartItemQty.getSessionid());
            cartItem.setProduct(existCartItemQty.getProduct());
            cartItem.setCartid(existCartItemQty.getCartid());
            cartItem.setQty(existCartItemQty.getQty()-1);
             return cartDao.save(cartItem);
        } else {
            cartDao.delete(existCartItemQty);
            return existCartItemQty;
        }

    }
//    public Map<Product,Integer> addProduct(Product product)
//    {
//        if (products.containsKey(product)) {
//            products.replace(product, products.get(product) + 1);
//        } else {
//            products.put(product, 1);
//        }
//        return this.getProductsInCart();
//    }
//    public Map<Product,Integer> removeProduct(Product product) {
//        if (products.containsKey(product)) {
//            if (products.get(product) > 1)
//                products.replace(product, products.get(product) - 1);
//            else if (products.get(product) == 1) {
//                products.remove(product);
//            }
//        }
//         return this.getProductsInCart();
//    }
//    public Map<Product, Integer> getProductsInCart() {
//        return products;
//    }
//    public BigDecimal getTotal() {
//        return products.entrySet().stream()
//                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
//                .reduce(BigDecimal::add)
//                .orElse(BigDecimal.ZERO);
//    }
}
