package quanlythuvien.controllers;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.ManageView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageController {
    private final PublicationDao publicationDao;
    private final ManageView manageView;

    public ManageController(PublicationDao publicationDao, ManageView manageView) {
        this.publicationDao = publicationDao;
        this.manageView = manageView;

        manageView.setSearchFieldOnChangeListener(new AddSearchListener());
        manageView.setAddBtnClickListener(new AddBtnClickListener());
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
            InfoView infoView = new InfoView();
            infoView.setAddMode();
            InfoController infoController = new InfoController(infoView);
            infoController.setPublicationDao(publicationDao);
            infoController.showInfoView();
        }
    }

//    public static void main(String[] args) {
//        ManageController mc = new ManageController();
//        mc.showView();
//    }
}
