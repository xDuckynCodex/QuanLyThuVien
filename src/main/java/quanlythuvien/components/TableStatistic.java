/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.components;

import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    private String[] columnStatistic = new String[] {"Tên thể loại", "Số lượng còn lại", "Số lượng đã mượn", "Tổng"};
    private Object[][] dataStatistic = new Object[5][4];
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

        dataStatistic[0][1] = String.valueOf(pubDao.countBook() - renterDao.countBook());
        dataStatistic[1][1] = String.valueOf(pubDao.countMagazine() - renterDao.countMagazine());
        dataStatistic[2][1] = String.valueOf(pubDao.countNovel() - renterDao.countNovel());
        dataStatistic[3][1] = String.valueOf(pubDao.countNewspaper() - renterDao.countNewspaper());

        dataStatistic[0][2] = String.valueOf(renterDao.countBook());
        dataStatistic[1][2] = String.valueOf(renterDao.countMagazine());
        dataStatistic[2][2] = String.valueOf(renterDao.countNovel());
        dataStatistic[3][2] = String.valueOf(renterDao.countNewspaper());

        dataStatistic[0][3] = String.valueOf(pubDao.countBook() );
        dataStatistic[1][3] = String.valueOf(pubDao.countMagazine());
        dataStatistic[2][3] = String.valueOf(pubDao.countNovel() );
        dataStatistic[3][3] = String.valueOf(pubDao.countNewspaper() );

        tableStatistic.setModel(new DefaultTableModel(dataStatistic, columnStatistic));

        for(int i = 0; i < 6; i++){
            tableStatistic.setRowHeight(i, 30);
        }

        this.setViewportView(tableStatistic);
        this.setPreferredSize(new Dimension(400, 200));
    }
}
