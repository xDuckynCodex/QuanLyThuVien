package quanlythuvien.views;


import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.DateFomatterUtil;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Objects;

// Thiết kế views của giao diện quản lý
public class ManagerView extends JFrame implements ActionListener, ListSelectionListener {
    //frame size
    private double hightFrame;
    private double widthFrame;
    // nút
    private  JButton addPublicationBtn;
    private JButton editPublicationBtn;
    private JButton deletePublicationBtn;
    private JButton clearBtn;
    private JButton sortByNameBtn;
    private JButton sortByPriceBtn;
    // label nhập
    private JLabel nameLabel;
    private JLabel codeLabel;
    private JLabel publisherLabel;
    private  JLabel authorLabel;
    private JLabel publishedDateLabel;
    private JLabel quantityLabel;
    private  JLabel priceLabel;
    private JLabel typeLabel;
    private JLabel tableLabel;
    private JLabel searchLabel;

    // field nhập
    private JTextField nameField;
    private JTextField codeField;
    private JTextField publisherField;
    private JTextField authorField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField searchField;

    //Date picker
    private UtilDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private Date dateValue;
    private String dateString;
    private String[] dateArray;
    private int year, month, day;

    // Combobox Type
    private JComboBox typeComboBox;
    private String typeString;
    private String[] types = {"Chose type","Book", "Magazine", "Novel",
            "Newspapers"};

    // bảng
    private  JScrollPane jScrollTable;
    private  JTable table;
    private TableFilterHeader tableFilter;

    // cột của table
    private String [] columnNames = new String [] {
            "STT", "Tên ấn phẩm", "Mã số", "NXB", "Tác giả", "NgXB", "SL",
            "Giá", "Thể loại"};
    private Object data = new Object [][] {};


    public ManagerView() {
        initComponent();
    }


    public void initComponent() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        hightFrame = getContentPane().getSize().getHeight();
        widthFrame = getContentPane().getSize().getWidth();

        // khởi tạo phim chức năng
        addPublicationBtn = new JButton("Thêm");
        editPublicationBtn = new JButton("Sửa");
        deletePublicationBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");
        sortByNameBtn = new JButton("Sort by Name");
        sortByPriceBtn = new JButton("Sort by price");

        // khởi tạo label
        nameLabel = new JLabel("Tên ấn phẩm");
        codeLabel = new JLabel("Mã sản phẩm");
        publisherLabel = new JLabel("Nhà xuất bản");
        authorLabel = new JLabel("Tác giả");
        publishedDateLabel = new JLabel("Ngày xuất bản");
        quantityLabel = new JLabel("Số lượng");
        priceLabel = new JLabel("Giá");
        typeLabel = new JLabel("Thể loại");
        tableLabel = new JLabel("Bảng thống kê ấn phẩm");
        tableLabel.setFont(new Font(tableLabel.getFont().getName(),
                Font.PLAIN, 40));
        searchLabel = new JLabel("Tìm kiếm ấn phẩm:");

