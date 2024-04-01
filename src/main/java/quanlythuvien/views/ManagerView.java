package quanlythuvien.views;


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Thiết kế views của giao diện quản lý
public class ManagerView extends JFrame implements ActionListener, ListSelectionListener {
    // nút
    private  JButton addPublicationBtn;
    private JButton editPublicationBtn;
    private JButton deletePublicationBtn;
    private JButton clearBtn;
    // label nhập
    private JLabel nameLabel;
    private JLabel codeLabel;
    private JLabel publisherLabel;
    private  JLabel authorLabel;
    private JLabel publishedDateLabel;
    private JLabel quantityLabel;
    private  JLabel priceLabel;
    private JLabel typeLabel;

    // field nhập
    private JTextField nameField;
    private JTextField codeField;
    private JTextField publisherField;
    private JTextField authorField;
    private JTextField publishedDateField;
    private JTextField quantityField;
    private JTextField priceField;
    private JTextField typeField;

    // bảng
    private  JScrollPane jScrollTable;
    private  JTable table;

    // cột của table
    private String [] columnNames = new String [] {
            "STT", "Tên ấn phẩm", "Mã số", "NXB", "Tác giả", "NgXB", "SL",
            "Giá", "Thể loại"};
    private Object data = new Object [][] {};
    public ManagerView() {
        initComponent();
    }
    public void initComponent() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // khởi tạo phim chức năng
        addPublicationBtn = new JButton("Thêm");
        editPublicationBtn = new JButton("Sửa");
        deletePublicationBtn = new JButton("Xóa");
        clearBtn = new JButton("Clear");

        // khơi tạo label
        nameLabel = new JLabel("Tên ấn phẩm");
        codeLabel = new JLabel("Mã sản phẩm");
        publisherLabel = new JLabel("Nhà xuất bản");
        authorLabel = new JLabel("Tác giả");
        publishedDateLabel = new JLabel("Ngày xuất bản");
        quantityLabel = new JLabel("Số lượng");
        priceLabel = new JLabel("Giá");
        typeLabel = new JLabel("Thể loại");

        //khởi tạo field nhập
        nameField = new JTextField(20);
        codeField = new JTextField(20);
        publisherField = new JTextField(20);
        authorField = new JTextField(20);
        publishedDateField = new JTextField(20);
        quantityField = new JTextField(20);
        priceField = new JTextField(20);
        typeField = new JTextField(20);

        // Tạo bảng lưu dữ liệu
        jScrollTable = new JScrollPane();
        table = new JTable();

        //cài đặt bảng
        table.setModel(new DefaultTableModel((Object[][]) data, columnNames));
        jScrollTable.setViewportView(table);
        jScrollTable.setPreferredSize(new Dimension(1350, 980));

        // Tạo layout của giao diện
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        panel.setSize(1920, 1080);
        panel.setLayout(layout);
        panel.add(jScrollTable);

        panel.add(addPublicationBtn);
        panel.add(editPublicationBtn);
        panel.add(deletePublicationBtn);
        panel.add(clearBtn);

        panel.add(nameLabel);
        panel.add(codeLabel);
        panel.add(publisherLabel);
        panel.add(authorLabel);
        panel.add(publishedDateLabel);
        panel.add(quantityLabel);
        panel.add(priceLabel);
        panel.add(typeLabel);

        panel.add(nameField);
        panel.add(codeField);
        panel.add(publisherField);
        panel.add(authorField);
        panel.add(publishedDateField);
        panel.add(quantityField);
        panel.add(priceField);
        panel.add(typeField);


        //set vị trí các phần thử của giao diện.
        // label
        layout.putConstraint(SpringLayout.WEST, nameLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameLabel, 10,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, codeLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, codeLabel, 40,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publisherLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publisherLabel, 70,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, authorLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, authorLabel, 100,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publishedDateLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publishedDateLabel, 130,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityLabel, 160,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceLabel, 190,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeLabel, 10,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeLabel, 220,
                SpringLayout.NORTH, panel);
        // field
        layout.putConstraint(SpringLayout.WEST, nameField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, nameField, 10,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, codeField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, codeField, 40,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publisherField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publisherField, 70,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, authorField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, authorField, 100,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, publishedDateField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, publishedDateField, 130,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, quantityField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, quantityField, 160,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, priceField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, priceField, 190,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, typeField, 100,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeField, 220,
                SpringLayout.NORTH, panel);
        // button
        layout.putConstraint(SpringLayout.WEST, addPublicationBtn, 30,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addPublicationBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, editPublicationBtn, 130,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, editPublicationBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, deletePublicationBtn, 230,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, deletePublicationBtn, 250,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, clearBtn, 330,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, clearBtn, 250,
                SpringLayout.NORTH, panel);
        // table
        layout.putConstraint(SpringLayout.EAST, jScrollTable, 0,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, jScrollTable, 0,
                SpringLayout.NORTH, panel);
        //
        this.add(panel);
        this.pack();
        // set jFrame
        this.setTitle("Manager View");
//        this.setSize(1920, 1080);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setVisible(true);
        this.setResizable(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }

    public static void main(String[] args) {
        ManagerView managerView = new ManagerView();
        managerView.setVisible(true);
    }
}
