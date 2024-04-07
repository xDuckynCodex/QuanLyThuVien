package quanlythuvien.components;

import quanlythuvien.controllers.InfoController;
import quanlythuvien.entities.Publication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Card extends JPanel {
    private InfoController infoController;
    private JPanel mainPanel;
    private JLabel nameCard;
    private JLabel authorCard;
    private  JLabel imgCardLabel;
    private ImageIcon imgCard;
    private ContextMenu menu;
    private Publication publication;
    public Card(Publication publication, GridCards gridCards,
                InfoController infoController) {
        this.publication = publication;
        this.infoController = infoController;
        initComponent(gridCards);
    }

    public void initComponent(GridCards gridCards) {
        // constructor elements
        nameCard = new JLabel(publication.getName());
        authorCard = new JLabel(publication.getAuthor());
        this.getImageFromFile(".\\public\\ph.png");
        imgCardLabel = new JLabel(imgCard);

        //context menu
        menu = new ContextMenu(publication, gridCards, infoController);

        //mainPanel;
        mainPanel = new JPanel();
        BoxLayout layout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(layout);

        //css
        imgCardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameCard.setFont(new Font(nameCard.getFont().getName(), Font.PLAIN,
                20));
        nameCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorCard.setFont(new Font("Bitter Medium", Font.PLAIN,
                15));
        authorCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add element to main panel
        mainPanel.add(imgCardLabel);
        mainPanel.add(nameCard);
        mainPanel.add(authorCard);

        //mouse listener
        mainPanel.addMouseListener(new RightClickMouse());

        // set layout card
        mainPanel.setSize(150, 230);
        this.add(mainPanel);
        this.setLayout(new GridLayout(1,1));
    }

    public void getImageFromURL(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();
        BufferedImage bImg = ImageIO.read(inputStream);
        imgCard = new ImageIcon(bImg);
    }

    public void getImageFromFile(String filePath) {
        BufferedImage bImg;
        try {
            bImg = ImageIO.read(new File(filePath));
            imgCard = new ImageIcon(bImg);
        } catch (IOException ignored) {

        }
    }


    class RightClickMouse implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {
                menu.show(mainPanel, e.getX(), e.getY());
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
}
