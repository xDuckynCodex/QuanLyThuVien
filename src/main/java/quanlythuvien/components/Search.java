package quanlythuvien.components;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Search extends JPanel {
    private JLabel searchLabel;
    private JTextField searchField;

    public void initComponent() {
        searchLabel = new JLabel("Tìm kiếm ấn phẩm");
        searchField = new JTextField(20);
        searchField.setFont(new Font(searchField.getFont().getName(), Font.PLAIN, 15));

        this.setLayout(new GridLayout(1,2));

        this.add(searchLabel);
        this.add(searchField);

        this.setVisible(true);
    }

    public Search() {
        initComponent();
    }

    //Xử lý sự kiện
    public void addSearching(DocumentListener listener) {
        searchField.getDocument().addDocumentListener(listener);
    }

    public String getSearchField() {
        return searchField.getText();
    }
}
