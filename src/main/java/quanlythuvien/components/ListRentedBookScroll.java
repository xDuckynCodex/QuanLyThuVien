package quanlythuvien.components;

import quanlythuvien.entities.RentedBook;
import quanlythuvien.views.RenterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListRentedBookScroll extends JScrollPane {
    public void setRenterView(RenterView renterView) {
        this.renterView = renterView;
    }

    private RenterView renterView;
    public List<RentedBook> getRentedBookList() {
        return rentedBookList;
    }
    public void setRentedBookList(List<RentedBook> rentedBookList) {
        this.rentedBookList = rentedBookList;
        panel = new JPanel();
        panel.setLayout(layout);
        for (RentedBook rentedBook : rentedBookList) {
            ButtonComp bc =
                    new ButtonComp(rentedBook.getPublication().getName(),
                            rentedBook.getPublication().getCode());
            bc.onClickListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    renterView.fillRentedBook(rentedBook);
                    renterView.hideTablePub();
                    renterView.setEnableRemoveBookBtn(true);
                }
            });
            panel.add(bc);
        }
        panel.setVisible(true);
        this.setViewportView(panel);
    }

    //data
    private List<RentedBook> rentedBookList;

    public List<ButtonComp> getButtonComps() {
        return buttonComps;
    }

    public void setButtonComps(List<ButtonComp> buttonComps) {
        this.buttonComps = buttonComps;
    }

    private List<ButtonComp> buttonComps;
    //panel
    private JPanel panel;
    private FlowLayout layout;
    public ListRentedBookScroll() {
        initComponent();
    }

    public void initComponent() {
        panel = new JPanel();
        layout = new FlowLayout();
        panel.setLayout(layout);

        this.setPreferredSize(new Dimension(400, 50));
    }

    class FillRentedBook implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}
