package quanlythuvien.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonComp extends JPanel {
    private JButton btn;
    public void initComponent(String label) {
        btn = new JButton(label);

        this.add(btn);
    }

    public ButtonComp(String label) {
        initComponent(label);
    }

    public void addClickListener(ActionListener listener) {
        btn.addActionListener(listener);
    }

}
