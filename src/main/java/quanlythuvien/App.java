package quanlythuvien;

import quanlythuvien.controllers.LoginController;
import quanlythuvien.utils.FontUtil;
import quanlythuvien.views.LoginView;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        FontUtil.setFont();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginView = new LoginView();
                LoginController loginController =
                        new LoginController(loginView);
                loginController.showLoginView();
            }
        });
    }
}
