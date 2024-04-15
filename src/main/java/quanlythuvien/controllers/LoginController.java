package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import quanlythuvien.dao.UserDao;
import quanlythuvien.entities.User;
import quanlythuvien.views.LoginView;
import quanlythuvien.views.ManageView;
//import quanlythuvien.views.StudentView;

public class LoginController {
    private UserDao userDao;
    private final LoginView loginView;
    private  ManageController manageController;

    public void setManageController(ManageController manageController) {
        this.manageController = manageController;
    }


//    private StudentView studentView;

    public LoginController(LoginView view) {
        this.loginView = view;
        iniComponent();
        loginView.addLoginListener(new LoginListener());
        loginView.addPressEnterListener(new PressEnterListener());
    }

    public void iniComponent() {
        this.userDao = new UserDao();
    }


    public void showLoginView() {
        loginView.setVisible(true);
    }

    /**
     * Lớp LoginListener
     * chứa cài đặt cho sự kiện click button "Login"
     *
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                manageController.showView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }

    class PressEnterListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                User user = loginView.getUser();
                if (userDao.checkUser(user)) {
                    // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                    manageController.showView();
                    loginView.setVisible(false);
                } else {
                    loginView.showMessage("username hoặc password không đúng.");
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}
