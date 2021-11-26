/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Model;


public class SaleItem {
    private Item     item;
    private int      quantity;
    private double   discount;
    private Discount discountType;
    
    public SaleItem(Item item) {
        this.item = item;
        quantity = 1;
        discount = 0.0;
        discountType = Discount.AMOUNT;
    }
    
    public void setDiscount(double discount)           { this.discount     = discount;     }
    public void setDiscountType(Discount discountType) { this.discountType = discountType; }
    
    public void increaseQuantity() { quantity++; }
    public void decreaseQuantity() { quantity--; }
    
    public int    getQuantity()    { return quantity;          }
    public String getItemBarcode() { return item.getBarcode(); }
    public String getItemName() { return item.getName(); }
    public String getItemSize() { return item.getSize(); }
    
    public double getLineTotal() {
        double total = 0.0;
        
        if (discount > 0) {
            if (discountType == Discount.AMOUNT) {
                total = (item.getPrice() * quantity) - discount;
            }
            else if (discountType == Discount.AMOUNT_SINGLE) {
                total = (item.getPrice() - discount) + (item.getPrice() * (quantity - 1));
            }
            else if (discountType == Discount.PERCENT) {
                double tempTotal = item.getPrice() * quantity;
                double calcDisc  = (tempTotal / 100) * discount;
                total            = tempTotal - calcDisc;
            }
            else { //Discount.PERCENT_SINGLE
                double tempTotal      = item.getPrice() * (quantity - 1);
                double itemDiscount   = (item.getPrice() / 100) * discount;
                double discountedItem = (item.getPrice() < itemDiscount ? 0 : item.getPrice() - itemDiscount);
                total                 = tempTotal + discountedItem;
            }
        }
        else {
            total = item.getPrice() * quantity;
        }

        return total;
    }
}
