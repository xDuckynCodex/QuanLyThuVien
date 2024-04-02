package quanlythuvien;

import quanlythuvien.views.LoginView;

import java.awt.*;
import quanlythuvien.controllers.LoginController;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginView = new LoginView();
                LoginController loginController = new LoginController(loginView);
                loginController.showLoginView();
                loginView.setVisible(true);
            }
        });
    }
}
