/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package GUI;

import Controller.State;
import Controller.StateManager;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MenuPanel extends JPanelBG {
    private JButton closeButton, salesButton, inventoryButton, statisticsButton;
    private Dimension mainButtonSize, smallButtonSize;
    private StateManager stateManager;
    
    public MenuPanel(String bgImage) {
        super(bgImage);
        stateManager = StateManager.getInstance();

        mainButtonSize = new Dimension(160, 160);
        smallButtonSize = new Dimension(80, 80);
        

        
        createComponents();
    }
    
    private void createComponents() {
        setLayout(null);

        salesButton      = GUIUtils.setUpButton("sales", 280, 300, mainButtonSize);
        inventoryButton  = GUIUtils.setUpButton("inventory", 580, 300, mainButtonSize);
        statisticsButton = GUIUtils.setUpButton("statistics", 880, 300, mainButtonSize);
        closeButton      = GUIUtils.setUpButton("close", 1180, 20, smallButtonSize);
        
        GUIUtils.addToPanel(this, salesButton, inventoryButton, statisticsButton, closeButton);
        
        salesButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.changeState(State.SALE_MAIN);
            }
        });
        inventoryButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.changeState(State.INVENTORY_MAIN);
            }
        });
        statisticsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.changeState(State.STATISTICS);
            }
        });
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.changeState(State.CLOSING);
            }
        });
    }
    
    
    
}