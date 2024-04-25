package quanlythuvien.controllers;

import quanlythuvien.components.GridCards;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.ImageUtils;
import quanlythuvien.views.InfoView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import quanlythuvien.views.ManageView;


public class InfoController {
    private InfoView infoView;

    public void setGridCards(GridCards gridCards) {
        this.gridCards = gridCards;
    }

    ManageView manageView;
  
    public void setManageView(ManageView manageView) {
        this.manageView = manageView;
    }
    private GridCards gridCards;
    private PublicationDao publicationDao;
    private Publication publication;
    public void setPublication(Publication publication) {
        this.publication = publication;
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
        infoView.setBrowseBtnOnClickListener(new BrowseClickListener());
        infoView.setNameFieldOnChangeListener(new NameFieldListener());
        infoView.setAuthorFieldOnChangeListener(new AuthorFieldListener());
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
            Publication publication = infoView.getNewInfoPublication();
            if(publication != null){
                publicationDao.add(publication);
                //reset gridcards
                gridCards.setCardList();
                manageView.setTableStatistic();
                JOptionPane.showMessageDialog(infoView, "Thêm ấn phẩm thành công");
                //dispose frame
                infoView.dispose();
            }
        }
    }

    class EditClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler edit event
            Publication publication = infoView.getEditInfoPublication();
            if(publication != null){
                publicationDao.edit(publication);
                //reset gridcards
                gridCards.setCardList();
                manageView.setTableStatistic();
                infoView.dispose();
                JOptionPane.showMessageDialog(infoView, "Sửa ấn phẩm thành công");
            }
        }
    }

    class DeleteClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler delete event
            if(publication.getRented() == 0){
                publicationDao.delete(publication);
                //reset gridcards
                gridCards.setCardList();
                manageView.setTableStatistic();
                infoView.dispose();
                JOptionPane.showMessageDialog(infoView, "Xoá ấn phẩm thành công");
            } else {
                JOptionPane.showMessageDialog(infoView, "Sách đang cho mượn nên không thể xoá");
            }
        }
    }

    class ExitClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //dispose frame
            infoView.dispose();
        }
    }

    class BrowseClickListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Image files", ImageIO.getReaderFileSuffixes());
            fc.setFileFilter(filter);
            fc.setCurrentDirectory(new File("."));
            int result = fc.showOpenDialog(infoView);
            if (result == JFileChooser.APPROVE_OPTION) {
                String imagePath = fc.getSelectedFile().getPath();
                infoView.card.setImageLabel(imagePath);
                infoView.setImgPath(imagePath);
            }
        }
    }

    class NameFieldListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            infoView.card.setNameCard(infoView.name.getTextField());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (infoView.name.getTextField().isBlank()) {
                infoView.card.setNameCard("...");
            } else {
                infoView.card.setNameCard(infoView.name.getTextField());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }

    class AuthorFieldListener implements DocumentListener {

        @Override
        public void insertUpdate(DocumentEvent e) {
            infoView.card.setAuthorCard(infoView.author.getTextField());
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            if (infoView.author.getTextField().isBlank()) {
                infoView.card.setAuthorCard("...");
            } else {
                infoView.card.setAuthorCard(infoView.author.getTextField());
            }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {

        }
    }


}
