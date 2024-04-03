package quanlythuvien.dao;

import java.text.ParseException;
import java.util.*;

import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FileUtils;

public class PublicationDao {
    private static final String file_name = "Publication.xml";
    private List<Publication> listPub;
    public PublicationDao(){
        this.listPub = readPublication();
        if(listPub == null){
            listPub = new ArrayList<Publication>();
        }
    }
    
    // in ra file XML
    public void writeListPub(List<Publication> listPublications) {
        PublicationXML pubXML = new PublicationXML();
        pubXML.setPublication(listPublications);
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
    public void edit(Publication pub) throws ParseException {
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
    }
    
    // xoa sach
    public boolean delete(Publication pub){
        boolean check = false;
        for(int i = 0; i < listPub.size(); i++){
            if(Objects.equals(listPub.get(i).getCode(), pub.getCode())){
                pub = listPub.get(i);
                check = true;
                break;
            }
        }
        if(check){
            listPub.remove(pub);
            writeListPub(listPub);
            return true;
        }
        return false;
    }
    
    public boolean Delete(String code){
        Publication pub = listPub.stream().filter(publi -> publi.getCode().equals(code)).findFirst().orElse(null);
        if(pub == null){
            return false;
        }
        this.listPub.remove(pub);
        return true;
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
    
    public void filter(){
        Publication p = listPub.stream().filter(publi -> "Tap chi".equals(publi.getType())).findAny().orElse(null);
        System.out.println(p);
    }
    
    public List<Publication> searchByName(String name){
        List<Publication> searchResult = new ArrayList<Publication>();
        for(int i = 0; i < listPub.size(); i++){
            if(listPub.get(i).getName().contains(name)) {
                searchResult.add(listPub.get(i));
            }
        }
        return searchResult;
    }
    
    public List<Publication> getListPublication(){
        return listPub;
    }
}
