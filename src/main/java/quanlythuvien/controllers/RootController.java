package quanlythuvien.controllers;

import quanlythuvien.components.ContextMenu;
import quanlythuvien.components.GridCards;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.LoginView;
import quanlythuvien.views.ManageView;

import java.util.PrimitiveIterator;

public class RootController {
    // view
    private ManageView manageView;
    private LoginView loginView;
    private InfoView infoView;

    //Component
    private GridCards gridCards;

    // controller
    private ManageController manageController;
    private InfoController infoController;
    private LoginController loginController;
    //data
    private PublicationDao publicationDao;

    public RootController()
    {
        initComponent();
    }

    public void initComponent() {
        //data
        publicationDao = new PublicationDao();

        // view
        loginView = new LoginView();
        manageView = new ManageView();
        infoView = new InfoView();

        // controller
        manageController = new ManageController(publicationDao, manageView);
        infoController = new InfoController(infoView);
        loginController = new LoginController(loginView);

        //Component
        gridCards = new GridCards(publicationDao, manageView, infoController);


        //pass instance
        loginController.setManageController(manageController);
        loginController.setManageView(manageView);


        manageController.setInfoController(infoController);

        infoController.setGridCards(gridCards);

        manageView.setGridCards(gridCards);
    }

    public void showLoginView() {
        loginController.showLoginView();
    }

    public void showManageView() {
        manageController.showView();
    }

    public static void main(String[] args) {
        RootController rc = new RootController();
        rc.showManageView();
    }
}
