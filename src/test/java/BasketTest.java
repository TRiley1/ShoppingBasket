import Products.Item;
import Products.Meat;
import Products.Milk;
import Products.Poultry;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BasketTest {
    Basket basket;
    Customer customer;
    Milk milk;
    Poultry poultry;
    Meat meat;

    @Before
    public void setUp(){
        customer = new Customer("Shrek");
        basket = new Basket(customer);
        milk = new Milk("Cravendale", 2.00);
        meat = new Meat("Beef Sirloin piece", 33.50);
        poultry = new Poultry("Chicken Thighs", 2.00);
    }

    @Test
    public void canAddItemToBasket(){
        basket.addItem(milk,1);
        assertEquals(1,basket.getItems().get(milk).intValue());
    }

    @Test
    public void canAddMoreMilk(){
        basket.addItem(milk,1);
        basket.addItem(milk,1);
        assertEquals(2,basket.getItems().get(milk).intValue());
    }

    @Test
    public void canCalcBasketPriceNoOffers(){
        basket.addItem(milk,2);
        basket.addItem(meat,1);
        double result = basket.calcBasketPriceBOGOF();
        assertEquals(37.50,result, 0);
    }

    @Test
    public void canCalcBasketPriceWithBOGOF(){
        milk.setOnOffer();
        basket.addItem(milk,3);
        basket.addItem(meat,1);
        double result = basket.calcBasketPriceBOGOF();
        assertEquals(3,basket.getItems().get(milk).intValue());
        assertEquals(37.50,result, 0);
    }

    @Test
    public void canEmptyBasket(){
        basket.addItem(milk,4);
        basket.clearItems();
        assertTrue(basket.getItems().isEmpty());
    }

    @Test
    public void canCalcBasketPrice(){
        basket.addItem(milk,1);
        basket.addItem(meat,1);
        basket.addItem(poultry,1);
        double result = basket.calcBasketPrice();
        assertEquals(33.75, result,0);
    }

    @Test
    public void canCalcBasketPriceIfOfferApplied(){
        milk.setOnOffer();
        basket.addItem(milk,2);
        basket.addItem(meat,1);
        basket.addItem(poultry,1);
        double result = basket.calcBasketPrice();
        assertEquals(33.75, result,0);
    }


    @Test
    public void canCalcNoLoyalNoOver20Discount(){
        basket.addItem(milk,1);
        double result = basket.calcBasketPrice();
        assertEquals(2,result, 0);
    }

    @Test
    public void canCalcYesLoyalNoOver20Discount(){
        customer.signUp();
        basket.addItem(milk,1);
        double result = basket.calcBasketPrice();
        assertEquals(1.96, result, 0.01);
    }

    @Test
    public void canCalcNoLoyalYesDiscount(){
        basket.addItem(meat,1);
        double result = basket.calcBasketPrice();
        assertEquals(30.15, result, 0.010);
    }

    @Test
    public void canCalcYesLoyalYesDiscount(){
        customer.signUp();
        basket.addItem(meat,1);
        double result = basket.calcBasketPrice();
        assertEquals(29.54,result, 0.010);
    }

}
