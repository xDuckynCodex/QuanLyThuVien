/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.views;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import quanlythuvien.components.DatePickerPanel;
import quanlythuvien.components.ListRentedBookScroll;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.entities.RentedBook;
import quanlythuvien.entities.Renter;

/**
 *
 * @author Admin
 */
public class RenterView extends JFrame {
    public void setPublicationDao(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }
    //data
    private PublicationDao publicationDao;

    public void setRenterDao(RenterDao renterDao) {
        this.renterDao = renterDao;
    }

    private RenterDao renterDao;
    public List<RentedBook> getRentedBookList() {
        return rentedBookList;
    }

    public void setRentedBookList(List<RentedBook> rentedBookList) {
        this.rentedBookList = rentedBookList;
    }
    public void addBookToRentedBookList(RentedBook rentedBook) {
        if (rentedBookList == null) {
            rentedBookList = new ArrayList<>();
        }
        if (rentedBook != null && this.validDate()) {
            rentedBookList.add(rentedBook);
        } else {
            showMessage("Ngày hết hạn không hợp lệ");
        }
    }
    private List<RentedBook> rentedBookList;
    // button
    private JButton addRenterBtn;
    private JButton editRenterBtn;
    private JButton deleteRenterBtn;
    private JButton sortByNameRenterBtn;
    private JButton clearBtn;
    private JButton transferToManageViewBtn;
    private JButton transferToPayerViewBtn;
    private JButton addBookBtn;
    private JButton removeBookBtn;

//    private InfoView infoView;

    // label
    private JLabel firstNameLabel;
    private JLabel nameLabel;
    private JLabel idLabel;
    private JLabel rentedBookLabel;
    private JLabel tableLabel;
    private JLabel quantityLabel;
    private JLabel expiredDateLabel;
    
    // field nhap
    private JTextField firstNameField;
    private JTextField nameField;
    private JTextField codeField;
    private JTextField rentedBookField;
    private JTextField quantityField;
    
    //date picker
    private DatePickerPanel datePickerPanel;

    //scroll pane
    private ListRentedBookScroll listRentedBookScroll;

    // bang
    private JScrollPane renterSrcollPane;
    private JScrollPane resultTableScrollPane;
    private JTable renterTable;
    private JTable resultTable;
    private TableFilterHeader tableFilter;
    
    // cot
    private final String[] column = new String[] {"STT","Họ và tên đệm", "Tên", "ID", "Ngày hết hạn"};

    private final Object data = new Object[][] {};
    private int dataPubRows = 0;
    private Object[][] dataPub;
    private final String[] columnPub = new String[] {"Ấn phẩm"};
    
