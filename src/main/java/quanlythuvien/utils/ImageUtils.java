package quanlythuvien.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ImageUtils {
    public static String getRelativePath(String path) {
        String absolutePath = new File(".").getAbsolutePath();
        return ".\\" + path.substring(absolutePath.length()- 1);
    }

    public static ImageIcon getImageFromFile(String filePath) {
        BufferedImage bImg;
        try {
            bImg = ImageIO.read(new File(filePath));
            return new ImageIcon(bImg);
        } catch (IOException ignored) {

        }
        return null;
    }
    public static ImageIcon getImageFromURL(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = url.openStream();
            BufferedImage bImg = ImageIO.read(inputStream);
            return new ImageIcon(bImg);
        } catch (Exception ignored) {

        }
        return null;
    }
}
