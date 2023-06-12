import Products.Milk;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemChildTest {
    Milk milk;

    @Before
    public void setup(){
        milk = new Milk("Cravendale", 2.00);
    }

    @Test
    public void canMilkGoOnOffer(){
        milk.setOnOffer();
        assertTrue(milk.isOnOffer());
    }

    @Test
    public void initialInstanceNotOnOffer(){
        assertFalse(milk.isOnOffer());
    }

}
