package quanlythuvien.controllers;

import quanlythuvien.components.GridCards;
import quanlythuvien.dao.PublicationDao;
import quanlythuvien.dao.RenterDao;
import quanlythuvien.entities.Publication;
import quanlythuvien.views.InfoView;
import quanlythuvien.views.LoginView;
import quanlythuvien.views.ManageView;
import quanlythuvien.views.RenterView;

import java.util.List;
import quanlythuvien.components.TableStatistic;

public class AdminController {
    //data
    private PublicationDao publicationDao;
    private List<Publication> publicationList;
    private RenterDao renterDao;
    // view
    private ManageView manageView;
    private LoginView loginView;
    private InfoView infoView;
    private RenterView renterView;
    //Component
    private GridCards gridCards;

    // controller
    private ManageController manageController;
    private InfoController infoController;
    private LoginController loginController;
    private RenterController renterController;

    public AdminController()
    {
        initComponent();
    }

    public void initComponent() {
        //data
        publicationDao = new PublicationDao();
        publicationList = publicationDao.getListPublication();
        renterDao = new RenterDao();

        // view
        loginView = new LoginView();
        manageView = new ManageView();
        infoView = new InfoView();
        renterView = new RenterView();
        // controller
        manageController = new ManageController(manageView);
        infoController = new InfoController(infoView);
        loginController = new LoginController(loginView);
        renterController = new RenterController(renterView);

        //Component
        gridCards = new GridCards(publicationDao, manageView, infoController);

        //pass instance
        loginController.setManageController(manageController);

        manageController.setInfoController(infoController);
        manageController.setPublicationDao(publicationDao);
        manageController.setRenterController(renterController);
        
        renterController.setPublicationDao(publicationDao);
        renterController.setRenterDao(renterDao);
        renterController.setManageView(manageView);
        renterController.setManageController(manageController);

        infoController.setGridCards(gridCards);
        infoController.setManageView(manageView);
        
        infoView.setRenterView(renterView);
        infoView.setRenterDao(renterDao);

        manageView.setGridCards(gridCards);

        renterView.setPublicationDao(publicationDao);
        renterView.setRenterDao(renterDao);
    }

    public void showLoginView() {
        loginController.showLoginView();
    }

    public void showManageView() {
        manageController.showView();
    }
}
