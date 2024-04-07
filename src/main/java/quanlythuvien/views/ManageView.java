package quanlythuvien.views;

import quanlythuvien.components.ButtonComp;
import quanlythuvien.components.GridCards;
import quanlythuvien.components.InputField;

import quanlythuvien.dao.PublicationDao;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManageView extends JFrame {
    public void setGridCards(GridCards gridCards) {
        this.gridCards = gridCards;
        panel.add(gridCards);
        //gridCard component
        layout.putConstraint(SpringLayout.EAST, gridCards, 0,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, gridCards, 0,
                SpringLayout.SOUTH, panel);
    }

    private JPanel panel;
    private SpringLayout layout;
    //components
    private GridCards gridCards;
    private InputField searchField;
    private ButtonComp addBtn;

    private ButtonComp transferBtn;
    private PublicationDao publicationDao;

    private JLabel frameLabel;
    public void initComponent() {
//        gridCards = new GridCards(publicationDao, this);
        searchField = new InputField("Tìm kiếm ấn phẩm: ", 20);
        frameLabel = new JLabel("Quản lý ấn phẩm");
        frameLabel.setFont(new Font(frameLabel.getFont().getName(),
                Font.PLAIN, 40));

        addBtn = new ButtonComp("Thêm ấn phẩm");
        transferBtn = new ButtonComp("Chuyển trang quản lý khách " +
                "hàng");
        //layout giao dien
        layout = new SpringLayout();
        panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(1900, 1000);



        //add element to panel
        //component
        panel.add(searchField);
        panel.add(addBtn);
        panel.add(transferBtn);
        //label
        panel.add(frameLabel);


        //set vi tri

        //search component
        layout.putConstraint(SpringLayout.WEST, searchField, 350,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 75,
                SpringLayout.NORTH, panel);
        //addBtn
        layout.putConstraint(SpringLayout.WEST, addBtn, 780,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 73,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.EAST, transferBtn, 0,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferBtn, 73,
                SpringLayout.NORTH, panel);
        //label
        layout.putConstraint(SpringLayout.WEST, frameLabel, 800,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, frameLabel, 10,
                SpringLayout.NORTH, panel);

        //set frame
        this.add(panel);
        this.pack();
        this.setTitle("Manage View");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public ManageView() {
        initComponent();
    }

    public ManageView(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
        initComponent();
    }

    public void showView() {
        gridCards.setCardList();
        this.setVisible(true);
    }

    public void showView(String searchText) {
        gridCards.setCardList(searchText);
        this.setVisible(true);
    }
    public String getSearchField() {
        return searchField.getTextField();
    }

    //Thêm sự kiên sự kiện
    public void setSearchFieldOnChangeListener(DocumentListener listener) {
        searchField.addFieldChange(listener);
    }

    public void setAddBtnClickListener(ActionListener listener) {
        addBtn.onClickListener(listener);
    }

    public void setTransferClickListener(ActionListener listener) {
        transferBtn.onClickListener(listener);
    }
}
