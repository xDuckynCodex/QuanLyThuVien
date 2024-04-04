/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
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
             if(Objects.equals(listRenter.get(i).getId(), r.getId())){
                 listRenter.get(i).setName(r.getName());
                 listRenter.get(i).setRentedBook(r.getRentedBook());
             }
        }
        writeListRenter(listRenter);
    }
    
    public boolean delete(Renter r){
        boolean check = false;
        for(int i = 0; i < listRenter.size(); i++){
            if(Objects.equals(listRenter.get(i).getId(), r.getId())){
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
        Collections.sort(listRenter, new Comparator<Renter>(){
            public int compare(Renter r1, Renter r2){
                return r1.getName().compareTo(r2.getName());
            }
        });
    }
    
    public List<Renter> getListRenter(){
        return listRenter;
    }
}
