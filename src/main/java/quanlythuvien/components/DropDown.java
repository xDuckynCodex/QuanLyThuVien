package quanlythuvien.components;

import javax.swing.*;
import java.awt.*;

public class DropDown extends JPanel {
    public DropDown() {
        initComponent();
    }

    private final String[] typeString = {"Choose type","Book", "Magazine", "Novel",
            "Newspapers"};
    private JComboBox<String> dropdown;
    private JLabel label;
    public void initComponent() {
        label = new JLabel("Thể loại: ");
        dropdown = new JComboBox<String>(typeString);

        this.setLayout(new FlowLayout());

        this.add(label);
        this.add(dropdown);

    }
}
