package quanlythuvien.controllers;

import quanlythuvien.components.GridCards;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.LoginView;
import quanlythuvien.views.ManageView;
import quanlythuvien.views.RenterView;

import quanlythuvien.views.PayerView;

public class AdminController {
    private static AdminController adminController;
    public static AdminController getInstance() {
        if (adminController == null) {
            adminController = new AdminController();
        }
        return adminController;
    }

    //data
    private PublicationDao publicationDao;
    private RenterDao renterDao;
    // view
    private ManageView manageView;
    private LoginView loginView;
    private InfoView infoView;
    private RenterView renterView;
    private PayerView payerView;
    //Component
    private GridCards gridCards;

    // controller
    private ManageController manageController;
    private InfoController infoController;
    private LoginController loginController;
    private RenterController renterController;
    private PayerController payerController;

    public AdminController()
    {
        initComponent();
    }

    public void initComponent() {
        //data
        publicationDao = new PublicationDao();
        renterDao = new RenterDao();

        // view
        loginView = new LoginView();
        manageView = new ManageView();
        infoView = new InfoView();
        renterView = new RenterView();
        payerView = new PayerView();
        // controller
        manageController = new ManageController(manageView);
        infoController = new InfoController(infoView);
        loginController = new LoginController(loginView);
        renterController = new RenterController(renterView);
        payerController = new PayerController(payerView);

        //Component
        gridCards = new GridCards(publicationDao, manageView, infoController);

        //pass instance
        loginController.setManageController(manageController);

        manageController.setInfoController(infoController);
        manageController.setPublicationDao(publicationDao);
        manageController.setRenterController(renterController);
        manageController.setPayerController(payerController);
        
        renterController.setPublicationDao(publicationDao);
        renterController.setRenterDao(renterDao);
        renterController.setManageView(manageView);
        renterController.setManageController(manageController);
        renterController.setPayerController(payerController);
        
        payerController.setManageController(manageController);
        payerController.setRenterController(renterController);
        payerController.setRenterDao(renterDao);

        infoController.setGridCards(gridCards);
        infoController.setManageView(manageView);


        manageView.setGridCards(gridCards);

        renterView.setPublicationDao(publicationDao);
        renterView.setRenterDao(renterDao);
    }

    public void showLoginView() {
        loginController.showLoginView();
    }
    public GridCards getGridCards() {
        return gridCards;
    }
}
