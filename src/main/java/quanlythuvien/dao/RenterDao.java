/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.dao;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import quanlythuvien.entities.Publication;
import quanlythuvien.entities.Renter;
import quanlythuvien.utils.DateFomatterUtil;
import quanlythuvien.utils.FileUtils;

/**
 *
 * @author Admin
 */
public class RenterDao {
    private static final String file = "Renter.xml";
    private List<Renter> listRenter = new ArrayList<>();

    public RenterDao(){
        this.listRenter = readRenter();
        if(listRenter == null){
            listRenter = new ArrayList<Renter>();
        }
        //set id for th
        if (listRenter.isEmpty()) {
            Renter.setId(0);
        } else {
            Renter.setId(Integer.parseInt(listRenter.getLast().getCode().substring(1)));
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
            }
        }
        writeListRenter(listRenter);
    }
    
    public void delete(Renter r){
        for(int i = 0; i < listRenter.size(); i++){
            if(listRenter.get(i).getCode().equals(r.getCode())){
                listRenter.get(i).isPaidBack = true;
                Date date = DateFomatterUtil.stringToValue(listRenter.get(i).getExpiredDate());
                Date dateNow = new Date();
                listRenter.get(i).setPayBackDate(DateFomatterUtil.valueToString(dateNow));
                if(dateNow.before(date)){
                    listRenter.get(i).setState("Trả sách đúng hạn");
                } else{
                    long day = (dateNow.getTime() - date.getTime()) / (1000*60*60*24);
                    listRenter.get(i).setState("Quá hạn " + day + " ngày");
                }
                writeListRenter(listRenter);
                break;
            }
        }
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
            if(listRenter.get(i).isPaidBack){
                String fullName = listRenter.get(i).getFirstName() + listRenter.get(i).getName();
                if(fullName.toLowerCase().contains(name) || fullName.toUpperCase().contains(name)) {
                    searchResult.add(listRenter.get(i));
                }
            }  
        }
        return searchResult;
    }

    public Renter getRenterByCode(String code) {
        for (Renter renter : listRenter) {
            if (Objects.equals(code, renter.getCode())) {
                return renter;
            }
        }
        return null;
    }

    public Renter getByCode(String code) {
        for (Renter r : listRenter) {
            if (Objects.equals(code, r.getCode())) {
                return r;
            }
        }
        return null;
    }
    
    public List<Renter> getListRenter(){
        return listRenter;
    }

    public List<Renter> getListRenterPayingBack(){
        List<Renter> listRenterPayingBack = new ArrayList<Renter>();
        for(Renter r : listRenter){
            if(r.isPaidBack){
                listRenterPayingBack.add(r);
            }
        }
        return listRenterPayingBack;
    }

    public void setListRenterPayingBack(String searchText){
        List<Renter> listRenterPayingBack = new ArrayList<Renter>();
    }

    public List<Renter> getListRenterNotPayingBack(){
        List<Renter> listRenterNotPayingBack = new ArrayList<Renter>();
        for(Renter r : listRenter){
            if(r.isPaidBack == false){
                listRenterNotPayingBack.add(r);
            }
        }
        return listRenterNotPayingBack;
    }
}
