/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.entities;

import jakarta.xml.bind.annotation.XmlElement;
import java.util.*;

/**
 *
 * @author Admin
 */
public class Renter {

    public static void setId(int id) {
        Renter.id = id;
    }

    public static int id = 0;
    private String name, firstName, code, expiredDate, payBackDate, state;
    public boolean isPaidBack;

//    public boolean getIsPaidBack() {
//        return isPaidBack;
//    }
//
//    public void setIsPaidBack(boolean isPaidBack) {
//        this.isPaidBack = isPaidBack;
//    }

    public List<RentedBook> getRentedBookList() {
        return rentedBook;
    }

    public void setRentedBookList(List<RentedBook> rentedBookList) {
        this.rentedBook = rentedBookList;
    }
    public void addRentedBook(RentedBook rentedBook) {
        this.rentedBook.add(rentedBook);
    }
//    @XmlElement(name = "rentedBooks")
    private List<RentedBook> rentedBook;

    public Renter(){
        rentedBook = new ArrayList<>();
        this.isPaidBack = false;
    }
  
    public Renter(String name, String firstName,  String code) {
        this.name = name;
        this.firstName = firstName;
        this.code = code;
    }

    public String getPayBackDate() {
        return payBackDate;
    }

    public void setPayBackDate(String payBackDate) {
        this.payBackDate = payBackDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }

    public String getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(String expiredDate) {
        this.expiredDate = expiredDate;
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
}
