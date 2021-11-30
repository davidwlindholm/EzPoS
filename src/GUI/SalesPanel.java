/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package GUI;

import Controller.State;
import Controller.StateManager;
import Model.Item;
import Model.ItemManager;
import Model.Sale;
import Model.SaleItem;
import Model.SettingsManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;


public class SalesPanel extends JPanelBG {
    private SettingsManager settingsManager;
    private ItemManager itemManager;
    private StateManager stateManager;
    private JScrollPane tableScroller;
    private JTable salesTable;
    private JButton payButton, removeButton, cancelButton, discountbutton, closeButton;
    private DefaultTableModel model;
    private Sale sale;
    private JLabel instructionLabel, totalHeaderLabel, totalAmountLabel;
    
    public SalesPanel(String bgImage) {
        super(bgImage);
        settingsManager = SettingsManager.getInstance();
        itemManager = ItemManager.getInstance();
        stateManager = StateManager.getInstance();
        setLayout(null);
        createComponents();
    }
    
    private void createComponents() {
        salesTable = new JTable();
        salesTable.setFont(new Font("Verdana", Font.PLAIN, 20));
        
        updateSalesTable();
        
        //JTableHeader header = new JTableHeader(settingsManager.getSalesTableHeaders());
        //salesTable.setTableHeader(header);
        //model = (DefaultTableModel) salesTable.getModel();
        tableScroller = new JScrollPane(salesTable);
        //salesTable.
        //salesTable.setTableHeader(settingsManager.getSalesTableHeaders());
        //salesTable.setBounds(10, 10, 790, 390);
       // salesTable.setFont(font);
        //tableScroller.setLayout(new FlowLayout());
        tableScroller.setBounds(20, 130, 1000, 550);
        //tableScroller.add(salesTable);
        //tableScroller.revalidate();
        //salesTable.set
        
     //   add(salesTable);
     
        closeButton = GUIUtils.setUpButton("close", 1180, 20, new Dimension(80, 80));
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.changeState(State.MENU);
            }
        });
     
        instructionLabel = new JLabel(settingsManager.getSaleAddRemoveText()[0]);
        instructionLabel.setForeground(settingsManager.getSaleAddRemoveColor()[0]);
        instructionLabel.setFont(new Font("Verdana", Font.BOLD, 80));
        instructionLabel.setBounds(30, 10, 800, 90);
        
        totalHeaderLabel = new JLabel("Total:");
        totalHeaderLabel.setForeground(Color.WHITE);
        totalHeaderLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        totalHeaderLabel.setBounds(1055, 390, 220, 50);
        
        totalAmountLabel = new JLabel("0,00 kr");
        totalAmountLabel.setForeground(Color.WHITE);
        totalAmountLabel.setFont(new Font("Verdana", Font.BOLD, 40));
        totalAmountLabel.setBounds(1055, 450, 220, 50);
     
        cancelButton = new JButton("Annuller");
        cancelButton.setBounds(1050, 138, 200, 100);
        cancelButton.setFont(new Font("Verdana", Font.BOLD, 28));
        cancelButton.setFocusable(false);
        cancelButton.setOpaque(true);
        cancelButton.setBackground(Color.RED);
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelSale();
            }
        });
        
        removeButton = new JButton("Slet vare");
        removeButton.setBounds(1050, 250, 200, 100);
        removeButton.setBackground(Color.ORANGE);
        removeButton.setFont(new Font("Verdana", Font.BOLD, 28));
        removeButton.setOpaque(true);
        removeButton.setFocusable(false);
        removeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stateManager.getState() == State.SALE_MAIN) {
                    stateManager.changeState(State.SALE_REMOVE);
                    instructionLabel.setText(settingsManager.getSaleAddRemoveText()[1]);
                    instructionLabel.setForeground(settingsManager.getSaleAddRemoveColor()[1]);
                    removeButton.setText("Sælg vare");
                }
                else {
                    stateManager.changeState(State.SALE_MAIN);
                    instructionLabel.setText(settingsManager.getSaleAddRemoveText()[0]);
                    instructionLabel.setForeground(settingsManager.getSaleAddRemoveColor()[0]);
                    removeButton.setText("Slet vare");
                }
            }
        });
        
        payButton = new JButton("Betalt");
        payButton.setBounds(1050, 550, 200, 100);
        payButton.setFocusable(false);
        payButton.setFont(new Font("Verdana", Font.BOLD, 28));
        payButton.setOpaque(true);
        payButton.setBackground(Color.GREEN);
        payButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(sale == null)) {
                    completeSale();
                }
            }
            
        });
        
        
        GUIUtils.addToPanel(this, closeButton, instructionLabel, tableScroller, cancelButton, removeButton, totalAmountLabel, totalHeaderLabel,payButton);
    }
    
    public void updateSalesTable() {
        model = new DefaultTableModel();
      //  System.out.println(model);
        model.addColumn(settingsManager.getSalesTableHeaders()[0]);
        model.addColumn(settingsManager.getSalesTableHeaders()[1]);
        model.addColumn(settingsManager.getSalesTableHeaders()[2]);
        model.addColumn(settingsManager.getSalesTableHeaders()[3]);
        
        JTableHeader tableHeader = salesTable.getTableHeader();
        //tableHeader.setBackground(Color.black);
        //tableHeader.setForeground(Color.white);
        Font headerFont = new Font("Verdana", Font.BOLD, 20);
        tableHeader.setFont(headerFont);
        
        //System.out.println(sale);
        if (!(sale == null)) {
            //System.out.println(sale.getSaleLines().size());
            for (SaleItem si : sale.getSaleLines()) {
                Item item = itemManager.getItem(si.getItemBarcode());
                Object[] row = new Object[]{item.getName(), item.getSize(), si.getQuantity(), item.getPrice()};
                model.addRow(row);
            }
        }
      //  System.out.println(model);
      //  System.out.println(salesTable);
        salesTable.setModel(model);
        if (!(sale == null)){
            DecimalFormat df = new DecimalFormat("#.00"); 
            
            totalAmountLabel.setText(df.format(sale.getTotal()) + " kr");
        }
    }
    
    public void addToSale(String barcode) {
        if (sale == null) {
            sale = new Sale();
        }
        sale.sellItem(barcode);
        updateSalesTable();
    }
    
    public void removeFromSale(String barcode) {
        if (!(sale == null)) {
            if (sale.getNumberOfItems() > 1) {
                sale.removeItem(barcode);
                updateSalesTable();
            }
            else {
                cancelSale();
            }
            
        }
    }
    
    public void completeSale() {
        itemManager.completeSale(sale);
        sale = null;
        updateSalesTable();
        totalAmountLabel.setText("0,00 kr");
    }
    
    public void cancelSale() {
        sale = null;
        updateSalesTable();
        //DecimalFormat df = new DecimalFormat("#.00"); 
        totalAmountLabel.setText("0,00 kr");
    }
    

}
