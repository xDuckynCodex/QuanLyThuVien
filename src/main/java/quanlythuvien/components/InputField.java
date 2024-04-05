package quanlythuvien.components;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class InputField extends JPanel {
    private JLabel label;

    public void setField(String field) {
        this.field.setText(field);
    }

    private JTextField field;

    public void initComponent(String label, int columns) {
        this.label = new JLabel(label);
        field = new JTextField(columns);
        field.setFont(new Font(field.getFont().getName(), Font.PLAIN, 15));

        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(this.label);
        this.add(field);

        this.setVisible(true);
    }

    public InputField(String label) {
        initComponent(label, 15);
    }

    public InputField(String label, int columns) {
        initComponent(label, columns);
    }

    public String getTextField() {
        return field.getText();
    }

    //Xử lý sự kiện
    public void addFieldChange(DocumentListener listener) {
        field.getDocument().addDocumentListener(listener);
    }

}
