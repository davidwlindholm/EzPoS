/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package GUI;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class GUIUtils {
    public static void addToPanel(JPanel panel, JComponent... components) {
        for (JComponent comp : components) {
            panel.add(comp);
        }
    }
    
    public static JButton setUpButton(String name, int x, int y, Dimension dim) {
        BufferedImage normal = null;
        BufferedImage pressed = null;
        try {
            normal  = ImageIO.read(GUIUtils.class.getResource("/Resources/" + name + "_normal.png"));
            pressed = ImageIO.read(GUIUtils.class.getResource("/Resources/" + name + "_pressed.png"));
        } 
        catch (IOException e) {
            System.out.println("Error loading button images for: " + name);
        }
        
        JButton button = new JButton(new ImageIcon(normal));
        
        button.setPressedIcon(new ImageIcon(pressed));
        button.setBounds(x, y, dim.width, dim.height);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);
        button.setFocusable(false);

        return button;
    }
}
