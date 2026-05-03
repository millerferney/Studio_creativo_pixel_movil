package com.example.studio_creativo_pixel.models;

import java.util.ArrayList;
import java.util.List;

public class CartManager {
    private static CartManager instance;
    private List<Product> cartItems;

    private CartManager() {
        cartItems = new ArrayList<>();
    }

    public static synchronized CartManager getInstance() {
        if (instance == null) {
            instance = new CartManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        cartItems.add(product);
    }

    public void removeProduct(int position) {
        if (position >= 0 && position < cartItems.size()) {
            cartItems.remove(position);
        }
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (Product p : cartItems) {
            subtotal += p.getPrice();
        }
        return subtotal;
    }

    public void clearCart() {
        cartItems.clear();
    }
}
