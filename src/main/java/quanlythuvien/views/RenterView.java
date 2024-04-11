/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.entities.Renter;
import quanlythuvien.utils.DateFomatterUtil;

/**
 *
 * @author Admin
 */
public class RenterView extends JFrame implements ActionListener, ListSelectionListener {
    // button
    Publication pub = new Publication();
    PublicationDao pubDao = new PublicationDao();
    Renter renter = new Renter();
    RenterDao renterDao = new RenterDao();

    private JButton addRenterBtn;
    private JButton editRenterBtn;
    private JButton deleteRenterBtn;
    private JButton sortByNameRenterBtn;
    private JButton clearBtn;
    private JButton transferBtn;
    
    // label
    private JLabel firstNameLabel;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel rentedBookLabel;
    private JLabel tableLabel;
    private JLabel quantityLabel;
    private JLabel typeLabel;
    private JLabel expiredDateLabel;
    private JLabel transferLabel;
    
    // field nhap
    private JTextField firstNameField;
    private JTextField nameField;
    private JTextField idField;
    private JTextField rentedBookField;
    private JTextField typeField;
    private JTextField quantityField;
    private JTextField expiredDateField;
    
    //date picker
    private UtilDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private Date dateValue;
    private String dateString;
    private String[] dateArray;
    private int year, month, day;
    
    // bang
    private JScrollPane pane;
    private JScrollPane panePub;
    private JTable table;
    private JTable tablePub;
    private TableFilterHeader tableFilter;
    
    // Combobox Type
    private JComboBox typeComboBox;
    private String typeString;
    private String[] types = {"Book", "Magazine", "Novel", "Newspapers"};
    
    // cot
    private String[] column = new String[] {"STT","Họ và tên đệm", "Tên", "ID", "Sách đã mượn", "Thể loại", "Số lượng", "Ngày trả"};
    private Object data = new Object[][] {};
    private Object dataPub = new Object[][] {};
    private String[] columnPub = new String[] {"Ấn phẩm"};
    
