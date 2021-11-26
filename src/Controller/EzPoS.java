/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Controller;

import javax.swing.JFrame;
import GUI.MenuPanel;
import Model.FileIO;


public class EzPoS {

    public static void main(String[] args) {
        StateManager stateManager = StateManager.getInstance();
        stateManager.start();
        

    }

}
