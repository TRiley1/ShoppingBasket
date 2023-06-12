import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomerTest {

    Customer customer;


    @Before
    public void setup(){
        customer = new Customer("Boris");
    }

    @Test
    public void startsOffWithNoLoyaltyCard(){
        assertFalse(customer.hasLoyaltyCard());
    }

    @Test
    public void canSignUpToLoyaltyCard(){
        customer.signUp();
        assertTrue(customer.hasLoyaltyCard());
    }
}
