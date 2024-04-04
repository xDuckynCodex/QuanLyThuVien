/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.entities;

/**
 *
 * @author Admin
 */
public class Renter {
    private String name, rentedBook, id;
 
    public Renter(){
        
    }
    public Renter(String name, String id, String rentedBook) {
        this.name = name;
        this.id = id;
        this.rentedBook = rentedBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
}
