/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Controller;

import javax.swing.JFrame;
import GUI.MainWindow;


public class StateManager {
    private State curState;
    private MainWindow mainWindow;
    private static StateManager instance = null;
    
    
    private StateManager() { }
    
    public static StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
        }
        return instance;
    }
    
    public void start() {
        mainWindow = new MainWindow();
        changeState(State.MENU);
        mainWindow.setVisible(true);
    }
    
    public void changeState(State newState) {
        curState = newState;
        
        switch(newState) {
            case MENU:
                mainWindow.switchPanel("menu");
                break;
            case SALE_MAIN:
                mainWindow.switchPanel("sales");
                break;
            case INVENTORY_MAIN:
                mainWindow.switchPanel("inventory");
                break;
            case STATISTICS:
                mainWindow.switchPanel("statistics");
                break;
            case CLOSING:
                System.exit(0);
                break;
        }
    }
    
    public State getState() { return curState; }
}
