package quanlythuvien.entities;

public class RentedBook {
    private Publication publication;
    private int quantity;
    public RentedBook() {

    }
    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }  
}
