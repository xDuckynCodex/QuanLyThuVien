package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.ManagerView;

public class ManagerController {
    private PublicationDao pubDao;
    private ManagerView managerView;
    
    public ManagerController(ManagerView view) {
        this.managerView = view;
        pubDao = new PublicationDao();
        
        view.addAddPublicationListener(new AddPublicationListener());
        view.addEditPublicationListener(new EditPublicationListener());
        view.addDeletePublicationListener(new DeletePublicationListener());       
    }
    class AddPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Publication publication = managerView.getPublicationInfo();
            if(publication == null){
                pubDao.add(publication);
                managerView.showPublication(publication);
                managerView.showListPublications(pubDao.getListPublication());
                managerView.showMessage("Thêm thành công");
            }
        }
    }
    
    class EditPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Publication publication = managerView.getPublicationInfo();
            if(publication == null){
                pubDao.add(publication);
                managerView.showPublication(publication);
                managerView.showListPublications(pubDao.getListPublication());
                managerView.showMessage("Cập nhật thành công");
            }
        }
    }
    
    class DeletePublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Publication publication = managerView.getPublicationInfo();
            if(publication == null){
                pubDao.add(publication);
                managerView.showPublication(publication);
                managerView.showListPublications(pubDao.getListPublication());
                managerView.showMessage("Xoá thành công");
            }
        }
    }
}
