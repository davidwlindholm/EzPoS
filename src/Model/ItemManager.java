/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Model;

import java.util.ArrayList;


public class ItemManager {
    private FileIO fileio;
    private ArrayList<Item> storedItems;
    private ArrayList<Sale> sales;
    private static ItemManager instance = null;
    
    private ItemManager() {
        storedItems = new ArrayList<>();
        sales = new ArrayList<>();
        
        //FIXME - DUMMY DATA
       // storedItems.add(new Item("Cola", "25CL", "5740700982972", 12.0, 25));
       // storedItems.add(new Item("Slik", "200g", "5702016913170", 20.0, 15));
        
    }
    
    private void loadData() {
        fileio = FileIO.getInstance();
        fileio.loadItemList();
    }
    
    public void completeSale(Sale sale) {
        //TODO decrease stock
        //TODO update stock CSV
        //TODO update sale CSV
        fileio.writeSale(sale);
    }
    

    
    public static ItemManager getInstance() {
        if (instance == null) {
            instance = new ItemManager();
            instance.loadData();
        }
        return instance;
    }
    
    public boolean itemExists(String barcode) {
        //TODO
        return true;
    }
    
    public boolean duplicateItem(String barcode) {
        //TODO
        return false;
    }
    
    public Item getItem(String barcode) {
        for (Item item : storedItems) {
            if (item.getBarcode().equals(barcode)) {
                return item;
            }
        }
        return null;
    }
    
    public void addItem(String name, String size, String barcode, double price, int stock) {
        storedItems.add(new Item(name, size, barcode, price, stock));
    }
    
}
