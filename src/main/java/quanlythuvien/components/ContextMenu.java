package quanlythuvien.components;

import quanlythuvien.components.GridCards;
import quanlythuvien.controllers.InfoController;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.InfoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContextMenu extends JPopupMenu {
    public ContextMenu() {
        initComponent();
    }
    private GridCards gridCards;
    private JMenuItem editMenuItem, deleteMenuItem;
    private InfoController infoController;
    private Publication publication;

    public ContextMenu(Publication publication, GridCards gridCards,
                       InfoController infoController) {
        this.publication = publication;
        this.gridCards = gridCards;
        this.infoController = infoController;
        initComponent();
    }

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
            infoController.setAddMode();
            infoController.showInfoView();
        }
    }

    class DeleteMenuItem implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gridCards.deleteCard(publication);
        }
    }

}

