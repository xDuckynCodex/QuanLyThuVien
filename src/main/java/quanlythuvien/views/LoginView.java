package quanlythuvien.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import quanlythuvien.entities.User;

public class LoginView extends JFrame implements ActionListener {
//    private static final long serialVersionUID = 1L;
    private JLabel userNameLabel;
    private JLabel passwordlabel;
    private JLabel title;
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JButton loginBtn;
    private JLabel img;

    public LoginView() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        userNameLabel = new JLabel("UserName");
        passwordlabel = new JLabel("Password");
        title = new JLabel("ỨNG DỤNG QUẢN LÝ ẤN PHẨM THƯ VIỆN");
        img = new JLabel();
        userNameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginBtn = new JButton();
        loginBtn.setBackground(Color.darkGray);
        loginBtn.setForeground(Color.white);
        
        userNameLabel.setForeground(Color.black);
        passwordlabel.setForeground(Color.black);
        title.setForeground(Color.black);
        
        title.setFont(new Font("Serif", Font.PLAIN, 25));
        
        img.setHorizontalAlignment(SwingConstants.LEFT);
        img.setBounds(0, 0, 400, 300);
        img.setIcon(new ImageIcon("..\\public\\images\\bg.jpg"));

        loginBtn.setText("Login");
        loginBtn.addActionListener(this);
//
//        // tạo spring layout
        SpringLayout layout = new SpringLayout();
        JPanel panel = new JPanel();
        // tạo đối tượng panel để chứa các thành phần của màn hình login
        panel.setSize(300, 200);
        panel.setLayout(layout);
        panel.add(userNameLabel);
        panel.add(passwordlabel);
        panel.add(title);
        panel.add(userNameField);
        panel.add(passwordField);
        panel.add(loginBtn);
        panel.add(img);

        // cài đặt vị trí các thành phần trên màn hình login
        layout.putConstraint(SpringLayout.WEST, userNameLabel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, userNameLabel, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordlabel, 100, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, passwordlabel, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, title, 50, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, title, 100, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, userNameField, 100, SpringLayout.WEST, userNameLabel);
        layout.putConstraint(SpringLayout.NORTH, userNameField, 200, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, passwordField, 100, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, passwordField, 230, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, loginBtn, 150, SpringLayout.WEST, passwordlabel);
        layout.putConstraint(SpringLayout.NORTH, loginBtn, 280, SpringLayout.NORTH, panel);

//        // add panel tới JFrame
        this.add(panel);

//        // cài đặt các thuộc tính cho JFrame
        this.setTitle("Login");
        this.setSize(600, 400);
        this.setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public User getUser() {
        return new User(userNameField.getText(),
        String.copyValueOf(passwordField.getPassword()));
    }

    public void actionPerformed(ActionEvent e) {
    }

    public void addLoginListener(ActionListener listener) {
        loginBtn.addActionListener(listener);
    }

    public void addPressEnterListener(KeyListener listener) {
        userNameField.addKeyListener(listener);
        passwordField.addKeyListener(listener);
    }
}