package quanlythuvien.components;

import quanlythuvien.controllers.InfoController;
import quanlythuvien.entities.Publication;
import quanlythuvien.utils.FontUtil;
import quanlythuvien.utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Card extends JPanel implements MouseListener {
    //data
    private Publication publication;
    //elements
    private SpringLayout layout;
    private JLabel nameCard;
    private JLabel authorCard;
    private JLabel imageLabel;
    private ContextMenu menu;
    //instance need
    public Card(Publication publication, InfoController infoController) {//init in grid
        this.publication = publication;
        initComponent();
        menu = new ContextMenu(publication, infoController);
    }

    public Card() {
        initComponent();
    }//init in infoView

    public void initComponent() {
        String name, author, imgPath;
        if (this.publication == null) {
            name = "...";
            author = "...";
            imgPath = ".\\public\\images\\ph.png";
        } else {
            name = publication.getName();
            author = publication.getAuthor();
            imgPath = publication.getImgPath();
        }

        layout = new SpringLayout();

        nameCard = new JLabel(name, JLabel.CENTER);
        nameCard.setFont(FontUtil.Bitter(15, Font.BOLD));
        nameCard.setPreferredSize(new Dimension(150, 20));

        authorCard = new JLabel(author,JLabel.RIGHT);
        authorCard.setFont(FontUtil.Bitter(15));
        authorCard.setPreferredSize(new Dimension(150, 20));

        imageLabel = new JLabel(ImageUtils.getImageFromFile(imgPath));

        this.add(nameCard);
        this.add(authorCard);
        this.add(imageLabel);

        this.setLayout(layout);

        layout.putConstraint(SpringLayout.WEST, imageLabel, 5,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, nameCard, 5,
                SpringLayout.SOUTH, imageLabel);
        layout.putConstraint(SpringLayout.EAST, authorCard, 0,
                SpringLayout.EAST, nameCard);
        layout.putConstraint(SpringLayout.NORTH, authorCard, 0,
                SpringLayout.SOUTH, nameCard);
        this.setPreferredSize(new Dimension(150, 250));
        this.addMouseListener(this);
    }

    public void setImageLabel(String imagePath) {
        this.imageLabel.setIcon(ImageUtils.getImageFromFile(imagePath));
    }

    public void setNameCard(String nameCard) {
        this.nameCard.setText(nameCard);
    }

    public void setAuthorCard(String authorCard) {
        this.authorCard.setText(authorCard);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            if (menu != null) {
                menu.show(this, e.getX(), e.getY());
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
