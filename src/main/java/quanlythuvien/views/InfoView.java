package quanlythuvien.views;

import quanlythuvien.components.*;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FontUtil;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Objects;

import quanlythuvien.utils.DateFomatterUtil;

public class InfoView extends JFrame {
    public InfoView() {
        initComponent();
    }
    public InputField name, code, publisher, author, quantity, price;
    private ButtonComp addBtn, editBtn, deleteBtn, exitBtn, browseBtn;
    private JLabel title;
    private DatePickerPanel datePickerPanel;

    public Card card;
    private DropDown typeMenu;

    public Publication getPublication() {
        return publication;
    }
    private Publication publication;

    private String imgPath = ".\\public\\images\\ph.png";
    private final int north = 50;
    private final int west = 250;
    public void initComponent() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // label
        title = new JLabel("Thông tin đối tượng");
        title.setFont(FontUtil.Bitter(30));
        //input field
        name = new InputField("Tên sản phẩm");
        code = new InputField("Mã sản phẩm");
        code.disableField();
        publisher = new InputField("Nhà xuất bản");
        author = new InputField("Tên tác giả");
        quantity = new InputField("Số lượng");
        price = new InputField("Giá sản phẩm");

        datePickerPanel = new DatePickerPanel("Ngày xuât bản");
        typeMenu = new DropDown();
        //button
        addBtn = new ButtonComp("Thêm");
        editBtn =new ButtonComp("Sửa");
        deleteBtn = new ButtonComp("Xóa");
        exitBtn = new ButtonComp("Exit");
        browseBtn = new ButtonComp("Browse");

        card = new Card();

        //giao dien chinh
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        //add element to panel
        panel.add(title);

        panel.add(name);
        panel.add(code);
        panel.add(publisher);
        panel.add(author);
        panel.add(quantity);
        panel.add(price);
        panel.add(datePickerPanel);
        panel.add(typeMenu);

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(exitBtn);
        panel.add(browseBtn);

        panel.add(card);

