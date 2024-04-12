package quanlythuvien.components;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ButtonComp extends JPanel {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;
    private JButton btn;
    public void initComponent(String label) {
        btn = new JButton(label);

        this.add(btn);
    }

    public ButtonComp(String label) {
        initComponent(label);
    }
    public ButtonComp(String label, String code) {
        initComponent(label);
        this.code = code;
    }

    public void setEnable(Boolean bool) {
        btn.setEnabled(bool);
    }

    public void onClickListener(ActionListener listener) {
        btn.addActionListener(listener);
    }
}
