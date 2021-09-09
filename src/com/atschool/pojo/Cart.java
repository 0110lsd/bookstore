package com.atschool.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    public void addItem(CartItem cartItem) {

        CartItem item = this.items.get(cartItem.getId());

        if(item == null) {
            items.put(cartItem.getId(), cartItem);
        } else {
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public void deleteItem(int id) {
        items.remove(id);
    }

    public void clear() {
        items.clear();
    }

    public void updateCount(int id, int count) {

        CartItem cartItem = items.get(id);

        if(cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));
        }
    }

    public BigDecimal getTotalPrice() {

        BigDecimal totalPrice = new BigDecimal(0);

        for(int key : items.keySet()) {

            CartItem cartItem = items.get(key);

            totalPrice = totalPrice.add(cartItem.getTotalPrice());
        }

        return totalPrice;
    }

    public int getTotalCount() {

        int totalCount = 0;

        for(int key : items.keySet()) {

            CartItem cartItem = items.get(key);

            totalCount += cartItem.getCount();
        }

        return totalCount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
