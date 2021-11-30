/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package GUI;

import Controller.State;
import Controller.StateManager;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class StatisticsPanel extends JPanelBG {
    private JButton closeButton; 
    private StateManager stateManager;
    
    public StatisticsPanel(String bgImage) {
        super(bgImage);
        stateManager = StateManager.getInstance();
        createComponents();
    }
    
    private void createComponents() {
        closeButton = GUIUtils.setUpButton("close", 1180, 20, new Dimension(80, 80));
        closeButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                stateManager.changeState(State.MENU);
            }
        });
        add(closeButton);
    }

}