    public void initComponent(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khoi tao phim
        addRenterBtn = new JButton("Thêm người mượn");
        editRenterBtn = new JButton("Sửa thông tin");
        deleteRenterBtn = new JButton("Xoá người mượn");
        sortByNameRenterBtn = new JButton("Sắp xếp theo tên");
        clearBtn = new JButton("Xoá");
        transferBtn = new JButton("Chuyển");
        
        //date time picker
        dateModel = new UtilDateModel();
        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        
        // khởi tạo label
        firstNameLabel = new JLabel("Họ và tên đệm người mượn");
        nameLabel = new JLabel("Tên người mượn");
        idLabel = new JLabel("ID");
        rentedBookLabel = new JLabel("Sách đã mượn");
        typeLabel = new JLabel("Thể loại");
        quantityLabel = new JLabel("Số lượng");
        expiredDateLabel = new JLabel("Ngày hết hạn");
        transferLabel = new JLabel("Chuyển qua chế độ quản lý ấn phẩm");
        transferLabel.setFont(new Font(transferLabel.getFont().getName(), Font.BOLD, 20));
        tableLabel = new JLabel("Danh sách người mượn ấn phẩm");
        tableLabel.setFont(new Font(tableLabel.getFont().getName(),
                Font.PLAIN, 40));
        
        // khởi tạo field
        firstNameField = new JTextField(20);
        nameField = new JTextField(20);
        idField = new JTextField(20);
        idField.setEnabled(false);
        rentedBookField = new JTextField(20);
        quantityField = new JTextField(20);
        expiredDateField = new JTextField(20);
        
        // ComboBo
        typeComboBox = new JComboBox(types);
        typeString = String.valueOf(typeComboBox.getItemAt(typeComboBox.getSelectedIndex()));
        
        // khởi tạo table
        table = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        tableFilter = new TableFilterHeader(table, AutoChoices.ENABLED);
        
        tablePub = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
            
        // cài đặt bảng
        pane = new JScrollPane();
        panePub = new JScrollPane();
        
        tablePub.setModel(new DefaultTableModel((Object[][]) dataPub, columnPub));
        tablePub.setFont(new Font(tablePub.getFont().getName(), Font.PLAIN, 15));
        panePub.setViewportView(tablePub);         
        panePub.setPreferredSize(new Dimension(220, 70));
               
        table.setModel(new DefaultTableModel((Object[][]) data, column));
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 15));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.clearSelection();


        pane.setViewportView(table);
        pane.setPreferredSize(new Dimension(1350, 750));
        
        // tạo layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        JPanel panelPub = new JPanel();
        
        panel.setSize(1900, 1050);
        panel.setLayout(layout);
        
        panel.add(addRenterBtn);
        panel.add(editRenterBtn);
        panel.add(deleteRenterBtn);
        panel.add(sortByNameRenterBtn);
        panel.add(transferBtn);
        panel.add(clearBtn);
        
        panel.add(firstNameLabel);
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(rentedBookLabel);
        panel.add(typeLabel);
        panel.add(quantityLabel);
        panel.add(expiredDateLabel);
        panel.add(tableLabel);
        panel.add(transferLabel);
        
        panel.add(firstNameField);
        panel.add(nameField);
        panel.add(idField);
        panel.add(rentedBookField);
        panel.add(quantityField);
        panel.add(typeComboBox);
        
        panel.add(datePicker);
        panel.add(pane);
        panelPub.add(panePub);
        panel.add(panelPub);

        // set location
        layout.putConstraint(SpringLayout.WEST, firstNameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, firstNameLabel, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idLabel, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, rentedBookLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, rentedBookLabel, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityLabel, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeLabel, 190, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, expiredDateLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, expiredDateLabel, 220, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferLabel, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferLabel, 550, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, panelPub, 300, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, panelPub, 250, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.WEST, firstNameField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, firstNameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, idField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, idField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, rentedBookField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, rentedBookField, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityField, 160, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeComboBox, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeComboBox, 190, SpringLayout.NORTH, panel);
   
        layout.putConstraint(SpringLayout.WEST, addRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addRenterBtn, 250, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editRenterBtn, 300, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteRenterBtn, 350, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByNameRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameRenterBtn, 400, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 450, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferBtn, 150, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferBtn, 600, SpringLayout.NORTH, panel);
        
        layout.putConstraint(SpringLayout.EAST, pane, 0, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, pane, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tableLabel, 1050, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tableLabel, 20, SpringLayout.NORTH, panel);
        
        //datepicker
        layout.putConstraint(SpringLayout.WEST, datePicker, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, datePicker, 220, SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Danh sách người mượn ấn phẩm");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setResizable(false);
    }
    
    public RenterView(){
        initComponent();
    }

    public void showView() {
        this.setVisible(true);
    }
    
    public void showResultView(String searchText){
        List<Publication> list = pubDao.searchByName(searchText);
        Object [][] rentedBookTable = new Object[list.size()][1];
        for(int i = 0; i < list.size(); i++){
            rentedBookTable[i][0] = list.get(i).getName();
        }
        tablePub.setModel(new DefaultTableModel(rentedBookTable, columnPub));
        for(int i = 0; i < list.size(); i++){
            tablePub.setRowHeight(i, 20);
        }
        panePub.setVisible(true);
    }

    public void hideTablePub(){
        panePub.setVisible(false);
    }
    
    public void showListRenter(List<Renter> list){
        Object [][] renterTable = new Object[list.size()][8];
        for(int i = 0; i < list.size(); i++){
            renterTable[i][0] = i + 1;
            renterTable[i][1] = list.get(i).getFirstName();
            renterTable[i][2] = list.get(i).getName();
            renterTable[i][3] = list.get(i).getCode();
            renterTable[i][4] = list.get(i).getRentedBook();
            renterTable[i][5] = list.get(i).getType();
            renterTable[i][6] = list.get(i).getQuantity();
            renterTable[i][7] = list.get(i).getExpiredDate();
        }
        table.setModel(new DefaultTableModel(renterTable, column));
        for(int i = 0; i < list.size(); i++){
            table.setRowHeight(i, 20);
        }
    }
    
    public void fillBookFromSelectedRow(){
        int row = tablePub.getSelectedRow();
        if(row != -1){
            rentedBookField.setText(tablePub.getModel().getValueAt(row, 0).toString());
        }
    }

    public void fillRenterFromSelectedRow(){
        int row = table.getSelectedRow();
        if (row != -1 ) {
            dateString = table.getModel().getValueAt(row, 7).toString();
            dateArray = dateString.split("/");
            year =  Integer.parseInt(dateArray[2]);
            month =  Integer.parseInt(dateArray[1]);
            day =  Integer.parseInt(dateArray[0]);
            int typeIndex = 0;
            String typeTable = table.getModel().getValueAt(row, 5).toString();
            for (String s : types) {
                if (!Objects.equals(s, typeTable)) {
                    typeIndex++;
                } else {
                    break;
                }
            } 
            if(row >= 0){
                firstNameField.setText(table.getModel().getValueAt(row, 1).toString());
                nameField.setText(table.getModel().getValueAt(row, 2).toString());
                
                rentedBookField.setText(table.getModel().getValueAt(row, 4).toString());
                typeComboBox.setSelectedIndex(typeIndex);
                quantityField.setText(table.getModel().getValueAt(row, 6).toString());
                dateModel.setDate(year, month -1, day);
                dateModel.setSelected(true);

                deleteRenterBtn.setEnabled(true);
                editRenterBtn.setEnabled(true);
                addRenterBtn.setEnabled(false);
            }
        }
    }
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    
    private boolean validFirstName(){
        String firstName = firstNameField.getText();
        if(firstName == null || firstName.trim().isEmpty()){
            rentedBookField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
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
  
//    private boolean validID(){
//        String id = idField.getText();
//        if(id == null || id.trim().isEmpty()){
//            idField.requestFocus();
//            showMessage("Không được bỏ trống");
//            return false;
//        }
//        if(compareByID(renter, renter)) {
//            if(!compareByName(renter, renter)){
//                showMessage("ID không được trùng nhau");
//                return false;
//            }
//        }
//        return true;
//    }
    
    public static boolean compareByID(Renter r1, Renter r2) {
        if(r1.getCode().equals(r2.getCode())){
            return true;
        }
        return false;
    }
    
    public static boolean compareByName(Renter r1, Renter r2) {
        if(r1.getName().equals(r2.getName())){
            return true;
        }
        return false;
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
    
    private boolean validQuantity(){
        String quantity = quantityField.getText();
        if(quantity == null || quantity.trim().isEmpty()){
            rentedBookField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    public boolean checkPublication(){
        List<Publication> list = pubDao.getListPublication();
        for(Publication p : list){
            if(!rentedBookField.getText().equals(p.getName())){
                return false;
            }
        }
        return true;
    }
    
    public boolean checkQuantityToRent(){
        List<Publication> list = pubDao.getListPublication();
        for(Publication p : list){
            if(rentedBookField.getText().equals(p.getName())){
                if(Integer.parseInt(quantityField.getText()) - p.getQuantity() > 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public Integer quantityLeft(){
        int count = 0;
        List<Publication> list = pubDao.getListPublication();
        for(Publication p : list){
            if(rentedBookField.getText().equals(p.getName())){
                count = p.getQuantity() - Integer.parseInt(quantityField.getText());
            }
        }
        return count;
    }
    
    public Renter getRenterInfo(){
        if(!validName() || !validRentedBook() || !validFirstName() || !validQuantity()){
            return null;
        }
        try{
            Renter renter = new Renter();
            
            dateValue = (Date) datePicker.getModel().getValue();
            dateString = DateFomatterUtil.valueToString(dateValue);
            renter.setFirstName(firstNameField.getText().trim());
            renter.setName(nameField.getText().trim());
            renter.setCodeByID();
            renter.setRentedBook(rentedBookField.getText().trim());
            renter.setType(typeString.trim());
            renter.setQuantity(Integer.parseInt(quantityField.getText().trim()));
            renter.setExpiredDate(dateString);
            return renter;
        } catch (Exception e){
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public void clear(){
        firstNameField.setText("");
        nameField.setText("");
        idField.setText("");
        rentedBookField.setText("");
        typeComboBox.setSelectedIndex(0);
        quantityField.setText("");
        dateModel.setSelected(false);
        
        editRenterBtn.setEnabled(false);
        deleteRenterBtn.setEnabled(false);
        sortByNameRenterBtn.setEnabled(true);
        addRenterBtn.setEnabled(true);
        table.clearSelection();
    }
    
    public void showRenter(Renter renter){
        firstNameField.setText(renter.getFirstName());
        nameField.setText(renter.getName());
        idField.setText(renter.getCode());
        rentedBookField.setText(renter.getRentedBook());
        quantityField.setText("" + renter.getQuantity());
        expiredDateField.setText(renter.getExpiredDate());
    }

    public String getSearchRentedBookField() {
        return rentedBookField.getText();
    }



    public String getRentedBookField(){
        return rentedBookField.getText();
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
    
    public void addFillBookFromSelectedRow(ListSelectionListener listener){
        tablePub.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addRentedBookFieldSearch(DocumentListener listener){
        rentedBookField.getDocument().addDocumentListener(listener);
    }
    
    public String getSearchRentedBookField(){
        return rentedBookField.getText();
    }
    
//    public void setRentedBookFieldOnChangeListener(DocumentListener listener){
//        rentedBookField.getDocument().addDocumentListener(listener);
//    }
            
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
