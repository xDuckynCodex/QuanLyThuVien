package quanlythuvien.controllers;

import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.InfoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


public class InfoController {
    private InfoView infoView;
    private PublicationDao publicationDao;
    public InfoController() {
        initComponent();
    }

    public InfoController(InfoView view) {
        this.infoView = view;
        initComponent();
    }

    public void initComponent() {
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

    class AddClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler add event
            Publication publication = infoView.getNewPublication();
            publicationDao.add(publication);

            JOptionPane.showMessageDialog(infoView, "Thêm ấn phẩm thành công");

            //dispose frame
            infoView.dispose();
        }
    }

    class EditClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler edit event
            Publication publication = infoView.getNewPublication();
            try {
                publicationDao.edit(publication);
                JOptionPane.showMessageDialog(infoView, "Sửa ấn phẩm thành " +
                        "công");
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

    public static void main(String[] args) {
        InfoView infoView = new InfoView();
        infoView.setEditMode();
        InfoController ic = new InfoController(infoView);
        ic.showInfoView();
    }
}
