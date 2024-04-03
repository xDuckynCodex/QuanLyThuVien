package quanlythuvien.dao;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import quanlythuvien.entities.Renter;

//Lưu dsach ấn phẩm vào file xml
@XmlRootElement(name = "renters")
@XmlAccessorType(XmlAccessType.FIELD)
public class RenterXML {
    private List<Renter> renter;

    @XmlElement(name = "renter")
    public List<Renter> getRenter(){
        return renter;
    }
    public void setRenter(List<Renter> renter){
        this.renter = renter;
    }
}