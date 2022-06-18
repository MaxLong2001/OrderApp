package frontend;

import backend.Order;

import java.util.HashMap;

public class TestOrder extends Order {
    public TestOrder() {
        this.dishes = new HashMap<>();
        dishes.put("钵钵鸡", 3);
        dishes.put("果冻捏", 2);
        dishes.put("红烧肉", 3);
        dishes.put("红烧肉2", 3);
        dishes.put("红烧肉3", 3);
        dishes.put("红烧肉4", 3);
    }
}
