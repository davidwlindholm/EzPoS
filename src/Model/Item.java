/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Model;


public class Item {
    private String name;
    private String size;
    private String barcode;
    private double price;
    private int    stock;
    
    public Item(String name, String size, String barcode, double price, int stock) {
        this.name = name;
        this.size = size;
        this.barcode = barcode;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public String getBarcode() {
        return barcode;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
