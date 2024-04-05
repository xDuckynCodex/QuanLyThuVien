package quanlythuvien.views;

import quanlythuvien.components.GridCards;
import quanlythuvien.components.InputField;

import quanlythuvien.dao.PublicationDao;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class ManageView extends JFrame {
    //components
    private GridCards gridCards;
    private InputField inputField;

    private PublicationDao publicationDao;


    private JLabel frameLabel;
    public void initComponent() {
        gridCards = new GridCards(publicationDao, this);
        inputField = new InputField("Tìm kiếm ấn phẩm: ", 20);
        frameLabel = new JLabel("Quản lý ấn phẩm");
        frameLabel.setFont(new Font(frameLabel.getFont().getName(),
                Font.PLAIN, 40));

        //layout giao dien
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(1900, 1000);



        //add element to panel
        //component
        panel.add(gridCards);
        panel.add(inputField);

        //label
        panel.add(frameLabel);


        //set vi tri
        //gridCard component
        layout.putConstraint(SpringLayout.EAST, gridCards, 0,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, gridCards, 0,
                SpringLayout.SOUTH, panel);
        //search component
        layout.putConstraint(SpringLayout.WEST, inputField, 350,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, inputField, 75,
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
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public ManageView() {
        initComponent();
    }

    public ManageView(PublicationDao publicationDao) {
        this.passInstance(publicationDao);
        initComponent();
    }

    public void passInstance(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public void showView() {
        gridCards.setCardList();
        this.setVisible(true);
    }

    public void showView(String searchText) {
        gridCards.setCardList(searchText);
        this.setVisible(true);
    }

    //Xử lý sự kiện
    public void addSearchListener(DocumentListener listener) {
        inputField.addFieldChange(listener);
    }

    public String getInputField() {
        return inputField.getTextField();
    }
}
