/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.entities;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class Renter {
    private String name, firstName, id, expiredDate, rentedBook, type;
    private Map<Publication, Integer> listRentedBook;
    private int quantity;
    
    public Renter(){
        
    }
    public Renter(String name, String firstName, String expiredDate, String id, Integer quantity, String rentedBook) {
        this.name = name;
        this.firstName = firstName;
        this.expiredDate = expiredDate;
        this.type = type;
        this.id = id;
        this.quantity = quantity;
        this.rentedBook = rentedBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRentedBook() {
        return rentedBook;
    }

    public void setRentedBook(String rentedBook) {
        this.rentedBook = rentedBook;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
