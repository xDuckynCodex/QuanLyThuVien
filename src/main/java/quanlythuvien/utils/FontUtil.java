package quanlythuvien.utils;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class FontUtil {
    private static final File fontFile
            = new File("..\\public\\fonts\\Bitter-Medium.ttf");
    public static void setFont() {
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        Font font;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            ge.registerFont(font);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Font Bitter(int fontSize) {
        return new Font("Bitter Medium", Font.PLAIN, fontSize);
    }
    public static Font Bitter(int fontSize, int style) {
        return new Font("Bitter Medium", style, fontSize);
    }
}
