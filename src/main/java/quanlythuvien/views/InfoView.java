package quanlythuvien.views;

import quanlythuvien.components.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoView extends JFrame {
    public InfoView() {
        initComponent();
    }

    private InputField name, code, publisher, author, quantity, price;
    private ButtonComp addBtn, editBtn, deleteBtn, exitBtn;
    private JLabel title;
    private DatePickPanel datePickPanel;
    private DropDown typeMenu;
    private Card card;
    private final int north = 50;
    private final int west = 250;
    public void initComponent() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // label
        title = new JLabel("Thông tin đối tượng");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        //input field
        name = new InputField("Tên sản phẩm");
        code = new InputField("Mã sản phẩm");
        publisher = new InputField("Nhà xuất bản");
        author = new InputField("Tên tác giả");
        quantity = new InputField("Số lượng");
        price = new InputField("Giá sản phẩm");
        title = new JLabel("Thông tin đối tượng");
        datePickPanel = new DatePickPanel();
        typeMenu = new DropDown();
        //button
        addBtn = new ButtonComp("Thêm");
        addBtn.addClickListener(new AddClickedListener());
        editBtn =new ButtonComp("Sửa");
        editBtn.addClickListener(new EditClickedListener());
        deleteBtn = new ButtonComp("Xóa");
        deleteBtn.addClickListener(new DeleteClickedListener());
        exitBtn = new ButtonComp("Exit");
        exitBtn.addClickListener(new ExitClickedListener());

//        card = new Card();

        //giao dien chinh
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);
        //add element to panel
        panel.add(title);

        panel.add(name);
        panel.add(code);
        panel.add(publisher);
        panel.add(author);
        panel.add(quantity);
        panel.add(price);
        panel.add(datePickPanel);
        panel.add(typeMenu);

        panel.add(addBtn);
        panel.add(editBtn);
        panel.add(deleteBtn);
        panel.add(exitBtn);


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
        //button
        //addBtn
        layout.putConstraint(SpringLayout.WEST, addBtn, west,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
        //editBtn
        layout.putConstraint(SpringLayout.WEST, editBtn, west + 80,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, editBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
        //deleteBtn
        layout.putConstraint(SpringLayout.WEST, deleteBtn, west + 80*2,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, deleteBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);
        //exitBtn
        layout.putConstraint(SpringLayout.WEST, exitBtn, west + 80 * 3,
                SpringLayout.WEST,
                panel);
        layout.putConstraint(SpringLayout.NORTH, exitBtn,
                north + 40 * 8,
                SpringLayout.NORTH,
                panel);

        //set frame
        this.add(panel);
        this.pack();
        this.setTitle("Thông tin đối tượng");
        this.setSize(new Dimension(600,450));
        this.setVisible(true);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public static void main(String[] args) {
        InfoView view = new InfoView();
    }

    // Xử lý listener
    class AddClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler add event
            JOptionPane.showMessageDialog(new JOptionPane(), "add evnt");
        }
    }

    class EditClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler edit event
        }
    }

    class DeleteClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Handler delete event
        }
    }

    class ExitClickedListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


}
