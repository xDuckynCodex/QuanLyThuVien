package quanlythuvien.views;

import quanlythuvien.components.GridCards;
import quanlythuvien.entities.Publication;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContextMenu extends JPopupMenu {
    public ContextMenu() {
        initComponent();
    }
    public ContextMenu(Publication publication, GridCards gridCards) {
        this.publication = publication;
        this.gridCards = gridCards;
        initComponent();
    }
    private GridCards gridCards;
    private JMenuItem editMenuItem, deleteMenuItem;
    private InfoView infoView;
    private Publication publication;

    public void initComponent() {
        editMenuItem = new JMenuItem("Edit");
        editMenuItem.addActionListener(new EditMenuItem());
        deleteMenuItem = new JMenuItem("Delete");
        deleteMenuItem.addActionListener(new DeleteMenuItem());
        this.add(editMenuItem);
        this.add(deleteMenuItem);
    }

    class EditMenuItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            infoView = new InfoView();
            infoView.showEditInfoView(publication);
        }
    }

    class DeleteMenuItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gridCards.deleteCard(publication);

        }
    }

}

