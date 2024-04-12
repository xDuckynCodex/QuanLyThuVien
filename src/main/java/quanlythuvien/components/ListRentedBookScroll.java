package quanlythuvien.components;

import quanlythuvien.entities.RentedBook;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ListRentedBookScroll extends JScrollPane {
    public List<RentedBook> getRentedBookList() {
        return rentedBookList;
    }

    public void setRentedBookList(List<RentedBook> rentedBookList) {
        this.rentedBookList = rentedBookList;
        panel = new JPanel();
        panel.setLayout(layout);
        for (RentedBook rentedBook : rentedBookList) {
            ButtonComp bc =
                    new ButtonComp(rentedBook.getPublication().getName());
            panel.add(bc);
        }
        panel.setVisible(true);
        this.setViewportView(panel);
    }

    //data
    private List<RentedBook> rentedBookList;
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
}
