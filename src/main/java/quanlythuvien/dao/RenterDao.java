/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.dao;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import quanlythuvien.entities.Publication;
import quanlythuvien.entities.Renter;
import quanlythuvien.utils.FileUtils;

/**
 *
 * @author Admin
 */
public class RenterDao {
    private static final String file = "Renter.xml";
    private List<Renter> listRenter = new ArrayList<>();
    PublicationDao pubDao = new PublicationDao();
    Publication pub = new Publication();
    Renter renter = new Renter();
    
    public RenterDao(){
        this.listRenter = readRenter();
        if(listRenter == null){
            listRenter = new ArrayList<Renter>();
        }
    }
    
    public void writeListRenter(List<Renter> renter) {
        RenterXML renterXML = new RenterXML();
        renterXML.setRenter(renter);
        List<Renter> existingRenters = readRenter();       
        FileUtils.writeXMLtoFile(file, renterXML);
    }
    
    public List<Renter> readRenter() {
        List<Renter> list = new ArrayList<Renter>();
        RenterXML renterXML = (RenterXML) FileUtils.readXMLFile(
                file, RenterXML.class);
        if (renterXML != null) {
            list = renterXML.getRenter();
        }
        return list;
    }
    
    public void addRenter(Renter r){
        listRenter.add(r);
        writeListRenter(listRenter);
    }
    
    public void editRenter(Renter r){
        for(int i = 0; i < listRenter.size(); i++){
            if(Objects.equals(listRenter.get(i).getCode(), r.getCode())){
                listRenter.get(i).setFirstName(r.getFirstName());
                listRenter.get(i).setName(r.getName());
                listRenter.get(i).setRentedBook(r.getRentedBook());
                listRenter.get(i).setType(r.getType());
                listRenter.get(i).setQuantity(r.getQuantity());
                listRenter.get(i).setExpiredDate(r.getExpiredDate());
            }
        }
        writeListRenter(listRenter);
    }
    
    public boolean delete(Renter r){
        boolean check = false;
        for(int i = 0; i < listRenter.size(); i++){
            if(Objects.equals(listRenter.get(i).getCode(), r.getCode())){
                r = listRenter.get(i);
                check = true;
                break;
            }
        }
        if(check){
            listRenter.remove(r);
            writeListRenter(listRenter);
            return true;
        }
        return false;
    }
    
    public void sortByName(){
        Collections.sort(listRenter, new Comparator<Renter>() {
            public int compare(Renter r1, Renter r2) {
                Collator collator = Collator.getInstance(new java.util.Locale("vi", "VN"));
                return r1.getName().compareTo(r2.getName());
            }
        });
    }
    
    public List<Renter> searchByName(String name){
        List<Renter> searchResult = new ArrayList<Renter>();
        for(int i = 0; i < listRenter.size(); i++){
            if(listRenter.get(i).getName().contains(name)) {
                searchResult.add(listRenter.get(i));
            }
        }
        return searchResult;
    }
    
    public Integer countBook(){
        int count = 0;
        for(Renter r : listRenter){
            if("Book".equals(r.getType())){
                count += r.getQuantity();
            }
        } 
        return count;
    }
    
    public Integer countMagazine(){
        int count = 0;
        for(Renter r : listRenter){
            if("Magazine".equals(r.getType())){
                count += r.getQuantity();
            }
        }
        return count;
    } 
    
    public Integer countNovel(){
        int count = 0;
        for(Renter r : listRenter){
            if("Novel".equals(r.getType())){
                count += r.getQuantity();
            }
        }
        return count;
    } 
    
    public Integer countNewspaper(){
        int count = 0;
        for(Renter r : listRenter){
            if("Newspaper".equals(r.getType())){
                count += r.getQuantity();
            }
        }
        return count;
    }
    
    public List<Renter> getListRenter(){
        return listRenter;
    }
}
