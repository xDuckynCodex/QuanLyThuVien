package quanlythuvien.views;

import quanlythuvien.components.*;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FontUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Renter;

public class InfoView extends JFrame {
    public InfoView() {
        initComponent();
    }

    public InfoView(GridCards gridCards) {
        this.gridCards = gridCards;
        this.publicationDao = gridCards.getPublicationDao();
        initComponent();
    }
    RenterView renterView;

    public void setRenterView(RenterView renterView) {
        this.renterView = renterView;
    }
    private InputField name, code, publisher, author, quantity, price;
    private ButtonComp addBtn, editBtn, deleteBtn, exitBtn;
    private JLabel title;
    private DatePickerPanel datePickerPanel;
    private DropDown typeMenu;
    private GridCards gridCards;
    private PublicationDao publicationDao;

    public Publication getPublication() {
        return publication;
    }
    
    private Publication publication;
    private RenterDao renterDao;

    public void setRenterDao(RenterDao renterDao) {
        this.renterDao = renterDao;
    }
    private final int north = 50;
    private final int west = 250;
    public void initComponent() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // label
        title = new JLabel("Thông tin đối tượng");
        title.setFont(new Font(FontUtil.Bitter, Font.PLAIN, 30 ));
        //input field
        name = new InputField("Tên sản phẩm");
        code = new InputField("Mã sản phẩm");
        code.disableField();
        publisher = new InputField("Nhà xuất bản");
        author = new InputField("Tên tác giả");
        quantity = new InputField("Số lượng");
        price = new InputField("Giá sản phẩm");

        datePickerPanel = new DatePickerPanel();
        typeMenu = new DropDown();
        //button
        addBtn = new ButtonComp("Thêm");
        editBtn =new ButtonComp("Sửa");
        deleteBtn = new ButtonComp("Xóa");
        exitBtn = new ButtonComp("Exit");

//        card = new Card();

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


        //set vi tri
        //title
        layout.putConstraint(SpringLayout.WEST, title, 200, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.NORTH,
                panel);
        //name
        layout.putConstraint(SpringLayout.WEST, name, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, name, north, SpringLayout.NORTH,
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
        this.setVisible(true);
    }
    
    public void setPublication(Publication publication) {
        this.publication = publication;
    }
    
    public void showInfoView() {
        this.setVisible(true);
    }
    
    public void updatePublication(){
        PublicationDao publicationDao = new PublicationDao();
//        publication.setName(name.getTextField());
//        publication.setCodeById();
//        publication.setAuthor(author.getTextField());
//        publication.setPrice(Double.parseDouble(price.getTextField()));
//        publication.setPublisher(publisher.getTextField());
//        publication.setPublishedDate(datePickerPanel.getDateString());
//        publication.setType(typeMenu.getTypeString());
        List<Publication> listP = publicationDao.getListPublication();
        List<Renter> listR = renterDao.getListRenter();
        for(Renter r : listR){
            for(Publication p : listP){
                if(p.getName().equals(r.getRentedBook())){
                    p.setQuantity(p.getQuantity() - r.getQuantity());
                    publicationDao.edit(p);
                }
            }
        }   
    }

    public Publication getNewInfoPublication() {
        Publication publication = new Publication();
        publication.setName(name.getTextField());
        publication.setCodeById();
        publication.setAuthor(author.getTextField());
        publication.setPrice(Double.parseDouble(price.getTextField()));
        publication.setPublisher(publisher.getTextField());
        publication.setPublishedDate(datePickerPanel.getDateString());
        publication.setType(typeMenu.getTypeString());
        publication.setQuantity(Integer.parseInt(quantity.getTextField()));
        return publication;
    }

    public Publication getEditInfoPublication() {
        Publication publication = new Publication();
        publication.setName(name.getTextField());
        publication.setCode(code.getTextField());
        publication.setAuthor(author.getTextField());
        publication.setPrice(Double.parseDouble(price.getTextField()));
        publication.setPublisher(publisher.getTextField());
        publication.setPublishedDate(datePickerPanel.getDateString());
        publication.setType(typeMenu.getTypeString());
        publication.setQuantity(Integer.parseInt(quantity.getTextField()));
        return publication;
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
}
