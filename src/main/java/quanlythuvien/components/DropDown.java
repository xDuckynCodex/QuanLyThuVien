package quanlythuvien.components;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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

    public String getTypeString() {
        return dropdown.getItemAt(dropdown.getSelectedIndex());
    }

    public void setType(String type) {
        int typeIndex = 1;
        for (String s : typeString) {
            if (!Objects.equals(s, type)) {
                typeIndex++;
            } else {
                break;
            }
        }
        dropdown.setSelectedIndex(typeIndex);
    }
}
