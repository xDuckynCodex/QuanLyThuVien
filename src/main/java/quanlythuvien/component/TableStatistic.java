/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.component;

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

/**
 *
 * @author Admin
 */
public class TableStatistic extends JFrame {
    private JScrollPane pane;
    private JTable tableStatistic;
    private String[] columnStatistic = new String[] {"Tên thể loại", "Số lượng đã mượn", "Số lượng còn lại", "Tổng"};
    private Object dataStatistic = new Object[][]{};
    private JPanel panel;
    
    PublicationDao pubDao = new PublicationDao();
    RenterDao renterDao = new RenterDao();
 
    public TableStatistic(){
        init();
    }
    
    public void init(){
        pane = new JScrollPane();
        tableStatistic = new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableStatistic.getTableHeader().setReorderingAllowed(false);
        tableStatistic.setModel(new DefaultTableModel((Object[][]) dataStatistic, columnStatistic));
        pane.setViewportView(tableStatistic);
        pane.setPreferredSize(new Dimension(300, 200));
        
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.add(pane);
        panel.setSize(1000, 1000);
        panel.setLayout(layout);
        
        Object [][] statisticTable = new Object[5][4];

        statisticTable[0][0] = "Book";
        statisticTable[1][0] = "Magazine";
        statisticTable[2][0] = "Novel";
        statisticTable[3][0] = "Newspaper";
        statisticTable[4][0] = "Tổng";
        
        statisticTable[0][1] = String.valueOf(pubDao.count("Book"));
        statisticTable[1][1] = String.valueOf(pubDao.count("Magazine"));
        statisticTable[2][1] = String.valueOf(pubDao.count("Novel"));
        statisticTable[3][1] = String.valueOf(pubDao.count("Newspaper"));
        
        statisticTable[0][2] = String.valueOf(renterDao.count("Book"));
        statisticTable[1][2] = String.valueOf(renterDao.count("Magazine"));
        statisticTable[2][2] = String.valueOf(renterDao.count("Novel"));
        statisticTable[3][2] = String.valueOf(renterDao.count("Newspaper"));
        
        statisticTable[0][3] = String.valueOf(pubDao.count("Book") + renterDao.count("Book"));
        statisticTable[1][3] = String.valueOf(pubDao.count("Magazine") + renterDao.count("Magazine"));
        statisticTable[2][3] = String.valueOf(pubDao.count("Novel") + renterDao.count("Novel"));
        statisticTable[3][3] = String.valueOf(pubDao.count("Newspaper")+ renterDao.count("Newspaper"));
        
        tableStatistic.setModel(new DefaultTableModel(statisticTable, columnStatistic));
        for(int i = 0; i < 6; i++){
            tableStatistic.setRowHeight(i, 20);
        }
        
        this.add(panel);
        this.pack();
        // set jFrame
        this.setTitle("Manager View");
//        this.setSize(1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);

    }
}
