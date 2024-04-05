package quanlythuvien.controllers;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.views.ManageView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ManageController {
    private final PublicationDao publicationDao;
    private final ManageView manageView;

    public ManageController() {
        this.publicationDao = new PublicationDao();
        this.manageView = new ManageView(publicationDao);


        manageView.addSearchListener(new AddSearchListener());
    }

    public void showView() {
        manageView.showView();
    }


    //Handler Event
    class AddSearchListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            String searchField = manageView.getInputField();
            manageView.showView(searchField);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String searchField = manageView.getInputField();
            manageView.showView(searchField);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
    public static void main(String[] args) {
        ManageController mc = new ManageController();
        mc.showView();
    }
}
