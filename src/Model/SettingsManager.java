/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Model;

import java.awt.Color;
import java.awt.Dimension;


public class SettingsManager {
    private static SettingsManager instance = null;
    private String title;
    private double version;
    private int[] windowSize;
    private String bgImage;
    private String[] salesTableHeaders;
    private String[] saleAddRemoveText;
    private Color[] saleAddRemoveColor;
    
    private SettingsManager() {
        //FIXME - DUMMY DATA
        title = "EzPoS - Easy Point of Sale";
        version = 0.1;
        windowSize = new int[]{1280, 720};
        bgImage = "bg.png";
        salesTableHeaders = new String[]{"Varenavn", "Størrelse", "Antal", "Stk pris"};
        saleAddRemoveText = new String[]{"Scan for at sælge", "Scan for at fjerne"};
        saleAddRemoveColor = new Color[]{Color.WHITE, Color.RED};
    }
    
    public static SettingsManager getInstance() {
        if (instance == null) {
            instance = new SettingsManager();
        }
        return instance;
    }

    public String getTitle() {
        return title;
    }

    public double getVersion() {
        return version;
    }

    public int[] getWindowSize() {
        return windowSize;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public void setWindowSize(int[] windowSize) {
        this.windowSize = windowSize;
    }

    public String getBgImage() {
        return bgImage;
    }

    public void setBgImage(String bgImage) {
        this.bgImage = bgImage;
    }

    public String[] getSalesTableHeaders() {
        return salesTableHeaders;
    }

    public void setSalesTableHeaders(String[] salesTableHeaders) {
        this.salesTableHeaders = salesTableHeaders;
    }

    public String[] getSaleAddRemoveText() {
        return saleAddRemoveText;
    }

    public Color[] getSaleAddRemoveColor() {
        return saleAddRemoveColor;
    }

    public void setSaleAddRemoveText(String[] saleAddRemoveText) {
        this.saleAddRemoveText = saleAddRemoveText;
    }

    public void setSaleAddRemoveColor(Color[] saleAddRemoveColor) {
        this.saleAddRemoveColor = saleAddRemoveColor;
    }
    
    
    
}
