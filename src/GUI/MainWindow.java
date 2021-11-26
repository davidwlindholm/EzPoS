/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package GUI;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import Model.ItemManager;
import Model.SettingsManager;
import Controller.StateManager;
import Controller.State;
import java.awt.CardLayout;


public class MainWindow extends JFrame {
    private String barcodeBuffer;
    private JPanel cardPanel, menuPanel, statisticsPanel;
    private SalesPanel salesPanel;
    private InventoryPanel inventoryPanel;
    private final ItemManager itemManager;
    private final StateManager stateManager;
    private final SettingsManager settingsManager;
    private CardLayout cardLayout;

    public MainWindow() {
        //Managers
        itemManager = ItemManager.getInstance();
        stateManager = StateManager.getInstance();
        settingsManager = SettingsManager.getInstance();
        
        //Window Settings
        setSize(settingsManager.getWindowSize()[0], settingsManager.getWindowSize()[1]);
        setTitle(settingsManager.getTitle() + " " + settingsManager.getVersion());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        //setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        
        //General init
        barcodeBuffer = "";
        
        //GTK Look & Feel
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        SwingUtilities.updateComponentTreeUI(this);
       
        //Hand scanner input
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new TastaturManager());
        
        //Window contents
        createComponents();
    }
    
    private void createComponents() {
        cardLayout = new CardLayout();
        String bgImage = "/Resources/" + settingsManager.getBgImage();
        cardPanel = new JPanel(cardLayout);
        menuPanel = new MenuPanel(bgImage);
        salesPanel = new SalesPanel(bgImage);
        inventoryPanel = new InventoryPanel(bgImage);
        statisticsPanel = new StatisticsPanel(bgImage);
        
        cardPanel.add(menuPanel, "menu");
        cardPanel.add(salesPanel, "sales");
        cardPanel.add(inventoryPanel, "inventory");
        cardPanel.add(statisticsPanel, "statistics");
        
        add(cardPanel);
        
    }
    
    public void switchPanel(String name) {
        cardLayout.show(cardPanel, name);
    }
    
    private class TastaturManager implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (stateManager.getState() == State.SALE_MAIN) {
                        salesPanel.addToSale(barcodeBuffer);
                    }
                    else if (stateManager.getState() == State.SALE_REMOVE) {
                        salesPanel.removeFromSale(barcodeBuffer);
                    }
                    //System.out.println(barcodeBuffer);
                    barcodeBuffer = "";
                }
                else {
                    KeyStroke ks = KeyStroke.getKeyStrokeForEvent(e);
                    String key = ks.toString();
                    key = key.replaceAll("released ", "");
                    barcodeBuffer += key;
                }
       
            }
            
            return false;
        }
    
    }
    
}
