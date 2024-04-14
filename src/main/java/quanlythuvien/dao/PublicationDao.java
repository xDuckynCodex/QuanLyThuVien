package quanlythuvien.dao;

import java.text.ParseException;
import java.util.*;

import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FileUtils;


public class PublicationDao {
    private static final String file_name = "Publication.xml";
    private List<Publication> listPub;

    List<Publication> pubFilter = new ArrayList<Publication>();

    public PublicationDao(){
        this.listPub = readPublication();
        if(listPub == null){
            listPub = new ArrayList<Publication>();
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
        List<Publication> existingPublications = readPublication();
        FileUtils.writeXMLtoFile(file_name, pubXML);
    }
    
    // doc file XML
    public List<Publication> readPublication() {
        List<Publication> list = new ArrayList<Publication>();
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
        for(int i = 0; i < listPub.size(); i++ ){
            if(Objects.equals(listPub.get(i).getCode(), pub.getCode())){
                listPub.get(i).setName(pub.getName());
                listPub.get(i).setType(pub.getType());
                listPub.get(i).setPublisher(pub.getPublisher());
                listPub.get(i).setAuthor(pub.getAuthor());
                listPub.get(i).setPrice(pub.getPrice());
                listPub.get(i).setQuantity(pub.getQuantity());
                listPub.get(i).setPublishedDate(pub.getPublishedDate());
            }
        }
        writeListPub(listPub);
    }

    public void setRentedInDeleting(Publication publication,
                                    int quantityRented) {
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

    public void deleteByCode(String code) {

    }

    //sap xep theo ten
    public void sortByName(){
        Collections.sort(listPub, new Comparator<Publication>(){
            public int compare(Publication pub1, Publication pub2){
                return pub1.getName().compareTo(pub2.getName());
            }
        });
    }
    
    // sap xep theo gia ca
    public void sortByPrice(){
        Collections.sort(listPub, new Comparator<Publication>(){
            public int compare(Publication pub1, Publication pub2){
                if(pub1.getPrice() > pub2.getPrice()){
                    return 1;
                } else if (pub1.getPrice() < pub2.getPrice()) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public Publication searchByCode(String code) {
        for (Publication p : listPub) {
            if (Objects.equals(p.getCode(), code)) {
                return p;
            }
        }
        return null;
    }
    
    public List<Publication> searchByName(String name){
        List<Publication> searchResult = new ArrayList<Publication>();
        for(int i = 0; i < listPub.size(); i++){
            if(listPub.get(i).getName().contains(name) || listPub.get(i).getName().toLowerCase(Locale.ROOT).contains(name)) {
                searchResult.add(listPub.get(i));
            }
        }
        return searchResult;
    }
    
    public List<Publication> filterByType(String type){
        List<Publication> searchResult = new ArrayList<Publication>();
        for(int i = 0; i < listPub.size(); i++){
            if(listPub.get(i).getType().contains(type)) {
                searchResult.add(listPub.get(i));
            }
        }
        return searchResult;
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
