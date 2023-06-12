public class Customer {
    private String name;
    private boolean loyalty;

    public Customer(String name) {
        this.name = name;
        this.loyalty = false;
    }

    public void signUp(){
        this.loyalty = true;
    }

    public boolean hasLoyaltyCard() {
        return loyalty;
    }
}
