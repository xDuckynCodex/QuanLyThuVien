package quanlythuvien;

import quanlythuvien.controllers.AdminController;
import quanlythuvien.utils.FontUtil;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        FontUtil.setFont();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                AdminController adminController = AdminController.getInstance();
                adminController.showLoginView();
            }
        });
    }
}
