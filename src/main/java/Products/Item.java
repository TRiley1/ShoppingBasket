package Products;

public abstract class Item {
    private String name;
    private double price;
    private boolean onOffer;


    public Item(String name, double price) {
        this.name = name;
        this.price = price;
        this.onOffer = false;
    }

    public boolean isOnOffer() {
        return onOffer;
    }

    public double getPrice() {
        return price;
    }

    public void setOnOffer() {
        this.onOffer = true;
    }


    public String getName() {
        return name;
    }

}
