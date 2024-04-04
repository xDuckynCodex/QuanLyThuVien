/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import quanlythuvien.entities.Renter;

/**
 *
 * @author Admin
 */
public class RenterView extends JFrame implements ActionListener, ListSelectionListener {
    // button
    private JButton addRenterBtn;
    private JButton editRenterBtn;
    private JButton deleteRenterBtn;
    private JButton sortByNameRenterBtn;
    private JButton clearBtn;
    private JButton transferBtn;
    
    // label
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel rentedBookLabel;
    private JLabel tableLabel;
    private JLabel transferLabel;
    
    // field nhap
    private JTextField nameField;
    private JTextField idField;
    private JTextField rentedBookField;
    
    // bang
    private JScrollPane pane;
    private JTable table;
    private  TableFilterHeader tableFilter;
    
    // cot
    private String[] column = new String[] {"STT", "Tên", "ID", "Sách đã mượn"};
    private Object data = new Object[][] {};
    public void initComponent(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khoi tao phim
        addRenterBtn = new JButton("Thêm người mượn");
        editRenterBtn = new JButton("Sửa thông tin");
        deleteRenterBtn = new JButton("Xoá người mượn");
        sortByNameRenterBtn = new JButton("Sắp xếp theo tên");
        clearBtn = new JButton("Xoá");
        transferBtn = new JButton("Chuyển");
        
        // khởi tạo label
        nameLabel = new JLabel("Tên người mượn");
        idLabel = new JLabel("ID");
        rentedBookLabel = new JLabel("Sách đã mượn");
        transferLabel = new JLabel("Chuyển qua chế độ quản lý ấn phẩm");
        tableLabel = new JLabel("Danh sách người mượn ấn phẩm");
        tableLabel.setFont(new Font(tableLabel.getFont().getName(),
                Font.PLAIN, 40));
        
        // khởi tạo field
        nameField = new JTextField(20);
        idField = new JTextField(20);
        rentedBookField = new JTextField(20);
        
        // khởi tạo table
        table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        tableFilter = new TableFilterHeader(table, AutoChoices.ENABLED);
        
        // cài đặt bảng
        pane = new JScrollPane();
        table.setModel(new DefaultTableModel((Object[][]) data, column));
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 15));
        pane.setViewportView(table);
        pane.setPreferredSize(new Dimension(1350, 750));
        
        // tạo layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1900, 1050);
        panel.setLayout(layout);
        
        panel.add(addRenterBtn);
        panel.add(editRenterBtn);
        panel.add(deleteRenterBtn);
        panel.add(sortByNameRenterBtn);
        panel.add(transferBtn);
        panel.add(clearBtn);
        
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(rentedBookLabel);
        panel.add(tableLabel);
        panel.add(transferLabel);
        
        panel.add(nameField);
        panel.add(idField);
        panel.add(rentedBookField);
        
        panel.add(pane);

        
        // set location
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, rentedBookLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, rentedBookLabel, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferLabel, 30, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferLabel, 470, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, nameField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 50, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, idField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, rentedBookField, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, rentedBookField, 150, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, addRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addRenterBtn, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editRenterBtn, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteRenterBtn, 300, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByNameRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameRenterBtn, 350, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferBtn, 80, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferBtn, 500, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.EAST, pane, 0, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, pane, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tableLabel, 1050, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tableLabel, 20, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Danh sách người mượn ấn phẩm");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);
    }
    
    public RenterView(){
        initComponent();
    }
    
    public void showListRenter(List<Renter> list){
        Object [][] renterTable = new Object[list.size()][4];
        for(int i = 0; i < list.size(); i++){
            renterTable[i][0] = i + 1;
            renterTable[i][1] = list.get(i).getName();
            renterTable[i][2] = list.get(i).getId();
            renterTable[i][3] = list.get(i).getRentedBook();
        }
        table.setModel(new DefaultTableModel(renterTable, column));
        for(int i = 0; i < list.size(); i++){
            table.setRowHeight(i, 20);
        }
    }
    
    public void fillRenterFromSelectedRow(){
        int row = table.getSelectedRow();
        if(row >= 0){
            nameField.setText(table.getModel().getValueAt(row, 1).toString());
            idField.setText(table.getModel().getValueAt(row, 2).toString());
            rentedBookField.setText(table.getModel().getValueAt(row, 3).toString());
            
            deleteRenterBtn.setEnabled(true);
            editRenterBtn.setEnabled(true);
            addRenterBtn.setEnabled(false);
        }
    }
    
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    
    private boolean validName(){
        String name = nameField.getText();
        if(name == null || name.trim().isEmpty()){
            nameField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    private boolean validID(){
        String id = idField.getText();
        if(id == null || id.trim().isEmpty()){
            idField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    private boolean validRentedBook(){
        String rentedBook = rentedBookField.getText();
        if(rentedBook == null || rentedBook.trim().isEmpty()){
            rentedBookField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    public Renter getRenterInfo(){
        if(!validID() || !validName() || !validRentedBook()){
            return null;
        }
        try{
            Renter renter = new Renter();
            renter.setName(nameField.getText().trim());
            renter.setId(idField.getText().trim());
            renter.setRentedBook(rentedBookField.getText().trim());
            return renter;
        } catch (Exception e){
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public void clear(){
        nameField.setText("");
        idField.setText("");
        rentedBookField.setText("");
        
        editRenterBtn.setEnabled(false);
        deleteRenterBtn.setEnabled(false);
        sortByNameRenterBtn.setEnabled(true);
        addRenterBtn.setEnabled(true);
        table.clearSelection();
    }
    
    public void showRenter(Renter renter){
        nameField.setText(renter.getName());
        idField.setText(renter.getId());
        rentedBookField.setText(renter.getRentedBook());
    }
    
    public void addAddRenterListener(ActionListener listener) {
        addRenterBtn.addActionListener(listener);
    }
    
    public void addEditRenterListener(ActionListener listener) {
        editRenterBtn.addActionListener(listener);
    }
    
    public void addDeleteRenterListener(ActionListener listener) {
        deleteRenterBtn.addActionListener(listener);
    }

    public void addClearRenterListener(ActionListener listener){
        clearBtn.addActionListener(listener);
    }
    
    public void addSortByNameRenterListener(ActionListener listener) {
        sortByNameRenterBtn.addActionListener(listener);
    }
    
    public void addTransferPublicationListener(ActionListener listener){
        transferBtn.addActionListener(listener);
    }
    
     public void addFillRenterFromSelectedRow(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public static void main(String[] args){
        RenterView renterView = new RenterView();
        renterView.setVisible(true);
    }
}
