package quanlythuvien.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import quanlythuvien.dao.UserDao;
import quanlythuvien.entities.User;
import quanlythuvien.views.LoginView;
import quanlythuvien.views.ManageView;
import quanlythuvien.views.ManagerView;
//import quanlythuvien.views.StudentView;

public class LoginController {
    private UserDao userDao;
    private LoginView loginView;
    private  ManageController manageController;
    private ManageView manageView;

    public void setManageController(ManageController manageController) {
        this.manageController = manageController;
    }

    public void setManageView(ManageView manageView) {
        this.manageView = manageView;
    }

//    private StudentView studentView;

    public LoginController(LoginView view) {
        this.loginView = view;
        iniComponent();
        view.addLoginListener(new LoginListener());
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
}
