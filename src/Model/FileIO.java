/** 
 * © David Lindholm - me@davidlindholm.info
 * License: GNU Affero General Public License v3.0
 * Legal: https://www.gnu.org/licenses/agpl-3.0.en.html
 */

package Model;

import java.io.BufferedWriter;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import org.apache.commons.codec.Charsets;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;


public class FileIO {
    private ItemManager itemManager;
    private static FileIO instance = null;
    
    private FileIO() {
        itemManager = ItemManager.getInstance();
    }
    
    public static FileIO getInstance() {
        if (instance == null) {
            instance = new FileIO();
        }
        return instance;
    }
    
    public void loadItemList() {
        try {
            
            File file = new File(getClass().getResource("/Data/items.csv").toURI());

            CSVParser parser = CSVParser.parse(file, Charsets.UTF_8, CSVFormat.DEFAULT);
            List<CSVRecord> records = parser.getRecords();
            
            for (int i = 1; i < records.size(); i++) {
                //System.out.println(records.get(i).get(0));
                itemManager.addItem(records.get(i).get(0), //Name
                                     records.get(i).get(1), //Size
                                     records.get(i).get(2), //Barcode
                                     new Double(records.get(i).get(3)), //Price
                                     new Integer(records.get(i).get(4))); //Stock
                
                
            }
            
            /*
            for (CSVRecord csvRecord : records) {
                csvRecord.get(0)
                System.out.println(csvRecord);
/*                for (int i = 0; i < csvRecord.size(); i++) {
                    System.out.print(csvRecord.get(i) + " ");
                }
                System.out.println("");*/
           /* }*/
        }
        catch (IOException | URISyntaxException e) {
            System.out.println("Unable to read item list");
        }
    }
    
    public void loadExistingSales() {
        try {
            File file = new File(getClass().getResource("/Data/sales.csv").toURI());

            CSVParser parser = CSVParser.parse(file, Charsets.UTF_8, CSVFormat.DEFAULT);
            List<CSVRecord> records = parser.getRecords();
            
            Sale sale = new Sale();
            
            for (int i = 1; i < records.size(); i++) {
                //sale.sellItem(barcode);
                
                
                //System.out.println(records.get(i).get(0));
                itemManager.addItem(records.get(i).get(0), //Name
                                     records.get(i).get(1), //Size
                                     records.get(i).get(2), //Barcode
                                     new Double(records.get(i).get(3)), //Price
                                     new Integer(records.get(i).get(4))); //Stock
                
                
            }
        }
        catch (IOException | URISyntaxException e) {
            System.out.println("Unable to read item list");
        }
        
        //itemManager.addSale(sale, true);
    }
    
    public void writeSale(Sale sale) {
 
        /*
        ArrayList<Sale> test = new ArrayList<>();
        Sale sale1 = new Sale();
        sale1.sellItem("5740700982972");
        sale1.sellItem("5740700982972");
        test.add(sale1);
        Sale sale2 = new Sale();
        sale2.sellItem("5740700982972");
        sale2.sellItem("5702016913170");
        test.add(sale2);
        Sale sale3 = new Sale();
        sale3.sellItem("5740700982972");
        test.add(sale3);*/
        try {
            
            File file = new File(getClass().getResource("/Data/sales.csv").toURI());
            BufferedWriter writer;
            CSVPrinter csvPrinter;
            if (!file.exists()) {
                //System.out.println("hep");
                writer = Files.newBufferedWriter(Paths.get(getClass().getResource("/Data/sales.csv").toURI()));
                csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                csvPrinter.printRecord("Name", "Size", "Quantity", "Total price");
            } else {
                writer = Files.newBufferedWriter(Paths.get(getClass().getResource("/Data/sales.csv").toURI()), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
                csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            }

         
            for (SaleItem si : sale.getSaleLines()) {

                csvPrinter.print(si.getItemName());
                csvPrinter.print(si.getItemSize());
                csvPrinter.print("" + si.getQuantity());
                csvPrinter.print("" + si.getLineTotal());
                csvPrinter.println();
            }
                
                
                
            
            
            csvPrinter.flush();
            
            
           // File file = new File(getClass().getResource("/Data/sales.csv").toURI());
        /*    Writer wr = new FileWriter(file);
            
               
            final CSVPrinter p = new CSVPrinter(wr, CSVFormat.DEFAULT);
            
            p.printRecord("Name", "Size", "Quantity", "Total price");
            for (Sale s : test) {
                for (SaleItem si : s.getSaleLines()) {
                    
                    p.print(si.getItemName());
                    p.print(si.getItemSize());
                    p.print("" + si.getQuantity());
                    p.print("" + si.getLineTotal());
                    p.println();
                }
                
                
                
            }
            p.close();
            wr.close();*/
            
            /*
            for (int i = 1; i < 11; ++i) {
                for (int j = 0; j < 4; ++j) {
                    p.print(i + 10 * j);
                }
                p.println();
            }*/
        }
        catch (IOException | URISyntaxException e) {
            System.out.println("Error writing sale");
        }
    }
}
