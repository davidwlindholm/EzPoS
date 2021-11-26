/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class JPanelBG extends JPanel {
  private Image bgImage;
    
  public JPanelBG(String imagePath) {
      super();
      
      try {
        File f = new File(getClass().getResource("/Resources/bg.png").toURI());
        bgImage = ImageIO.read(f);
      }
      catch (IOException | URISyntaxException e) {
          System.out.println("BG image not found");
      }
  }
    
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
        g.drawImage(bgImage, 0, 0, null);
  }
}
