package quanlythuvien.dao;
import java.util.List;

import javax.xml.bind.annotation.*;

import quanlythuvien.entities.Publication;

//Lưu dsach ấn phẩm vào file xml
@XmlRootElement(name = "publications")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublicationXML {
    @XmlTransient
    private List<Publication> pub;

    @XmlElement(name = "pub")
    public List<Publication> getPublication(){
        return pub;
    }
    public void setPublication(List<Publication> pub){
        this.pub = pub;
    }
}
