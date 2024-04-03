package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import java.text.ParseException;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.ManagerView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
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

        view.addDeletePublicationListener(new DeletePublicationListener());
        view.addClearPublicationListener(new ClearPublicationListener());
        view.addFillPublicationFromSelectedRow(new FillPublicationFromSelectedRowListener());
        view.addSortByName(new SortByNameListener());
        view.addSortByPrice(new SortByPriceListener());
        view.addSearchByName(new SearchByNameListener());
    }

    public void showPublicationView() {
        managerView.setVisible(true);
        managerView.showListPublications(pubDao.getListPublication());
    }

    class AddPublicationListener implements ActionListener{
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
                try {
                    pubDao.edit(publication);
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }
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

//    class FilterPublicationListener implements ActionListener{
//        public void actionPerformed(ActionEvent e){
//                pubDao.filter(); 
//                managerView.showListPublications(pubDao.getListPubFilter());
//                managerView.clearPublication();   
//        }
//    }

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

    class SortByNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pubDao.sortByName();
            managerView.showListPublications(pubDao.getListPublication());
        }
    }

    class  SortByPriceListener implements  ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            pubDao.sortByPrice();
            managerView.showListPublications(pubDao.getListPublication());
        }
    }

    class  SearchByNameListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            managerView.showListPublications(pubDao.searchByName(managerView.getSearchField()));
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            managerView.showListPublications(pubDao.searchByName(managerView.getSearchField()));
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
    
    public static void main(String[] args) {
        ManagerView mv = new ManagerView();
        ManagerController mc = new ManagerController(mv);
        mc.showPublicationView();
    }
}
