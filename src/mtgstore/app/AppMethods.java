
package mtgstore.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Scanner;
import org.apache.commons.mail.EmailException;


public class AppMethods {
    static Scanner sc = new Scanner(System.in);
    static String parent = System.getProperty("user.dir")+ File.separator +"data";
    static File dataDirectory = new File(parent);
    public static void nacitanieSkladu() throws IOException{
        
        
        while (true) {
                try {
                    System.out.println("Zadej nazev vstupniho souboru");
                    StockList.loadInventory(new File(dataDirectory, sc.next()));
                    String thisisdumb = sc.nextLine();
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadej znova");
                }catch(RuntimeException e){
                    System.out.println(e.getMessage());
                    System.out.println("Nektery udaj v souboru je spatne/nazev souboru je zadan spatne. Oprav to.");
                }
            }
    }
    
        public static void kartaNaSklade()throws FileNotFoundException{
        System.out.println("Zadajte nazov hladanej karty:");
        //String thisisdumb = sc.nextLine();
        String karta = sc.nextLine();
        ListIterator itr = StockList.stock.listIterator();
        while(itr.hasNext()){
            Card card = (Card)itr.next();
            if(card.getName().toUpperCase().equals(karta.toUpperCase())){
                System.out.println(card.getName() + ", " + card.getEdition() + " - pocet kusov na sklade: " + card.getStock());
            }
        }
    }
        
        public static void mailObjednavka() throws IOException, EmailException{
        System.out.println("Zadajte ID objednavky:");
        int ID = sc.nextInt();
        String thisisdumb = sc.nextLine();
        Order objednavka = new Order(ID);
        Order.loadOrder(new File(dataDirectory, String.valueOf(ID)+".txt"));
        SendMail.sendOrderMail(objednavka);
    }
        
        public static void vymazPolozku() throws IOException{
            System.out.println("Zadajte ID objednavky:");
            int ID = sc.nextInt();
            String thisisdumb = sc.nextLine();
            Order objednavka = new Order(ID);
            Order.loadOrder(new File(dataDirectory, String.valueOf(ID)+".txt"));
            System.out.println("Zadajte nazov karty ktoru chcete odstranit:");
            String karta = sc.next();
            thisisdumb = sc.nextLine();
            objednavka.removeCard(karta);
            System.out.println(objednavka);
            System.out.println("Karta bola odstranena");
        }
        
        public static void zistiCenu() throws IOException{
            System.out.println("Zadajte ID objednavky:");
            int ID = sc.nextInt();
            String thisisdumb = sc.nextLine();
            Order objednavka = new Order(ID);
            Order.loadOrder(new File(dataDirectory, String.valueOf(ID)+".txt"));
            System.out.println("Cena objednavky je: " + Order.getPriceOfOrder(objednavka));
        }
}
