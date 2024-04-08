/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.components;

import java.awt.Component;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.table.DefaultTableModel;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.ManageView;

/**
 *
 * @author Admin
 */
public class TableStatistic extends JPanel {
    private JScrollPane pane;
    private JTable tableStatistic;
    private String[] columnStatistic = new String[] {"Tên thể loại", "Số lượng còn lại", "Số lượng đã mượn", "Tổng"};
    private Object dataStatistic = new Object[][]{};
    private JPanel panel;
    Object [][] statisticTable = new Object[5][4];

    PublicationDao pubDao = new PublicationDao();
    RenterDao renterDao = new RenterDao();
    
    public Object[][] getStatisticTable() {
        return statisticTable;
    }

    public void setStatisticTable(int x, int y, int newValue) {
        this.statisticTable[x][y] = newValue;
    }    
 
    public TableStatistic(){
        init(pubDao.getListPublication());
    }
    
    public void init(List<Publication> list){
        pane = new JScrollPane();
        tableStatistic = new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableStatistic.getTableHeader().setReorderingAllowed(false);
        tableStatistic.setModel(new DefaultTableModel((Object[][]) dataStatistic, columnStatistic));
        pane.setViewportView(tableStatistic);
        pane.setPreferredSize(new Dimension(300, 175));
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.add(pane);
        panel.setSize(300, 150);

        panel.setLayout(layout);     

        statisticTable[0][0] = "Book";
        statisticTable[1][0] = "Magazine";
        statisticTable[2][0] = "Novel";
        statisticTable[3][0] = "Newspaper";
        statisticTable[4][0] = "Tổng";
        
        statisticTable[0][1] = String.valueOf(pubDao.countBook());
        statisticTable[1][1] = String.valueOf(pubDao.countMagazine());
        statisticTable[2][1] = String.valueOf(pubDao.countNovel());
        statisticTable[3][1] = String.valueOf(pubDao.countNewspaper());
        
        statisticTable[0][2] = String.valueOf(renterDao.countBook());
        statisticTable[1][2] = String.valueOf(renterDao.countMagazine());
        statisticTable[2][2] = String.valueOf(renterDao.countNovel());
        statisticTable[3][2] = String.valueOf(renterDao.countNewspaper());
        
        statisticTable[0][3] = String.valueOf(pubDao.countBook() + renterDao.countBook());
        statisticTable[1][3] = String.valueOf(pubDao.countMagazine() + renterDao.countMagazine());
        statisticTable[2][3] = String.valueOf(pubDao.countNovel() + renterDao.countNovel());
        statisticTable[3][3] = String.valueOf(pubDao.countNewspaper() + renterDao.countNewspaper());
        
        tableStatistic.setModel(new DefaultTableModel(statisticTable, columnStatistic));
        
        for(int i = 0; i < 6; i++){
            tableStatistic.setRowHeight(i, 30);
        }
        this.add(pane);
        this.setSize(new Dimension(300, 150));
    }
}
