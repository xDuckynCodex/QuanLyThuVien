/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.components;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.ManageView;

/**
 *
 * @author Admin
 */
public class TableStatistic extends JScrollPane {
    private JTable tableStatistic;
    private final String[] columnStatistic = new String[] {"Tên thể loại", "Số lượng còn lại", "Số lượng đã mượn", "Tổng"};
    private final Object[][] dataStatistic = new Object[5][4];
    PublicationDao pubDao = new PublicationDao();
    RenterDao renterDao = new RenterDao();

    public TableStatistic(){
        initComponent();
    }

    public void initComponent() {
        tableStatistic = new JTable(){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tableStatistic.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableStatistic.getTableHeader().setReorderingAllowed(false);

        this.calculateData();
    }

    public void calculateData() {
        pubDao = new PublicationDao();
        renterDao = new RenterDao();

        dataStatistic[0][0] = "Book";
        dataStatistic[1][0] = "Magazine";
        dataStatistic[2][0] = "Novel";
        dataStatistic[3][0] = "Newspaper";
        dataStatistic[4][0] = "Tổng";

        dataStatistic[0][1] =
               pubDao.countType((String) dataStatistic[0][0]) - pubDao.countRentedType((String) dataStatistic[0][0]);
        dataStatistic[1][1] =
               pubDao.countType((String) dataStatistic[1][0]) - pubDao.countRentedType((String) dataStatistic[1][0]);
        dataStatistic[2][1] =
               pubDao.countType((String) dataStatistic[2][0]) - pubDao.countRentedType((String) dataStatistic[2][0]);
        dataStatistic[3][1] =
               pubDao.countType((String) dataStatistic[3][0]) - pubDao.countRentedType((String) dataStatistic[3][0]);

        dataStatistic[0][2] = pubDao.countRentedType((String) dataStatistic[0][0]);
        dataStatistic[1][2] = pubDao.countRentedType((String) dataStatistic[1][0]);
        dataStatistic[2][2] = pubDao.countRentedType((String) dataStatistic[2][0]);
        dataStatistic[3][2] = pubDao.countRentedType((String) dataStatistic[3][0]);

        dataStatistic[0][3] =
                pubDao.countType((String) dataStatistic[0][0]);
        dataStatistic[1][3] =
                pubDao.countType((String) dataStatistic[1][0]);
        dataStatistic[2][3] =
                pubDao.countType((String) dataStatistic[2][0]);
        dataStatistic[3][3] =
                pubDao.countType((String) dataStatistic[3][0]);
        
        dataStatistic[4][1] = Integer.valueOf(dataStatistic[0][1].toString()) + Integer.valueOf(dataStatistic[1][1].toString()) 
                + Integer.parseInt(dataStatistic[2][1].toString()) + Integer.parseInt(dataStatistic[3][1].toString());
        
        dataStatistic[4][2] = Integer.valueOf(dataStatistic[0][2].toString()) + Integer.valueOf(dataStatistic[1][2].toString()) 
                + Integer.valueOf(dataStatistic[2][2].toString()) + Integer.valueOf(dataStatistic[3][2].toString());
        
        dataStatistic[4][3] = Integer.valueOf(dataStatistic[0][3].toString()) + Integer.valueOf(dataStatistic[1][3].toString()) 
                + Integer.valueOf(dataStatistic[2][3].toString()) + Integer.valueOf(dataStatistic[3][3].toString());
        

        tableStatistic.setModel(new DefaultTableModel(dataStatistic, columnStatistic));

        for(int i = 0; i < 6; i++){
            tableStatistic.setRowHeight(i, 30);
        }

        this.setViewportView(tableStatistic);
        this.setPreferredSize(new Dimension(400, 200));
    }
}