        //date time picker
        dateModel = new UtilDateModel();
        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);

        //khởi tạo field nhập
        nameField = new JTextField(20);
        codeField = new JTextField(20);
        publisherField = new JTextField(20);
        authorField = new JTextField(20);
        quantityField = new JTextField(20);
        priceField = new JTextField(20);
        searchField = new JTextField(30);

        // ComboBo
        typeComboBox = new JComboBox(types);

        // Tạo bảng lưu dữ liệu
        jScrollTable = new JScrollPane();
        table = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.getTableHeader().setReorderingAllowed(false);
        tableFilter = new TableFilterHeader(table, AutoChoices.ENABLED);

        //cài đặt bảng
        table.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        table.setFont(new Font(table.getFont().getName(), Font.PLAIN, 15));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollTable.setViewportView(table);
        jScrollTable.setPreferredSize(new Dimension(1350, 780));

        // Tạo layout của giao diện
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1920, 1080);
        panel.setLayout(layout);
        panel.add(jScrollTable);

        panel.add(addPublicationBtn);
        panel.add(editPublicationBtn);
        panel.add(deletePublicationBtn);
        panel.add(clearBtn);
        panel.add(sortByNameBtn);
        panel.add(sortByPriceBtn);

        panel.add(nameLabel);
        panel.add(codeLabel);
        panel.add(publisherLabel);
        panel.add(authorLabel);
        panel.add(publishedDateLabel);
        panel.add(quantityLabel);
        panel.add(priceLabel);
        panel.add(typeLabel);
        panel.add(tableLabel);
        panel.add(searchLabel);

        panel.add(nameField);
        panel.add(codeField);
        panel.add(publisherField);
        panel.add(authorField);
        panel.add(datePicker);
        panel.add(quantityField);
        panel.add(priceField);
        panel.add(searchField);
        panel.add(typeComboBox);


        //set vị trí các phần thử của giao diện.
        // label
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 10,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, codeLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, codeLabel, 40,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publisherLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publisherLabel, 70,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, authorLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, authorLabel, 100,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publishedDateLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publishedDateLabel, 130,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityLabel, 160,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceLabel, 190,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeLabel, 220,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tableLabel, 1050,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tableLabel, 20,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchLabel, 570,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchLabel, 100,
                SpringLayout.NORTH, panel);
        // field
        layout.putConstraint(SpringLayout.WEST, nameField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 10,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, codeField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, codeField, 40,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publisherField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publisherField, 70,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, authorField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, authorField, 100,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityField, 160,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceField, 190,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeComboBox, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeComboBox, 220,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, searchField, 700,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 100,
                SpringLayout.NORTH, panel);
        // button
        layout.putConstraint(SpringLayout.WEST, addPublicationBtn, 30,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addPublicationBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editPublicationBtn, 130,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editPublicationBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deletePublicationBtn, 230,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deletePublicationBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 330,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByNameBtn, 570,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByNameBtn, 950,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, sortByPriceBtn, 700,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, sortByPriceBtn, 950,
                SpringLayout.NORTH, panel);
        // table
        layout.putConstraint(SpringLayout.EAST, jScrollTable, 0,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollTable, 150,
                SpringLayout.NORTH, panel);
        //datepicker
        layout.putConstraint(SpringLayout.WEST, datePicker, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, datePicker, 127,
                SpringLayout.NORTH, panel);

        //
        this.add(panel);
        this.pack();
        // set jFrame
        this.setTitle("Manager View");
