package quanlythuvien.views;

import quanlythuvien.components.Card;
import quanlythuvien.components.DatePickPanel;
import quanlythuvien.components.DropDown;
import quanlythuvien.components.InputField;

import javax.swing.*;
import java.awt.*;

public class InfoView extends JFrame {
    public InfoView() {
        initComponent();
    }

    private InputField name, code, publisher, author, quantity, price;
    private JLabel title;
    private DatePickPanel datePickPanel;
    private DropDown typeMenu;
    private Card card;
    private final int north = 50;
    private final int west = 250;
    public void initComponent() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        title = new JLabel("Thông tin đối tượng");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        name = new InputField("Tên sản phẩm");
        code = new InputField("Mã sản phẩm");
        publisher = new InputField("Nhà xuất bản");
        author = new InputField("Tên tác giả");
        quantity = new InputField("Số lượng");
        price = new InputField("Giá sản phẩm");
        title = new JLabel("Thông tin đối tượng");
        datePickPanel = new DatePickPanel();
        typeMenu = new DropDown();
//        card = new Card();

        //giao dien chinh
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);

        panel.add(title);
        panel.add(name);
        panel.add(code);
        panel.add(publisher);
        panel.add(author);
        panel.add(quantity);
        panel.add(price);
        panel.add(datePickPanel);
        panel.add(typeMenu);
        //set vi tri
        //title
        layout.putConstraint(SpringLayout.WEST, title, 200, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, title, 10, SpringLayout.NORTH,
                panel);
        //name
        layout.putConstraint(SpringLayout.WEST, name, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, name, north, SpringLayout.NORTH,
                panel);
        //code
        layout.putConstraint(SpringLayout.WEST, code, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, code, north + 40,
                SpringLayout.NORTH,
                panel);
        //publisher
        layout.putConstraint(SpringLayout.WEST, publisher, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, publisher, north + 40 * 2,
                SpringLayout.NORTH,
                panel);
        //author
        layout.putConstraint(SpringLayout.WEST, author, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, author, north + 40 * 3,
                SpringLayout.NORTH,
                panel);
        //quantity
        layout.putConstraint(SpringLayout.WEST, quantity, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, quantity, north + 40 * 4,
                SpringLayout.NORTH,
                panel);
        //price
        layout.putConstraint(SpringLayout.WEST, price, west, SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, price, north + 40 * 5,
                SpringLayout.NORTH,
                panel);
        //date picker
        layout.putConstraint(SpringLayout.WEST, datePickPanel, west,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, datePickPanel,
                north + 40 * 6,
                SpringLayout.NORTH,
                panel);
        //type menu
        layout.putConstraint(SpringLayout.WEST, typeMenu, west,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, typeMenu,
                north + 40 * 7,
                SpringLayout.NORTH,
                panel);

        //set frame
        this.add(panel);
        this.pack();
        this.setTitle("Thông tin đối tượng");
        this.setSize(new Dimension(600,450));
        this.setVisible(true);
    }
}
