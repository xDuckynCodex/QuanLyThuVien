package quanlythuvien.components;

import quanlythuvien.controllers.InfoController;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.ManageView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridCards extends JScrollPane {
    JPanel cardPanel;
    private final int gridColumns = 6;
    private List<Card> cardList;
    private GridLayout layout;

    public PublicationDao getPublicationDao() {
        return publicationDao;
    }

    private PublicationDao publicationDao;
    private ManageView manageView;
    private InfoController infoController;

    public void initComponent() {
        cardList = new ArrayList<Card>();
        // card panel setup
        this.setCardPanel();

        this.setViewportView(cardPanel);
        this.setPreferredSize(new Dimension(1350, 900));
        this.getVerticalScrollBar().setUnitIncrement(16);
    }

    public GridCards() {
        initComponent();
    }

    public GridCards(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
        initComponent();
    }

    public GridCards(PublicationDao publicationDao, ManageView manageView) {
        this.publicationDao = publicationDao;
        this.manageView = manageView;
        initComponent();
    }

    public GridCards(PublicationDao publicationDao, InfoController infoController) {
        this.publicationDao = publicationDao;
        this.infoController = infoController;
        initComponent();
    }

    public GridCards(PublicationDao publicationDao, ManageView manageView, InfoController infoController) {
        this.publicationDao = publicationDao;
        this.manageView = manageView;
        this.infoController = infoController;
        initComponent();
    }

    //call to show info
    public void setCardList() {
        cardList = new ArrayList<Card>();
        for (Publication publication : publicationDao.getListPublication()) {
            cardList.add(new Card(publication, this, infoController));
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
            cardList.add(new Card(publication, this, infoController));
        }
        this.setCardPanel();
        for (Card c : cardList) {
            cardPanel.add(c);
        }
        this.setViewportView(cardPanel);
    }
    
    public void filterByType(String type){
        cardList = new ArrayList<Card>();
        for (Publication publication : publicationDao.filterByType(type)) {
            cardList.add(new Card(publication, this, infoController));
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

    public void deleteCard(Publication publication) {
        publicationDao.delete(publication);
        manageView.setTableStatistic();
        this.setCardList(manageView.getSearchField());
    }
    
    public List<Card> getCardList(){
        return cardList;
    }
}