        //set vi tri
        layout.putConstraint(SpringLayout.WEST, card, 40, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, card, north, SpringLayout.NORTH,
                panel);
        //title
        layout.putConstraint(SpringLayout.WEST, title, 200, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.NORTH,
                panel);
        //name
        layout.putConstraint(SpringLayout.WEST, name, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, name, north + 10,
                SpringLayout.NORTH,
                panel);
        //code
        layout.putConstraint(SpringLayout.WEST, code, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, code, north + 40,
                SpringLayout.NORTH,
                panel);
        //publisher
        layout.putConstraint(SpringLayout.WEST, publisher, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, publisher, north + 40 * 2,
                SpringLayout.NORTH,
                panel);
        //author
        layout.putConstraint(SpringLayout.WEST, author, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, author, north + 40 * 3,
                SpringLayout.NORTH,
                panel);
        //quantity
        layout.putConstraint(SpringLayout.WEST, quantity, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, quantity, north + 40 * 4,
                SpringLayout.NORTH,
                panel);
        //price
        layout.putConstraint(SpringLayout.WEST, price, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, price, north + 40 * 5,
                SpringLayout.NORTH,
                panel);
        //date picker
        layout.putConstraint(SpringLayout.WEST, datePickerPanel, west,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, datePickerPanel,
                north + 40 * 6,
                SpringLayout.NORTH,
                panel);
        //type menu
        layout.putConstraint(SpringLayout.WEST, typeMenu, west,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, typeMenu,
                north + 40 * 7,
                SpringLayout.NORTH,
                panel);
        //button
        //addBtn
        layout.putConstraint(SpringLayout.WEST, addBtn, west,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
        //editBtn
        layout.putConstraint(SpringLayout.WEST, editBtn, west + 80,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
        //deleteBtn
        layout.putConstraint(SpringLayout.WEST, deleteBtn, west + 80*2,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
        //exitBtn
        layout.putConstraint(SpringLayout.WEST, exitBtn, west + 80 * 3,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, exitBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
//        browseBtn
        layout.putConstraint(SpringLayout.WEST, browseBtn, 100,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, browseBtn,
                north + 40 * 7,
                SpringLayout.NORTH,
                panel);
        //set frame
        this.add(panel);
        this.pack();
        this.setTitle("Thông tin đối tượng");
        this.setSize(new Dimension(600,450));
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void setEditInfoView(Publication publication) {
        name.setField(publication.getName());
        code.setField(publication.getCode());
        publisher.setField(publication.getPublisher());
        author.setField(publication.getAuthor());
        quantity.setField(publication.getQuantity()+"");
        price.setField(publication.getPrice() + "");
        datePickerPanel.showDate(publication.getPublishedDate());
        typeMenu.setType(publication.getType());
        card.setImageLabel(publication.getImgPath());
        this.setVisible(true);
    }
    
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void showInfoView() {
        this.setVisible(true);
    }
    

    public Publication getNewInfoPublication() {
        if(!validAuthor() || !validName() || !validPrice() || !validPublisher() || !validQuantity() || !validDate() || !validType()){
            return null;
        } 
        try{
            Publication publication = new Publication();
            publication.setName(name.getTextField());
            publication.setCodeById();
            publication.setAuthor(author.getTextField());
            publication.setPrice(Double.parseDouble(price.getTextField()));
            publication.setPublisher(publisher.getTextField());
            publication.setPublishedDate(datePickerPanel.getDateString());
            publication.setType(typeMenu.getTypeString());
            publication.setQuantity(Integer.parseInt(quantity.getTextField()));
            publication.setImgPath(imgPath);
            return publication;
        } catch(Exception e) {
            showMessage(e.getMessage());
        } 
        return null;
    }

    public Publication getEditInfoPublication() {
        if(!validAuthor() || !validName() || !validPrice() || !validPublisher() || !validQuantity() || !validDate() || !validType()){
            return null;
        } 
        try{
            Publication publication = new Publication();
            publication.setName(name.getTextField());
            publication.setCode(code.getTextField());
            publication.setAuthor(author.getTextField());
            publication.setPrice(Double.parseDouble(price.getTextField()));
            publication.setPublisher(publisher.getTextField());
            publication.setPublishedDate(datePickerPanel.getDateString());
            publication.setType(typeMenu.getTypeString());
            publication.setQuantity(Integer.parseInt(quantity.getTextField()));
            publication.setImgPath(imgPath);
            return publication;
        } catch(Exception e){
            showMessage(e.getMessage());
        }
        return null;
    }

    public void setAddMode() {
        name.setField("");
        code.setField("");
        publisher.setField("");
        author.setField("");
        quantity.setField("");
        price.setField("");
        datePickerPanel.clearDate();
        typeMenu.clearType();
        imgPath = ".\\public\\images\\ph.png";
        card.setImageLabel(imgPath);

        addBtn.setEnable(true);
        editBtn.setEnable(false);
        deleteBtn.setEnable(false);
    }

    public void setEditMode() {
        this.setEditInfoView(publication);
        addBtn.setEnable(false);
        editBtn.setEnable(true);
        deleteBtn.setEnable(true);
    }
    
    public void showMessage(String message){
        JOptionPane.showMessageDialog(this, message);
    }
    
    public boolean validName(){
        String checkName = name.getTextField();
        if(checkName == null || checkName.trim().isEmpty()){
            showMessage("Không được bỏ trống 1");
            return false;
        }
        return true;
    }
    
    public boolean validPublisher(){
        String checkPublisher = publisher.getTextField();
        if(checkPublisher == null || checkPublisher.trim().isEmpty()){
            showMessage("Không được bỏ trống 2");
            return false;
        }
        return true;
    }
    
    public boolean validAuthor(){
        String checkAuthor = author.getTextField();
        if(checkAuthor == null || checkAuthor.trim().isEmpty()){
            showMessage("Không được bỏ trống 3");
            return false;
        }
        return true;
    }
    
    public boolean validQuantity(){
        String checkQuantity = quantity.getTextField();
        if(checkQuantity == null || checkQuantity.trim().isEmpty()){
            showMessage("Không được bỏ trống 4");
            return false;
        }
        try {
            if(Integer.parseInt(checkQuantity) <= 0){
                showMessage("Số lượng không hợp lệ");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessage("Số lượng không hợp lệ");
        }
        return true;
    }
    
    public boolean validPrice(){
        String checkPrice = price.getTextField();
        if(checkPrice == null || checkPrice.trim().isEmpty()){
            showMessage("Không được bỏ trống 5");
            return false;
        }
        try {
            if(Double.parseDouble(checkPrice) <= 0){
                showMessage("Giá không hợp lệ");
                return false;
            }
        } catch (NumberFormatException e) {
            showMessage("Giá không hợp lệ");
        }

        return true;
    }
    
    public boolean validType(){
        String type = typeMenu.getTypeString();
        if(Objects.equals(type, "Choose Type")){
            showMessage("Thể loại không hợp lệ");
            return false;
        } 
        return true;
    }
    
    public boolean validDate(){
        Date date = datePickerPanel.getDateValue();
        Date dateNow = new Date();
        if(date == null || DateFomatterUtil.valueToString(date).trim().isEmpty()){
            showMessage("Không được bỏ trống");
            return false;
        }
        if(dateNow.after(date)){
            return true;
        } else{
            showMessage("Ngày không hợp lệ");
            return false;
        }
    }

    public void setAddBtnOnClickListener(ActionListener listener) {
        addBtn.onClickListener(listener);
    }

    public void setEditBtnOnClickListener(ActionListener listener) {
        editBtn.onClickListener(listener);
    }

    public void setDeleteBtnOnClickListener(ActionListener listener) {
        deleteBtn.onClickListener(listener);
    }

    public void setExitBtnOnClickListener(ActionListener listener) {
        exitBtn.onClickListener(listener);
    }

    public void setBrowseBtnOnClickListener(ActionListener listener) {
        browseBtn.onClickListener(listener);
    }

    public void setNameFieldOnChangeListener(DocumentListener listener) {
        name.addFieldChange(listener);
    }

    public void setAuthorFieldOnChangeListener(DocumentListener listener) {
        author.addFieldChange(listener);
    }
}