    public void initComponent(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khoi tao phim
        addRenterBtn = new JButton("Thêm người mượn");
        editRenterBtn = new JButton("Sửa thông tin");
        deleteRenterBtn = new JButton("Xoá người mượn");
        sortByNameRenterBtn = new JButton("Sắp xếp theo tên");
        clearBtn = new JButton("Xoá");
        transferToManageViewBtn = new JButton("Chuyển sang quản lý ấn phẩm");
        transferToPayerViewBtn = new JButton("Chuyển sang quản lý người trả");
        addBookBtn = new JButton("+");
        removeBookBtn = new JButton("-");
        removeBookBtn.setEnabled(false);
        
        
        //date time picker
        datePickerPanel = new DatePickerPanel();
        
        // khởi tạo label
        firstNameLabel = new JLabel("Họ và tên đệm người mượn");
        nameLabel = new JLabel("Tên người mượn");
        idLabel = new JLabel("ID");
        rentedBookLabel = new JLabel("Sách đã mượn");
        quantityLabel = new JLabel("Số lượng");
        expiredDateLabel = new JLabel("Ngày hết hạn");
        tableLabel = new JLabel("Danh sách người mượn ấn phẩm");
        tableLabel.setFont(new Font(tableLabel.getFont().getName(),
                Font.PLAIN, 40));
        
        // khởi tạo field
        firstNameField = new JTextField(20);
        nameField = new JTextField(20);
        codeField = new JTextField(20);
        codeField.setEnabled(false);
        rentedBookField = new JTextField(20);
        quantityField = new JTextField(20);
        
        // khởi tạo table
        renterTable = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        renterTable.getTableHeader().setReorderingAllowed(false);
        tableFilter = new TableFilterHeader(renterTable, AutoChoices.ENABLED);
        
        resultTable = new JTable(){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        //scroll pane
        listRentedBookScroll = new ListRentedBookScroll();
        listRentedBookScroll.setRenterView(this);

        // cài đặt bảng
        renterSrcollPane = new JScrollPane();
        resultTableScrollPane = new JScrollPane();
        
        resultTable.setModel(new DefaultTableModel(dataPub, columnPub));
        resultTable.setFont(new Font(resultTable.getFont().getName(), Font.PLAIN, 15));
        resultTableScrollPane.setViewportView(resultTable);
        resultTableScrollPane.setPreferredSize(new Dimension(225, 100));
        resultTableScrollPane.setVisible(false);
               
        renterTable.setModel(new DefaultTableModel((Object[][]) data, column));
        renterTable.setFont(new Font(renterTable.getFont().getName(), Font.PLAIN, 15));
        renterTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        renterSrcollPane.setViewportView(renterTable);
        renterSrcollPane.setPreferredSize(new Dimension(1350, 750));
        
        // tạo layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        
        panel.setSize(1900, 1050);
        panel.setLayout(layout);

        panel.add(resultTableScrollPane);


        panel.add(addRenterBtn);
        panel.add(editRenterBtn);
        panel.add(deleteRenterBtn);
        panel.add(sortByNameRenterBtn);
        panel.add(transferToManageViewBtn);
        panel.add(transferToPayerViewBtn);
        panel.add(clearBtn);
        panel.add(addBookBtn);
        panel.add(removeBookBtn);
        
        panel.add(firstNameLabel);
        panel.add(nameLabel);
        panel.add(idLabel);
        panel.add(rentedBookLabel);
        panel.add(quantityLabel);
        panel.add(expiredDateLabel);
        panel.add(tableLabel);
        
        panel.add(firstNameField);
        panel.add(nameField);
        panel.add(codeField);
        panel.add(rentedBookField);
        panel.add(quantityField);

        panel.add(listRentedBookScroll);
        panel.add(datePickerPanel);
        panel.add(renterSrcollPane);

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
        layout.putConstraint(SpringLayout.WEST, expiredDateLabel, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, expiredDateLabel, 190,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, resultTableScrollPane, 180,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, resultTableScrollPane, 150,
                SpringLayout.NORTH,
                panel);
        
        layout.putConstraint(SpringLayout.WEST, firstNameField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, firstNameField, 40, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, nameField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 70, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, codeField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, codeField, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, rentedBookField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, rentedBookField, 130, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityField, 180, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityField, 160, SpringLayout.NORTH, panel);
   
        layout.putConstraint(SpringLayout.WEST, addRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addRenterBtn, 280,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editRenterBtn, 330,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deleteRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deleteRenterBtn, 380,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByNameRenterBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameRenterBtn, 430,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 480,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferToManageViewBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferToManageViewBtn, 600, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, transferToPayerViewBtn, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferToPayerViewBtn, 650, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addBookBtn, 420,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, addBookBtn, 140,
                SpringLayout.NORTH,
                panel);
        layout.putConstraint(SpringLayout.WEST, removeBookBtn, 420,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, removeBookBtn, 180,
                SpringLayout.NORTH,
                panel);

        layout.putConstraint(SpringLayout.WEST, listRentedBookScroll, 10,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, listRentedBookScroll, 220,
                SpringLayout.NORTH,
                panel);
        
        layout.putConstraint(SpringLayout.EAST, renterSrcollPane, 0, SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, renterSrcollPane, 150, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tableLabel, 1050, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tableLabel, 20, SpringLayout.NORTH, panel);
        
        //datepicker
        layout.putConstraint(SpringLayout.WEST, datePickerPanel, 180, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, datePickerPanel, 180,
                SpringLayout.NORTH, panel);
        
        this.add(panel);
        this.pack();
        this.setTitle("Danh sách người mượn ấn phẩm");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    public RenterView(){
        initComponent();
    }
    
    public void showResultView(String searchText){
        List<Publication> list = publicationDao.searchByName(searchText);
        dataPubRows = list.size();
        dataPub = new Object[dataPubRows][1];
        for(int i = 0; i < dataPubRows; i++){
            dataPub[i][0] = list.get(i).getName();
        }
        resultTable.setModel(new DefaultTableModel(dataPub, columnPub));
        for(int i = 0; i < list.size(); i++){
            resultTable.setRowHeight(i, 20);
        }
        resultTableScrollPane.setVisible(true);
    }

    public void hideTablePub(){
        resultTableScrollPane.setVisible(false);
    }
    
    public void showListRenter(List<Renter> list){
        Object [][] data = new Object[list.size()][5];
        for(int i = 0; i < list.size(); i++){
            data[i][0] = i + 1;
            data[i][1] = list.get(i).getFirstName();
            data[i][2] = list.get(i).getName();
            data[i][3] = list.get(i).getCode();
            data[i][4] = list.get(i).getExpiredDate();
        }
        this.renterTable.setModel(new DefaultTableModel(data, column));
        for(int i = 0; i < list.size(); i++){
            this.renterTable.setRowHeight(i, 20);
        }
        renterTable.clearSelection();
    }
    
    public void fillBookFromSelectedRow(){
        int row = resultTable.getSelectedRow();
        if(row != -1){
            rentedBookField.setText(resultTable.getModel().getValueAt(row, 0).toString());
        }
        resultTableScrollPane.setVisible(false);
    }

    public void fillRenterFromSelectedRow(){
        int row = renterTable.getSelectedRow();
        if (row != -1) {
            Renter renter = renterDao.getByCode(renterTable.getModel().getValueAt(row, 3).toString());
            firstNameField.setText(renter.getFirstName());
            nameField.setText(renter.getName());
            codeField.setText(renter.getCode());
            datePickerPanel.showDate(renter.getExpiredDate());
            setRentedBookList(renter.getRentedBookList());
            setListToListRentedBookScroll();
            clearBook();

            deleteRenterBtn.setEnabled(true);
            editRenterBtn.setEnabled(true);
            addRenterBtn.setEnabled(false);
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

    private boolean validRentedBook(){
        String rentedBook = rentedBookField.getText();
        if(rentedBook == null || rentedBook.trim().isEmpty()){
            rentedBookField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }

    private boolean validQuantity(Publication publication){
        String quantity = quantityField.getText();
        if(quantity == null || quantity.trim().isEmpty()){
            rentedBookField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        } else if(Integer.parseInt(quantity) <= 0
                ||  Integer.parseInt(quantity) > (publication.getQuantity() - publication.getRented())) {
            showMessage("Số lượng không hợp lệ");
            return false;
        }
        return true;
    }

    public boolean validDate() {
        Date date = datePickerPanel.getDateValue();
        Date dateNow = new Date();
        return dateNow.before(date);
    }

    private RentedBook checkExist(Publication publication) {
        for (RentedBook rentedBook : rentedBookList) {
            if (Objects.equals(publication.getCode(),
                    rentedBook.getPublication().getCode())) {
                return rentedBook;
            }
        }
        return null;
    }
    
    public boolean checkPublication(){
        List<Publication> list = publicationDao.getListPublication();
        for(Publication p : list){
            if(!rentedBookField.getText().equals(p.getName())){
                return true;
            }
        }
        return false;
    }

    public boolean checkQuantityToRent(){
        List<Publication> list = publicationDao.getListPublication();
        for(Publication p : list){
            if(rentedBookField.getText().equals(p.getName())){
                if(Integer.parseInt(quantityField.getText()) - p.getQuantity() > 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean checkMaximum(){
        List<RentedBook> list = this.getRentedBookList();
        int count = 0;
        for(RentedBook r : list){
            count += r.getQuantity();
        }
        return count <= 5;
    }

    public Renter getNewRenterInfo(){
        if(!validName() || !validFirstName()){
            return null;
        }
        try{
            Renter renter = new Renter();
            renter.setFirstName(firstNameField.getText().trim());
            renter.setName(nameField.getText().trim());
            renter.setCodeByID();
            renter.setRentedBookList(rentedBookList);
            renter.setExpiredDate(datePickerPanel.getDateString());
            return renter;
        } catch (Exception e){
            showMessage(e.getMessage());
        }
        return null;
    }

    public Renter getEditRenterInfo(){
        if(!validName() || !validFirstName()){
            return null;
        }
        try{
            Renter renter = new Renter();
            renter.setFirstName(firstNameField.getText().trim());
            renter.setName(nameField.getText().trim());
            renter.setCode(codeField.getText().trim());
            renter.setExpiredDate(datePickerPanel.getDateString());
            renter.setRentedBookList(rentedBookList);
            return renter;
        } catch (Exception e){
            showMessage(e.getMessage());
        }
        return null;
    }
    
    public void clear(){
        firstNameField.setText("");
        nameField.setText("");
        codeField.setText("");
        rentedBookField.setText("");
        quantityField.setText("");
        datePickerPanel.clearDate();
        
        editRenterBtn.setEnabled(false);
        deleteRenterBtn.setEnabled(false);
        sortByNameRenterBtn.setEnabled(true);
        addRenterBtn.setEnabled(true);
        renterTable.clearSelection();
        rentedBookList = new ArrayList<>();
        setListToListRentedBookScroll();
    }

    public void clearBook() {
        rentedBookField.setText("");
        quantityField.setText("");
//        datePickerPanel.clearDate();
    }
    
    public RentedBook getRentedBookInfo() {
        Publication publication =
                publicationDao.searchByName(rentedBookField.getText()).getFirst();
        if (checkExist(publication) == null) {
            if (validQuantity(publication) && validRentedBook() && validDate()) {
                publication.setRented(publication.getRented()+Integer.parseInt(quantityField.getText()));

                RentedBook rentedBook = new RentedBook();
                rentedBook.setPublication(publication);
                rentedBook.setQuantity(Integer.parseInt(quantityField.getText()));

                return rentedBook;
            }
            return null;
        } else {
            RentedBook rentedBook = checkExist(publication);
            rentedBookList.remove(rentedBook);
            publication.setRented(publication.getRented() -
                    rentedBook.getQuantity());
            if (validQuantity(publication) && validRentedBook() && validDate()) {
                publication.setRented(publication.getRented() + Integer.parseInt(quantityField.getText()));

                rentedBook.setQuantity(Integer.parseInt(quantityField.getText()));
                rentedBook.setPublication(publication);

                return rentedBook;
            }
            return null;
        }
    }

    public void setListToListRentedBookScroll() {
        listRentedBookScroll.setRentedBookList(rentedBookList);
    }

    public void removeBookInRentedBook() {
        String name = rentedBookField.getText();
        if(rentedBookList.isEmpty()){
            showMessage("Không được xoá sách đang mượn");
        }
        for (int i = 0; i < rentedBookList.size(); i++) {
            if (Objects.equals(name,
                rentedBookList.get(i).getPublication().getName())) {
                rentedBookList.remove(i);
            }
        }
        setRentedBookList(rentedBookList);
    }

    public void setEnableRemoveBookBtn(boolean bool) {
        removeBookBtn.setEnabled(bool);
    }

    public void fillRentedBook(RentedBook rentedBook) {
        rentedBookField.setText(rentedBook.getPublication().getName());
        quantityField.setText("" +rentedBook.getQuantity());
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
        transferToManageViewBtn.addActionListener(listener);
    }
    
    public void addTransferToPayerViewListener(ActionListener listener){
        transferToPayerViewBtn.addActionListener(listener);
    }
    
    public void addFillRenterFromSelectedRow(ListSelectionListener listener) {
        renterTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addFillBookFromSelectedRow(ListSelectionListener listener){
        resultTable.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addRentedBookFieldSearch(DocumentListener listener){
        rentedBookField.getDocument().addDocumentListener(listener);
    }

    public void addNewBookToRentedBookList(ActionListener listener) {
        addBookBtn.addActionListener(listener);
    }

    public void addRemoveBookInRentedBookList(ActionListener listener) {
        removeBookBtn.addActionListener(listener);
    }
}