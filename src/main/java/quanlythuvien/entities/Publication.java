package quanlythuvien.entities;


import java.io.Serial;
import java.io.Serializable;
//@XmlElement(name = "publication")
//@XmlAccessorType(XmlAccessType.FIELD)
public class Publication implements Serializable {
    // class định nghĩa các ấn phẩm của thư viện
    // chứa các thông tin sau: sách, báo, tạp chí, chuyên san, tập san, sách tranh…, 
    //giá thành, số lượng, mã số, Nhà xuất bản, tác giả…
    @Serial
    private static final long serialVersionUID = 1L;

    public static void setId(int id) {
        Publication.id = id;
    }

    private static int id = 0;
    private String type, code, publisher, author, name, imgPath;
    private double price;
    private int quantity;

    public int getRented() {
        return rented;
    }

    public void setRented(int rented) {
        this.rented = rented;
    }

    private int rented;
    private String publishedDate;
    public Publication(){
       rented = 0;
        imgPath = ".\\public\\images\\ph.png";
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

    public void setCodeById() {
        int id = ++Publication.id;
        if (id < 10) {
            this.code = "P00000" + id;
        } else if (id < 100) {
            this.code = "P0000" + id;
        } else if (id < 1000) {
            this.code = "P000" + id;
        } else if (id < 10000) {
            this.code = "P00" + id;
        } else if (id < 100000) {
            this.code = "P0" + id;
        } else {
            this.code = "P" + id;
        }
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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}