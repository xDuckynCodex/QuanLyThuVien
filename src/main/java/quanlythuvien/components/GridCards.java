package quanlythuvien.components;

import quanlythuvien.entities.Publication;
import quanlythuvien.views.ContextMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GridCards extends JScrollPane {
    JPanel cardPanel;
    private final int gridColumns = 8;

    private List<Card> cardList;
    private ContextMenu menu;
    private GridLayout layout;
    public void initComponent() {
        cardList = new ArrayList<Card>();
        menu = new ContextMenu();
        // card panel setup
        this.setCardPanel();

        this.setViewportView(cardPanel);
        this.setPreferredSize(new Dimension(1610, 900));
        this.getVerticalScrollBar().setUnitIncrement(16);
        this.addMouseListener(new RightClickMouse());
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

    class RightClickMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
            menu.show(cardPanel, e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
