package quanlythuvien.components;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.ManageView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridCards extends JScrollPane {
    JPanel cardPanel;
    private final int gridColumns = 8;
    private List<Card> cardList;
    private GridLayout layout;
    private PublicationDao publicationDao;
    private ManageView manageView;

    public void initComponent() {
        cardList = new ArrayList<Card>();
        // card panel setup
        this.setCardPanel();

        this.setViewportView(cardPanel);
        this.setPreferredSize(new Dimension(1610, 900));
        this.getVerticalScrollBar().setUnitIncrement(16);
    }

    public GridCards() {
        initComponent();
    }

    public GridCards(PublicationDao publicationDao) {
        this.passInstance(publicationDao);
        initComponent();
    }

    public GridCards(PublicationDao publicationDao, ManageView manageView) {
        this.passInstance(publicationDao);
        this.manageView = manageView;
        initComponent();
    }

    //call to show info
    public void setCardList() {
        cardList = new ArrayList<Card>();
        for (Publication publication : publicationDao.getListPublication()) {
            cardList.add(new Card(publication, this));
        }
        this.setCardPanel();
        for (Card c : cardList) {
            cardPanel.add(c);
        }
        this.setViewportView(cardPanel);
    }

    public void setCardList(String searchText) {
        cardList = new ArrayList<Card>();
        for (Publication publication : publicationDao.searchByName(searchText)) {
            cardList.add(new Card(publication, this));
        }
        this.setCardPanel();
        for (Card c : cardList) {
            cardPanel.add(c);
        }
        this.setViewportView(cardPanel);
    }


    public void setCardPanel() {
        layout = new GridLayout(0, gridColumns, 10, 10);
        cardPanel = new JPanel();
        cardPanel.setLayout(layout);
    }

    public void passInstance(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public void deleteCard(Publication publication) {
        publicationDao.delete(publication);
        this.setCardList(manageView.getInputField());
    }
}
