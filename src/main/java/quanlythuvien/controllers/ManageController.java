package quanlythuvien.controllers;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.ManageView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import quanlythuvien.components.TableStatistic;

public class ManageController {
    TableStatistic ts;
    private PublicationDao publicationDao;
    private final ManageView manageView;
    private InfoController infoController;
    private RenterController renterController;
    private PayerController payerController;
    
    public void setRenterController(RenterController renterController) {
        this.renterController = renterController;
    }

    public void setPayerController(PayerController payerController) {
        this.payerController = payerController;
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
        this.manageView.setTransferToRenterViewClickListener(new TransferToRenterViewClickListener());
        this.manageView.setFilterByTypeBtnClickListener(new FilterByTypeClickListener());
        this.manageView.setTransferToPayerViewClickListener(new TransferToPayerViewClickListener());
    }

    public TableStatistic getTs() {
        return ts;
    }

    public void setTs(TableStatistic ts) {
        this.ts = ts;
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

    class TransferToRenterViewClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            manageView.setVisible(false);
            renterController.showRenterView();
        }
    }
    
    class TransferToPayerViewClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            manageView.setVisible(false);
            payerController.showPayerView();
        }
    }
    
    class FilterByTypeClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            manageView.filter();
        }
    }
}
