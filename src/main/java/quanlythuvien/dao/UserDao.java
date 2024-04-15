package quanlythuvien.dao;

import quanlythuvien.entities.User;

public class UserDao {
    public boolean checkUser(User user) {
        if (user != null) {
            return "admin".equals(user.getUserName())
                    && "admin".equals(user.getPassword());
        }
        return false;
    }
}