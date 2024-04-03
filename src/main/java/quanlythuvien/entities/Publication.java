package quanlythuvien.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
public class Publication implements Serializable {
    // class định nghĩa các ấn phẩm của thư viện
    // chứa các thông tin sau: sách, báo, tạp chí, chuyên san, tập san, sách tranh…, 
    //giá thành, số lượng, mã số, Nhà xuất bản, tác giả…
    private static final long serialVersionUID = 1L;
    private String type, code, publisher, author, name;
    private double price;
    private int quantity;
    private String publishedDate;
    public Publication(){
       
    }
    public Publication(String type, String code, String publisher,
                       String author, double price, int quantity,
                       String publishedDate, String name) {
        this.name = name;
        this.type = type;
        this.code = code;
        this.publisher = publisher;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.publishedDate = publishedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
