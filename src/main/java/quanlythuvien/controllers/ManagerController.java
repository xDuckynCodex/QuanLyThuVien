package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.ManagerView;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ManagerController {
    private PublicationDao pubDao;
    private ManagerView managerView;
    
    public ManagerController(ManagerView view) {
        this.managerView = view;
        pubDao = new PublicationDao();
        
        view.addAddPublicationListener(new AddPublicationListener());
        view.addEditPublicationListener(new EditPublicationListener());
        view.addFilterPublicationListener(new FilterPublicationListener());
        view.addDeletePublicationListener(new DeletePublicationListener());
        view.addClearPublicationListener(new ClearPublicationListener());
        view.addFillPublicationFromSelectedRow(new FillPublicationFromSelectedRowListener());
    }

    public void showPublicationView() {
        managerView.setVisible(true);
        managerView.showListPublications(pubDao.getListPublication());
    }

    public class AddPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Publication publication = managerView.getPublicationInfo();
            if(publication != null){
                pubDao.add(publication);
                managerView.showPublication(publication);
                managerView.showListPublications(pubDao.getListPublication());
                managerView.clearPublication();
                managerView.showMessage("Thêm thành công");
            }
        }
    }
    
    class EditPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Publication publication = managerView.getPublicationInfo();
            if(publication != null){
                pubDao.edit(publication);
                managerView.showPublication(publication);
                managerView.showListPublications(pubDao.getListPublication());
                managerView.clearPublication();
                managerView.showMessage("Cập nhật thành công");

            }
        }
    }

    class DeletePublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Publication publication = managerView.getPublicationInfo();
            if(publication != null){
                pubDao.delete(publication);
                managerView.showPublication(publication);
                managerView.showListPublications(pubDao.getListPublication());
                managerView.showMessage("Xoá thành công");
            }
        }
    }
    
    class FilterPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
                pubDao.filter(); 
                managerView.showListPublications(pubDao.getListPubFilter());
                managerView.clearPublication();   
        }
    }
    
    class FillPublicationFromSelectedRowListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            managerView.fillPublicationFromSelectedRow();
        }
    }

    class ClearPublicationListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            managerView.clearPublication();
        }
    }
}
