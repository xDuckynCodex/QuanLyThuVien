package quanlythuvien.views;

import quanlythuvien.components.ButtonComp;
import quanlythuvien.components.GridCards;
import quanlythuvien.components.InputField;

import quanlythuvien.components.TableStatistic;
import quanlythuvien.dao.PublicationDao;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.List;
import java.awt.event.ActionListener;
import quanlythuvien.entities.Publication;


public class ManageView extends JFrame {
    public void setGridCards(GridCards gridCards) {
        this.gridCards = gridCards;
        panel.add(gridCards);
        //gridCard component
        layout.putConstraint(SpringLayout.EAST, gridCards, -100,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.SOUTH, gridCards, 0,
                SpringLayout.SOUTH, panel);
    }
  
    public void setTableStatistic() {
        this.tableStatistic.calculateData();

    }
    private JPanel panel;
    private TableStatistic tableStatistic;
    private SpringLayout layout;
    //components
    private GridCards gridCards;
    private InputField searchField;
    private ButtonComp addBtn;
    private JButton filterBtn;

    private ButtonComp transferBtn;
    private PublicationDao publicationDao;
    
    private JComboBox typeSelection;
    private String typeString;
    private final String[] types = {"Choose type", "Book", "Magazine", "Novel", "Newspaper"};

    private JLabel frameLabel;
    
    PublicationDao pubDao = new PublicationDao();
    public void initComponent() {
        searchField = new InputField("Tìm kiếm ấn phẩm: ", 20);
        frameLabel = new JLabel("Quản lý ấn phẩm");
        frameLabel.setFont(new Font(frameLabel.getFont().getName(),
                Font.PLAIN, 40));

        addBtn = new ButtonComp("Thêm ấn phẩm");
        transferBtn = new ButtonComp("Chuyển sang quản lý người mượn");
        filterBtn = new JButton("Lọc");
        tableStatistic = new TableStatistic();
        
        
        typeSelection = new JComboBox(types);
//        typeString = String.valueOf(typeSelection.getItemAt(typeSelection.getSelectedIndex()));

        //layout giao dien
        layout = new SpringLayout();
        panel = new JPanel();
        panel.setLayout(layout);
        panel.setSize(1900, 1000);

        //add element to panel
        //component
        panel.add(searchField);
        panel.add(addBtn);
        panel.add(transferBtn);
        panel.add(tableStatistic);
        panel.add(filterBtn);
        
        // select type
        panel.add(typeSelection);
       
        //label
        panel.add(frameLabel);
        
        //set vi tri
        //search component
        layout.putConstraint(SpringLayout.WEST, searchField, 350,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, searchField, 75,
                SpringLayout.NORTH, panel);
        
        // select type component
        layout.putConstraint(SpringLayout.WEST, typeSelection, 1000,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, typeSelection, 73,
                SpringLayout.NORTH, panel);
        
        //addBtn and filterBtn
        layout.putConstraint(SpringLayout.WEST, addBtn, 780,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 73,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, addBtn, 780,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, addBtn, 73,
                SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, filterBtn, 1130,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, filterBtn, 73,
                SpringLayout.NORTH, panel);
        
        //transferBtn
        layout.putConstraint(SpringLayout.EAST, transferBtn, -100,
                SpringLayout.EAST, panel);
        layout.putConstraint(SpringLayout.NORTH, transferBtn, 73,
                SpringLayout.NORTH, panel);
        
        //label
        layout.putConstraint(SpringLayout.WEST, frameLabel, 800,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, frameLabel, 10,
                SpringLayout.NORTH, panel);
        
        //tableStatistic
        layout.putConstraint(SpringLayout.WEST, tableStatistic, 0,
                SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, tableStatistic, 300,
                SpringLayout.NORTH, panel);
        
        //set frame
        this.add(panel);
        this.pack();
        this.setTitle("Manage View");
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public JComboBox getTypeSelection() {
        return typeSelection;
    }
    
    public void filterByType(String typeString){    
        List<Publication> list = pubDao.getListPublication();
        for(Publication p : list){
            if(p.getType().equals(typeString)){
                showView(typeString);
            }
        }
    }

    public ManageView() {
        initComponent();
    }

    public ManageView(PublicationDao publicationDao) {
        this.publicationDao = publicationDao;
        initComponent();
    }

    public void showView() {
        gridCards.setCardList();
        this.setVisible(true);
    }

    public void showView(String searchText) {
        gridCards.setCardList(searchText);
        this.setVisible(true);
    }
    
    public void filter(){
        typeString = (String) typeSelection.getItemAt(typeSelection.getSelectedIndex());
        typeString = typeString.equals("Choose type") ? "" : typeString;
        gridCards.filterByType(typeString);
        this.setVisible(true);
    }
    
    public String getSearchField() {
        return searchField.getTextField();
    }

    //Thêm sự kiên sự kiện
    public void setSearchFieldOnChangeListener(DocumentListener listener) {
        searchField.addFieldChange(listener);
    }

    public void setAddBtnClickListener(ActionListener listener) {
        addBtn.onClickListener(listener);
    }
    
    public void setFilterByTypeBtnClickListener(ActionListener listener){
        filterBtn.addActionListener(listener);
    }

    public void setTransferClickListener(ActionListener listener) {
        transferBtn.onClickListener(listener);
    }
}
