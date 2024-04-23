package quanlythuvien.dao;

import java.util.*;

import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FileUtils;


public class PublicationDao {
    private static final String file_name = "Publication.xml";
    private List<Publication> listPub;


    public PublicationDao(){
        this.listPub = readPublication();
        if(listPub == null){
            listPub = new ArrayList<>();
        }
        //set id for th
        if (listPub.isEmpty()) {
            Publication.setId(0);
        } else {
            Publication.setId(Integer.parseInt(listPub.getLast().getCode().substring(1)));
        }
    }
    
    // in ra file XML
    public void writeListPub(List<Publication> publications) {
        PublicationXML pubXML = new PublicationXML();
        pubXML.setPublication(publications);
        FileUtils.writeXMLtoFile(file_name, pubXML);
    }
    
    // doc file XML
    public List<Publication> readPublication() {
        List<Publication> list = new ArrayList<>();
        PublicationXML pubXML = (PublicationXML) FileUtils.readXMLFile(
                file_name, PublicationXML.class);
        if (pubXML != null) {
            list = pubXML.getPublication();
        }
        return list;
    }
    
    // them sach
    public void add(Publication pub){
        listPub.add(pub);
        writeListPub(listPub);
    }
    
    // sua thong tin

    public void edit(Publication pub)  {
        for (Publication publication : listPub) {
            if (Objects.equals(publication.getCode(), pub.getCode())) {
                publication.setName(pub.getName());
                publication.setType(pub.getType());
                publication.setPublisher(pub.getPublisher());
                publication.setAuthor(pub.getAuthor());
                publication.setPrice(pub.getPrice());
                publication.setQuantity(pub.getQuantity());
                publication.setPublishedDate(pub.getPublishedDate());
            }
        }
        writeListPub(listPub);
    }

    public void setRentedInDeleting(Publication publication, int quantityRented) {
        for (Publication p : listPub) {
            if (Objects.equals(p.getCode(), publication.getCode())) {
                p.setRented(p.getRented() - quantityRented);
            }
        }
        writeListPub(listPub);
    }


    public void delete(Publication pub){
        listPub.remove(pub);
        writeListPub(listPub);
    }

//    public Publication searchByCode(String code) {
//        for (Publication p : listPub) {
//            if (Objects.equals(p.getCode(), code)) {
//                return p;
//            }
//        }
//        return null;
//    }
    
    public List<Publication> searchByName(String name){
        List<Publication> searchResult = new ArrayList<>();
        for (Publication publication : listPub) {
            if (publication.getName().contains(name) || publication.getName().toLowerCase(Locale.ROOT).contains(name)) {
                searchResult.add(publication);
            }
        }
        return searchResult;
    }
    
    public List<Publication> filterByType(String type){
        List<Publication> filterResult = new ArrayList<>();
        for (Publication publication : listPub) {
            if (publication.getType().contains(type)) {
                filterResult.add(publication);
            }
        }
        return filterResult;
    }

    public Integer countType(String type) {
        int count = 0;
        for(Publication p : listPub){
            if(Objects.equals(type, p.getType())){
                count += p.getQuantity();
            }
        }
        return count;
    }

    public int countRentedType(String type) {
        int count = 0;
        for(Publication p : listPub){
            if(Objects.equals(type, p.getType())){
                count += p.getRented();
            }
        }
        return count;
    }

    public List<Publication> getListPublication(){
        return listPub;
    }
}
