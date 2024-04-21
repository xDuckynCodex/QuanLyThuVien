/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.views.ManageView;
import quanlythuvien.views.PayerView;
import quanlythuvien.views.RenterView;

/**
 *
 * @author Admin
 */
public class PayerController {
    private RenterDao renterDao;
    private RenterView renterView;
    private ManageView manageView;
    private PayerView payerView;
    private ManageController manageController;
    private RenterController renterController;

    public void setManageController(ManageController manageController) {
        this.manageController = manageController;
    }

    public void setRenterController(RenterController renterController) {
        this.renterController = renterController;
    }

    public void setRenterDao(RenterDao renterDao) {
        this.renterDao = renterDao;
    }
    
    public PayerController(PayerView view){
        this.payerView = view;
        view.addTransferToPublicationViewListener(new TransferToPublicationViewListener());
        view.addTransfertoRenterViewListener(new TransferToRenterViewListener());
    }
    
    public void showPayerView(){
        payerView.setVisible(true);
        payerView.showListPayer(renterDao.getListRenterPayingBack());
    }
    
    class AddSearchListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            String searchField = payerView.getSearchField();
            payerView.searchByName(searchField);
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            String searchField = payerView.getSearchField();
            payerView.searchByName(searchField);
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }
        
    
    class TransferToPublicationViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            payerView.setVisible(false);
            manageController.showView();
        }
    }
    
    class TransferToRenterViewListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            payerView.setVisible(false);
            renterController.showRenterView();
        }
    }
}
