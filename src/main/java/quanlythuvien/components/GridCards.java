package quanlythuvien.components;

import quanlythuvien.entities.Publication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridCards extends JScrollPane {
    JPanel cardPanel;
    private final int gridColumns = 8;

    private List<Card> cardList;

    private GridLayout layout;
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

    //call to show info
    public void getCardList(List<Publication> publicationList) throws IOException {
        cardList = new ArrayList<Card>();
        for (Publication publication : publicationList) {
            cardList.add(new Card(publication));
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
}
