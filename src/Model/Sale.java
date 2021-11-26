/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Model;

import java.util.ArrayList;


public class Sale {
    private ItemManager itemManager;
    private ArrayList<SaleItem> saleLines;
    private double discount;
    private Discount discountType;
    
    public Sale() {
        itemManager = ItemManager.getInstance();
        saleLines = new ArrayList<>();
        discount = 0.0;
        discountType = Discount.AMOUNT;
    }
    
    public void sellItem(String barcode) {
        boolean found = false;
        for (SaleItem si : saleLines) {
            if (si.getItemBarcode().equals(barcode)) {
                si.increaseQuantity();
                found = true;
            }
        }
        
        if (!found) {
            Item item = itemManager.getItem(barcode);
            SaleItem saleItem = new SaleItem(item);
            saleLines.add(saleItem);
        }
        

    }
    
    public void removeItem(String barcode) {
        for (SaleItem si : saleLines) {
            if (si.getItemBarcode().equals(barcode)) {
                if (si.getQuantity() > 1) {
                    si.decreaseQuantity();
                }
                else {
                    saleLines.remove(si);
                }
            }
        }
    }
    
    public ArrayList<SaleItem> getSaleLines() { return saleLines; }
    
    public int getNumberOfItems() {
        int amount = 0;
        for (SaleItem si : saleLines) {
            amount += si.getQuantity();
        }
        return amount;
    }
    
    public double getTotal() {
        double tempTotal = 0;
        for (SaleItem si : saleLines) {
            tempTotal += si.getLineTotal();
        }
        
        if (discount > 0) {
            if (discountType == Discount.AMOUNT) {
                tempTotal -= discount;
            }
            else {
                double calcDisc = (tempTotal / 100) * discount;
                tempTotal -= calcDisc;
            }
        }
        
        return (tempTotal < 0 ? 0.0 : tempTotal);
    }
}
