package quanlythuvien.controllers;

import quanlythuvien.components.GridCards;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.InfoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class InfoController {
    private InfoView infoView;

    public void setGridCards(GridCards gridCards) {
        this.gridCards = gridCards;
    }

    private GridCards gridCards;
    private PublicationDao publicationDao;
    private Publication publication;
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public InfoController() {
        initComponent();
    }

    public InfoController(InfoView view) {
        this.infoView = view;
        initComponent();
    }

    public void initComponent() {
        infoView.setPublication(publication);

        infoView.setAddBtnOnClickListener(new AddClickedListener());
        infoView.setEditBtnOnClickListener(new EditClickedListener());
        infoView.setDeleteBtnOnClickListener(new DeleteClickedListener());
        infoView.setExitBtnOnClickListener(new ExitClickedListener());
    }

    // setter
    public void setInfoView(InfoView infoView) {
        this.infoView = infoView;
    }

    public void setPublicationDao(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
    }

    //show view
    public void showInfoView() {
        this.infoView.showInfoView();
    }

    public void setAddMode() {
        this.infoView.setAddMode();
    }

    public void setEditMode() {
        this.infoView.setPublication(publication);
        this.infoView.setEditMode();
    }

    class AddClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler add event
            Publication publication = infoView.getInfoPublication();
            publicationDao.add(publication);

            //reset gridcards
            gridCards.setCardList();

            JOptionPane.showMessageDialog(infoView, "Thêm ấn phẩm thành công");

            //dispose frame
            infoView.dispose();
        }
    }

    class EditClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler edit event
            Publication publication = infoView.getInfoPublication();
            try {
                publicationDao.edit(publication);
                JOptionPane.showMessageDialog(infoView, "Sửa ấn phẩm thành " +
                        "công");
                //reset gridcards
                gridCards.setCardList();
                infoView.dispose();
            } catch (ParseException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    class DeleteClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler delete event
        }
    }

    class ExitClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //dispose frame
            infoView.dispose();
        }
    }
}
