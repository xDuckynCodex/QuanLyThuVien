package quanlythuvien.dao;
import java.util.List;

import jakarta.xml.bind.annotation.*;
import quanlythuvien.entities.Renter;

//Lưu dsach ấn phẩm vào file xml
@XmlRootElement(name = "renters")
@XmlAccessorType(XmlAccessType.FIELD)
public class RenterXML {
    @XmlTransient
    private List<Renter> renter;

    @XmlElement(name = "renter")
    public List<Renter> getRenter(){
        return renter;
    }
    public void setRenter(List<Renter> renter){
        this.renter = renter;
    }
}