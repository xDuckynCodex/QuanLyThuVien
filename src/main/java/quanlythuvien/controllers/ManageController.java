package quanlythuvien.controllers;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.MangeView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.IOException;
import java.util.List;

public class ManageController {
    private PublicationDao publicationDao;
    private MangeView mangeView;

    public ManageController(MangeView view) {
        this.mangeView = view;
        this.publicationDao = new PublicationDao();

        mangeView.addSearchListener(new AddSearchListener());
    }

    public void showView() throws IOException {
        List<Publication> publicationList = publicationDao.getListPublication();
        mangeView.showView(publicationList);
    }


    //Handler Event
    class AddSearchListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            try {
                mangeView.showView(publicationDao.searchByName(mangeView.search.getSearchField()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            try {
                mangeView.showView(publicationDao.searchByName(mangeView.search.getSearchField()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
    public static void main(String[] args) throws IOException {
        MangeView mv = new MangeView();
        ManageController mc = new ManageController(mv);
        mc.showView();
    }
}