//        this.setSize(1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
//        this.setResizable(false);
    }

    public String getSearchField() {
        return searchField.getText();
    }

    public void showListPublications(List<Publication> list) {
        int size = list.size();
        Object [][] publicationsTable = new Object[size][9];
        for (int i = 0; i < size; i++) {
            publicationsTable[i][0] = i + 1;
            publicationsTable[i][1] = list.get(i).getName();
            publicationsTable[i][2] = list.get(i).getCode();
            publicationsTable[i][3] = list.get(i).getPublisher();
            publicationsTable[i][4] = list.get(i).getAuthor();
            publicationsTable[i][5] = list.get(i).getPublishedDate();
            publicationsTable[i][6] = list.get(i).getQuantity();
            publicationsTable[i][7] = list.get(i).getPrice();
            publicationsTable[i][8] = list.get(i).getType();
        }
        table.setModel(new DefaultTableModel(publicationsTable, columnNames));
        for (int i = 0; i < size; i++) {
            table.setRowHeight(i, 20);
        }
    }

    public void fillPublicationFromSelectedRow() {
        int row = table.getSelectedRow();
        if (row != -1 ) {
            dateString = table.getModel().getValueAt(row, 5).toString();
            dateArray = dateString.split("/");
            year =  Integer.parseInt(dateArray[2]);
            month =  Integer.parseInt(dateArray[1]);
            day =  Integer.parseInt(dateArray[0]);

            int typeIndex = 0;
            String typeTable = table.getModel().getValueAt(row, 8).toString();
            for (String s : types) {
                if (!Objects.equals(s, typeTable)) {
                    typeIndex++;
                } else {
                    break;
                }
            }

            if (row >= 0) {
                nameField.setText(table.getModel().getValueAt(row, 1).toString());
                codeField.setText(table.getModel().getValueAt(row, 2).toString());
                publisherField.setText(table.getModel().getValueAt(row, 3).toString());
                authorField.setText(table.getModel().getValueAt(row, 4).toString());
                dateModel.setDate(year, month -1, day);
                dateModel.setSelected(true);
                quantityField.setText(table.getModel().getValueAt(row, 6).toString());
                priceField.setText(table.getModel().getValueAt(row, 7).toString());
                typeComboBox.setSelectedIndex(typeIndex);
                //enable
                editPublicationBtn.setEnabled(true);
                deletePublicationBtn.setEnabled(true);
                //disable
                addPublicationBtn.setEnabled(false);
            }
        }
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    private boolean validName(){
        String name = nameField.getText();
        if(name == null || name.trim().isEmpty()){
            nameField.requestFocus();
            showMessage("Không được bỏ trống name");
            return false;
        }
        return true;
    }
    
    private boolean validType(){
        typeString =
                String.valueOf(typeComboBox.getItemAt(typeComboBox.getSelectedIndex()));
        String type = "";
        if (typeComboBox.getSelectedIndex() >= 1) {
            type = typeString;
        }
        if(type == null || type.trim().isEmpty()){
            showMessage("Type không hợp lệ");
            return false;
        }
        return true;
    }
    
    private boolean validCode(){
        String code = codeField.getText();
        if(code == null || code.trim().isEmpty()){
            codeField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    private boolean validPublisher(){
        String publisher = publisherField.getText();
        if(publisher == null || publisher.trim().isEmpty()){
            publisherField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    private boolean validAuthor(){
        String author = authorField.getText();
        if(author == null || author.trim().isEmpty()){
            authorField.requestFocus();
            showMessage("Không được bỏ trống");
            return false;
        }
        return true;
    }
    
    private boolean validPrice(){
        try {
            double price = Double.parseDouble(priceField.getText().trim());
            if(price < 0){
                priceField.requestFocus();
                showMessage("Không hợp lệ");
                return false;
            }
        } catch (Exception e){
            priceField.requestFocus();
            showMessage("Giá không hợp lệ");
            return false;
        }
        return true;
    }
    
    private boolean validQuantity(){
        try {
            int quantity = Integer.parseInt(quantityField.getText().trim());
            if(quantity < 0){
                quantityField.requestFocus();
                showMessage("Không hợp lệ");
                return false;
            }
        } catch (Exception e){
            quantityField.requestFocus();
            showMessage("Số lượng không hợp lệ");
            return false;
        }
        return true;
    }
    
    public Publication getPublicationInfo(){
        if(!validName() || !validType() || !validCode() ||
                !validPublisher() || !validAuthor() || !validPrice() || !validQuantity()){
            return null;
        }
        try{
            Publication publication = new Publication();
            dateValue = (Date) datePicker.getModel().getValue();
            dateString = DateFomatterUtil.valueToString(dateValue);
            typeString =
                    String.valueOf(typeComboBox.getItemAt(typeComboBox.getSelectedIndex()));

            publication.setName(nameField.getText().trim());
            publication.setCode(codeField.getText().trim());
            publication.setPublisher(publisherField.getText().trim());
            publication.setAuthor(authorField.getText().trim());
            publication.setType(typeString.trim());
            publication.setPublishedDate(dateString);
            publication.setPrice(Double.parseDouble(priceField.getText().trim()));
            publication.setQuantity(Integer.parseInt(quantityField.getText().trim()));

            return publication;
        } catch (Exception e){
            showMessage(e.getMessage());
        }
        return null;
    }

    public void clearPublication(){
        nameField.setText("");
        codeField.setText("");
        publisherField.setText("");
        authorField.setText("");
        dateModel.setSelected(false);
        priceField.setText("");
        quantityField.setText("");
        typeComboBox.setSelectedIndex(0);

        editPublicationBtn.setEnabled(false);
        deletePublicationBtn.setEnabled(false);
        addPublicationBtn.setEnabled(true);
        table.clearSelection();
    }

    public void showPublication(Publication publication){
        nameField.setText(publication.getName());
        codeField.setText(publication.getCode());
        publisherField.setText(publication.getPublisher());
        authorField.setText(publication.getAuthor());
        priceField.setText("" + publication.getPrice());
        quantityField.setText("" + publication.getQuantity());
    }
    @Override
    public void actionPerformed(ActionEvent e) {    }

    @Override
    public void valueChanged(ListSelectionEvent e) {    }

    //listener
    public void addAddPublicationListener(ActionListener listener) {
        addPublicationBtn.addActionListener(listener);
    }
    
    public void addEditPublicationListener(ActionListener listener) {
        editPublicationBtn.addActionListener(listener);
    }
    
    public void addDeletePublicationListener(ActionListener listener) {
        deletePublicationBtn.addActionListener(listener);
    }

    public void addClearPublicationListener(ActionListener listener){
        clearBtn.addActionListener(listener);
    }

    public void addFillPublicationFromSelectedRow(ListSelectionListener listener) {
        table.getSelectionModel().addListSelectionListener(listener);

    }
    public void addSortByName(ActionListener listener) {
        sortByNameBtn.addActionListener(listener);
    }

    public void addSortByPrice(ActionListener listener) {
        sortByPriceBtn.addActionListener(listener);
    }

    public void addSearchByName(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }

    public static void main(String[] args) {
        ManagerView mc = new ManagerView();
        mc.setVisible(true);
    }
}
