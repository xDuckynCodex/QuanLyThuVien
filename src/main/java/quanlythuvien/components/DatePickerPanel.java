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
        this.add(datePicker);
    }
    public DatePickerPanel(String label) {
        initComponent();
        dateLabel = new JLabel(label);
        this.add(dateLabel);
        this.add(datePicker);
    }

    private UtilDateModel dateModel;
    private JDatePanelImpl datePanel;
    private JDatePickerImpl datePicker;
    private JLabel dateLabel;
    public Date date;
    public void initComponent() {
        dateModel = new UtilDateModel();
        datePanel = new JDatePanelImpl(dateModel);
        datePicker = new JDatePickerImpl(datePanel);
        this.setLayout(new FlowLayout());

    }

    public void showDate(String dateString) {
        String[] dateValue = dateString.split("/");
        int year = Integer.parseInt(dateValue[2]);
        int month = Integer.parseInt(dateValue[1]) - 1; // month is zero-base
        int day = Integer.parseInt(dateValue[0]);
        dateModel.setDate(year, month, day);
        dateModel.setSelected(true);
    }

    public void clearDate() {
        dateModel.setSelected(false);
    }
    
    public String getDateString() {
        date = (Date) datePicker.getModel().getValue();
        return DateFomatterUtil.valueToString(date);
    }

    public Date getDateValue() {
        return (Date) datePicker.getModel().getValue();
    }
}
