/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.views;

import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import quanlythuvien.components.InputField;
import quanlythuvien.dao.RenterDao;

/**
 *
 * @author Admin
 */
public class PayerView extends JFrame{
    private RenterDao renterDao;
    private JLabel title;
    
    // thêm nút
    private JButton deleteBtn;
    private JButton transferToPublicationViewBtn;
    private JButton transfertoRenterViewBtn;
    
    // thêm trường tìm kiếm
    private InputField searchField;
    
    // thêm trường giao diện
    private ManageView manageView;
    private InfoView infoView;
    
    // thêm bảng
    private JScrollPane payerScrollPane;
    private JTable payerTable;
    
    // thêm cột cho bảng
    private final String[] column = new String[] {"STT", "Tên", "ID", "Ngày trả"};
    private final Object data = new Object[][] {};
    
    public void initComponet(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        searchField = new InputField("Tìm kiếm người trả: ", 20);
        deleteBtn = new JButton("Xoá người trả");
        transferToPublicationViewBtn = new JButton("Chuyển sang quản lý ấn phẩm");
        transfertoRenterViewBtn = new JButton("Chuyển sang quản lý người trả");
        deleteBtn.setEnabled(false);
        
        title = new JLabel("Quản lý người trả ấn phẩm");
        title.setFont(new Font(title.getFont().getName(),
                Font.PLAIN, 40));
        
        // khởi tạo bảng
        payerTable= new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        payerTable.getTableHeader().setReorderingAllowed(false);
        
        payerScrollPane = new JScrollPane();
        payerTable.setModel(new DefaultTableModel((Object[][]) data, column));
        payerTable.setFont(new Font(payerTable.getFont().getName(), Font.PLAIN, 15));

        payerScrollPane.setViewportView(payerTable);
        payerScrollPane.setPreferredSize(new Dimension(1350, 750));
        
        // tạo layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        
        panel.setSize(1900, 1050);
        panel.setLayout(layout);
        
        panel.add(deleteBtn);
        panel.add(transferToPublicationViewBtn);
        panel.add(transfertoRenterViewBtn);
        
        panel.add(title);
        panel.add(searchField);
        
        panel.add(payerScrollPane);
        
        // thiết lập vị trí
        layout.putConstraint(SpringLayout.EAST, payerScrollPane, 0, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, payerScrollPane, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteBtn, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferToPublicationViewBtn, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferToPublicationViewBtn, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transfertoRenterViewBtn, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transfertoRenterViewBtn, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchField, 130, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 250, SpringLayout.NORTH, panel);    
        layout.putConstraint(SpringLayout.WEST, title, 1050, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, title, 20, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Danh sách người trả ấn phẩm");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public PayerView(){
        initComponet();
    }
    
    public static void main(String[] args){
        PayerView payerView = new PayerView();
        payerView.setVisible(true);
    }
}
