package quanlythuvien.components;

import quanlythuvien.entities.Publication;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridCards extends JScrollPane {
    private JPanel cardPanel;

    public void setGridRows(int gridRows) {
        this.gridRows = gridRows;
    }

    private int gridRows;
    private final int gridColumns = 10;

    private List<Card> cardList;


    public void initComponent() {
        cardList = new ArrayList<Card>();
        // card panel setup
        GridLayout layout = new GridLayout(gridRows, gridColumns, 10, 10);
        cardPanel = new JPanel();
        cardPanel.setLayout(layout);
        this.setViewportView(cardPanel);
        this.setPreferredSize(new Dimension(1610, 900));
        this.getVerticalScrollBar().setUnitIncrement(16);
    }

    public GridCards() {
        initComponent();
    }

    public void getCardList(List<Publication> publicationList) throws IOException {
        for (Publication publication : publicationList) {
            cardList.add(new Card(publication));
        }
        for (Card c : cardList) {
            cardPanel.add(c);
        }
        setGridRows(publicationList.size() / gridColumns + 1);
    }
}
