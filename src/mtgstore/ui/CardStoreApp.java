
package mtgstore.ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import mtgstore.app.AppMethods;
import mtgstore.app.Order;
import static mtgstore.app.Order.loadOrder;
import mtgstore.app.StockList;
import org.apache.commons.mail.EmailException;

public class CardStoreApp {
    static Scanner sc = new Scanner(System.in);
    static Order order;
    static StockList stock;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws IOException, EmailException {
        stock = new StockList();
        boolean aplikacia = true;
        int choice;
        AppMethods.nacitanieSkladu();
        sb.append("1: Stav skladu").append("\n");
        sb.append("2: Mame kartu na sklade?").append("\n");
        sb.append("3: Posli mail o spracovani objednavky").append("\n");
        sb.append("4: Vymaz polozku z objednavky").append("\n");
        sb.append("5: Zisti cenu objednavky").append("\n");
        sb.append("6: Ukonci aplikaciu");
        while(aplikacia){           
            try{
            System.out.println(sb.toString());
            System.out.println("Zadej pokyn:");
            choice = Integer.parseInt(sc.nextLine());
            //String thisisdumb = sc.nextLine();
            switch(choice){
                case 1:
                    stavSkladu();
                    break;
                case 2:
                    AppMethods.kartaNaSklade();
                    break;
                case 3:
                    AppMethods.mailObjednavka();                          
                    break;
                case 4:
                    AppMethods.vymazPolozku();
                    break;
                case 5:
                    AppMethods.zistiCenu();                            
                    break;
                case 6:
                    aplikacia = false;
                    break;
                default:
                    System.out.println("Zadali ste nespravne cislo, skuste znovu");
                    break;
            }
            }catch(NumberFormatException e){
                System.out.println("Nespravny vstup, skuste znovu prosim.");
            }
        }
        
    }
    
    
    
    
    public static void stavSkladu() throws IOException{       
        System.out.println(stock.toString());
    }
    
}
