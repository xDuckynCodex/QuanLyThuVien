package quanlythuvien.views;

import quanlythuvien.components.GridCards;
import quanlythuvien.components.Search;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class MangeView extends JFrame {
    //components
    private GridCards gridCards;
    public Search search;

    private JLabel frameLabel;
    public void initComponent() {
        gridCards = new GridCards();
        search = new Search();
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
        panel.add(search);

        //label
        panel.add(frameLabel);


        //set vi tri
        //gridCard component
        layout.putConstraint(SpringLayout.EAST, gridCards, 0,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, gridCards, 0,
                SpringLayout.SOUTH, panel);
        //search component
        layout.putConstraint(SpringLayout.WEST, search, 350,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, search, 80,
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

    public MangeView() throws IOException {
        initComponent();
    }

    public void showView(List<Publication> publicationList) throws IOException {
        gridCards.getCardList(publicationList);
        this.setVisible(true);
    }

    //Xử lý sự kiện
    public void addSearchListener(DocumentListener listener) {
        search.addSearching(listener);
    }



    public static void main(String[] args) throws IOException {
        PublicationDao publicationDao = new PublicationDao();
        MangeView mc = new MangeView();
        mc.showView(publicationDao.getListPublication());
        mc.setVisible(true);
    }
}
