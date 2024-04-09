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

    public static void setId(int id) {
        Renter.id = id;
    }

    public static int id = 0;
    private String name, firstName, code, expiredDate, rentedBook, type;
    private Map<Publication, Integer> listRentedBook;
    private int quantity;
    
    public Renter(){
        
    }
    public Renter(String name, String firstName, String expiredDate, String code, Integer quantity, String rentedBook) {
        this.name = name;
        this.firstName = firstName;
        this.expiredDate = expiredDate;
        this.code = code;
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

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public void setCodeByID() {
        int id = ++Renter.id;
        if(id < 10){
            this.code = "R00000" + id;
        } else if(id < 100){
            this.code = "R0000" + id;
        } else if(id < 1000){
            this.code = "R000" + id;
        } else if(id < 10000){
            this.code = "R00" + id;
        } else if(id < 100000){
            this.code = "R0" + id;
        } else{
            this.code = "R" + id;
        }
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
