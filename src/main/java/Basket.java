import Products.Item;

import java.util.HashMap;

public class Basket {
    private Customer customer;
    private HashMap<Item, Integer> items;
    private int quantity;

    public Basket(Customer customer) {
        this.customer = customer;
        this.items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            items.put(item, currentQuantity + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public double calcBasketPriceBOGOF(){
        double total = 0.0;
//        you can only use final variables in lambda functions... hence this work around
//        as a list is mutable.
        final double[] basketTotal = {total};
        items.forEach((item,quantity) -> {
            if(item.isOnOffer()){
                basketTotal[0] += ((item.getPrice() * (Math.floor(quantity/2))) + ((item.getPrice() * (quantity % 2))));
            }
            else{
                basketTotal[0] += item.getPrice() * quantity;
            }
        });
        return basketTotal[0];
    }


    public double calcBasketPrice() {
        double price = this.calcBasketPriceBOGOF();

        if (price > 20) {
            double newTotalWith20Discount = over20Discount(price);
            if (customer.hasLoyaltyCard()) {
                return loyaltyDiscount(newTotalWith20Discount);
            } else {
                return newTotalWith20Discount;
            }
        } else {
            if (customer.hasLoyaltyCard()) {
                return loyaltyDiscount(price);
            } else {
                return price;
            }
        }
    }


    public double loyaltyDiscount(double value){
        return value * 0.98;
    }

    public double over20Discount(double value){
        return value * 0.9;
    }

    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void clearItems(){
        this.items = new HashMap<>();
    }
}
