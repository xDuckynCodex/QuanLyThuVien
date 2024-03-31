package quanlythuvien;

import quanlythuvien.views.LoginView;

import java.awt.*;

public class App {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginView loginView = new LoginView();
            }
        });
    }
}
