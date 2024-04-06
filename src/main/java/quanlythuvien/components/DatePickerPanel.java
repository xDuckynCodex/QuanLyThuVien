package quanlythuvien.components;


import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import quanlythuvien.utils.DateFomatterUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class DatePickerPanel extends JPanel {

    public DatePickerPanel() {
        initComponent();
    }

    public UtilDateModel dateModel;
    public JDatePanelImpl datePanel;
    public JDatePickerImpl datePicker;
    private JLabel dateLabel;
    public Date date;
    public void initComponent() {
        dateModel = new UtilDateModel();
        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        dateLabel = new JLabel("Ngày xuất bản");
        this.setLayout(new FlowLayout());

        this.add(dateLabel);
        this.add(datePicker);
    }

    public void showDate(String dateString) {
        String[] dateValue = dateString.split("/");
        int year = Integer.parseInt(dateValue[2]);
        int month = Integer.parseInt(dateValue[1]) - 1; // month is zero-base
        int day = Integer.parseInt(dateValue[0]);
        dateModel.setDate(year, month, day);
        dateModel.setSelected(true);
    }

    public String getDate() {
        date = (Date) datePicker.getModel().getValue();
        return DateFomatterUtil.valueToString(date);
    }
}
