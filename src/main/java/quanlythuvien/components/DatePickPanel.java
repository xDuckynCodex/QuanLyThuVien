package quanlythuvien.components;


import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;

public class DatePickPanel extends JPanel {

    public DatePickPanel() {
        initComponent();
    }

    public UtilDateModel dateModel;
    public JDatePanelImpl datePanel;
    public JDatePickerImpl datePicker;
    private JLabel dateLabel;
    public void initComponent() {
        dateModel = new UtilDateModel();
        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        dateLabel = new JLabel("Ngày xuất bản");
        this.setLayout(new FlowLayout());

        this.add(dateLabel);
        this.add(datePicker);
    }
}
