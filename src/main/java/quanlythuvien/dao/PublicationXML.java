package quanlythuvien.dao;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import quanlythuvien.entities.Publication;

//Lưu dsach ấn phẩm vào file xml
@XmlRootElement(name = "Publication")
@XmlAccessorType(XmlAccessType.FIELD)
public class PublicationXML {
    private List<Publication> pub;
    public List<Publication> getPublication(){
        return pub;
    }
    public void setPublication(List<Publication> pub){
        this.pub = pub;
    }
}
