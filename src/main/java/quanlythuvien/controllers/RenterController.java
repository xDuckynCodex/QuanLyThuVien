/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.RentedBook;
import quanlythuvien.entities.Renter;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.ManageView;
import quanlythuvien.views.RenterView;

/**
 *
 * @author Admin
 */
public class RenterController {
    private RenterDao renterDao;
    private PublicationDao publicationDao;

    public void setRenterDao(RenterDao renterDao) {
        this.renterDao = renterDao;
    }
    public void setPublicationDao(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }
    public void setManageView(ManageView manageView) {
        this.manageView = manageView;
    }

    private final RenterView renterView;
    private ManageView manageView;

    public void setManageController(ManageController manageController) {
        this.manageController = manageController;
    }

    private ManageController manageController;

    public RenterController(RenterView view){
        this.renterView = view;
//        view.hideTablePub();

        view.addAddRenterListener(new AddNewRenterListener());
        view.addEditRenterListener(new EditRenterListener());
        view.addDeleteRenterListener(new DeleteRenterListener());
        view.addSortByNameRenterListener(new SortByNameRenterListener());
        view.addClearRenterListener(new ClearRenterListener());
        view.addFillRenterFromSelectedRow(new FillRenterFromSelectedRowListener());
        view.addFillBookFromSelectedRow(new FillBookFromSelectedRowListener());
        view.addTransferPublicationListener(new TransferPublicationListener());
        view.addRentedBookFieldSearch(new RentedBookSearchListener());
        view.addNewBookToRentedBookList(new NewBookToRentedBookList());
        view.addRemoveBookInRentedBookList(new RemoveBookInRentedBookList());
    }
    
    public void showRenterView(){
        renterView.setVisible(true);
        renterView.showListRenter(renterDao.getListRenter());
        renterView.clear();
    }
    
    class AddNewRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Renter renter = renterView.getNewRenterInfo();
            if(renter != null && renterView.checkPublication() && renterView.checkQuantityToRent()){

                renterDao.addRenter(renter);
                for (RentedBook rentedBook : renter.getRentedBookList()) {
                    publicationDao.edit(rentedBook.getPublication());
                }

                renterView.showListRenter(renterDao.getListRenter());

                renterView.showMessage("Thêm thành công");
            }
            renterView.clear();
            manageView.setTableStatistic();
            renterView.setRentedBookList(new ArrayList<>());
            renterView.setListToListRentedBookScroll();
        }
    }



    class EditRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Renter renter = renterView.getEditRenterInfo();
            if(renter != null){
                renterDao.editRenter(renter);

                for (RentedBook rentedBook : renter.getRentedBookList()) {
                    publicationDao.edit(rentedBook.getPublication());
                }

                renterView.showListRenter(renterDao.getListRenter());
                renterView.showMessage("Cập nhật thành công");
            }
            renterView.clear();
            manageView.setTableStatistic();
            renterView.setRentedBookList(new ArrayList<>());
            renterView.setListToListRentedBookScroll();
        }
    }

    class DeleteRenterListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            Renter renter = renterView.getEditRenterInfo();
            renter = renterDao.getRenterByCode(renter.getCode());
            if(renter != null){
                renterDao.delete(renter);

                for (RentedBook rentedBook : renter.getRentedBookList()) {
                    publicationDao.setRentedInDeleting(rentedBook.getPublication(), rentedBook.getQuantity());
                }

                renterView.showListRenter(renterDao.getListRenter());
                renterView.showMessage("Xoá thành công");
            }
            renterView.clear();
            manageView.setTableStatistic();
            renterView.setRentedBookList(new ArrayList<>());
        }
    }

    class FillRenterFromSelectedRowListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            renterView.fillRenterFromSelectedRow();
        }
    }

    class FillBookFromSelectedRowListener implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e){
            renterView.fillBookFromSelectedRow();
        }
    }

    class ClearRenterListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            renterView.clear();
        }
    }
    
    class TransferPublicationListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            renterView.setVisible(false);
            manageController.showView();
        }
    }

    class SortByNameRenterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            renterDao.sortByName();
            renterView.showListRenter(renterDao.getListRenter());
        }
    }
    
    class RentedBookSearchListener implements DocumentListener{
        public void insertUpdate(DocumentEvent e) {
            String searchRentedBookField = renterView.getSearchRentedBookField();
            renterView.showResultView(searchRentedBookField);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String searchRentedBookField = renterView.getSearchRentedBookField();
            if("".equals(searchRentedBookField)){
                renterView.hideTablePub();
            } else{
                renterView.showResultView(searchRentedBookField);
            }       
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    class NewBookToRentedBookList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            renterView.addBookToRentedBookList(renterView.getRentedBookInfo());
            renterView.setListToListRentedBookScroll();
            renterView.clearBook();
        }
    }

    class RemoveBookInRentedBookList implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            renterView.removeBookInRentedBook();
            renterView.setListToListRentedBookScroll();
            renterView.clearBook();
            renterView.setEnableRemoveBookBtn(false);
        }
    }
}
