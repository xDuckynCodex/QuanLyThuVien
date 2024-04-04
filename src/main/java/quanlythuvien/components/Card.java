package quanlythuvien.components;

import quanlythuvien.entities.Publication;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Card extends JPanel {

    private JLabel nameCard;
    private JLabel authorCard;
    private  JLabel imgCardLabel;
    private ImageIcon imgCard;
    public Card(Publication publication) throws IOException {
        initComponent(publication);
    }

    public void initComponent(Publication publication) throws IOException {
        // constructor elements
        nameCard = new JLabel(publication.getName());
        authorCard = new JLabel(publication.getAuthor());
        this.getImageFromFile(".\\public\\ph.png");
        imgCardLabel = new JLabel(imgCard);

        //css
        imgCardLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameCard.setFont(new Font(nameCard.getFont().getName(), Font.PLAIN,
                20));
        nameCard.setAlignmentX(Component.CENTER_ALIGNMENT);
        authorCard.setFont(new Font(nameCard.getFont().getName(), Font.PLAIN,
                15));
        authorCard.setAlignmentX(Component.CENTER_ALIGNMENT);

        //add element to panel
        this.add(imgCardLabel);
        this.add(nameCard);
        this.add(authorCard);

        // set layout card
        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setSize(150, 230);
        this.setLayout(layout);
    }

    public void getImageFromURL(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        InputStream inputStream = url.openStream();
        BufferedImage bImg = ImageIO.read(inputStream);
        imgCard = new ImageIcon(bImg);
    }

    public void getImageFromFile(String filePath) throws IOException {
        BufferedImage bImg = ImageIO.read(new File(filePath));
        imgCard = new ImageIcon(bImg);
    }
}
