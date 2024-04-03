/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FileUtils;

/**
 *
 * @author Admin
 */
public class RenterDao {
    private static final String file = "RenterXML";
    private HashMap<Integer, String> renter = new HashMap<>();
    public HashMap<Integer, String> readRenter() {
        HashMap<Integer, String> map = new HashMap<>();
        PublicationXML pubXML = (PublicationXML) FileUtils.readXMLFile(
                file, PublicationXML.class);
        if (pubXML != null) {
            map = pubXML.getRenter();
        }
        return map;
    }
}
