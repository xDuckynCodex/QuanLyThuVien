package quanlythuvien.controllers;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.ManageView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageController {
    private PublicationDao publicationDao;
    private final ManageView manageView;
    private InfoController infoController;
    private RenterController renterController;

    public void setRenterController(RenterController renterController) {
        this.renterController = renterController;
    }

    public void setInfoController(InfoController infoController) {
        this.infoController = infoController;
    }
    public void setPublicationDao(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    public ManageController(ManageView manageView) {
        this.manageView = manageView;

        this.manageView.setSearchFieldOnChangeListener(new AddSearchListener());
        this.manageView.setAddBtnClickListener(new AddBtnClickListener());
        this.manageView.setTransferClickListener(new TransferClickListener());
    }

    public void showView() {
        manageView.showView();
    }

    //Handler Event
    class AddSearchListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            String searchField = manageView.getSearchField();
            manageView.showView(searchField);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String searchField = manageView.getSearchField();
            manageView.showView(searchField);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
    class AddBtnClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            infoController.setAddMode();
            infoController.setPublicationDao(publicationDao);
            infoController.showInfoView();
        }
    }

    class TransferClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            manageView.setVisible(false);
            renterController.showRenterView();
        }
    }
}
