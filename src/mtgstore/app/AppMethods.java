
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
    
    /**
     * nacita sklad, vypyta si meno suboru
     * @throws IOException 
     */
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
        /**
         * podla mena karty zisti kolko je jej kusov na sklade
         * @throws FileNotFoundException 
         */
        public static void kartaNaSklade()throws FileNotFoundException{
        System.out.println("Zadajte nazov hladanej karty:");
        //String thisisdumb = sc.nextLine();
        String karta = sc.nextLine();
        int counter = 0;
        ListIterator itr = StockList.stock.listIterator();
        while(itr.hasNext()){
            Card card = (Card)itr.next();
            if(card.getName().toUpperCase().equals(karta.toUpperCase())){
                System.out.println(card.getName() + ", " + card.getEdition() + " - pocet kusov na sklade: " + card.getStock());
                counter +=1;
            }
        }
        if(counter == 0){
            System.out.println("Zadali ste nespravne meno, skuste znovu");
        }
    }
        /**
         * ked dokoncime objednavku, spustime toto. zadame id a ono to automaticky posle mail
         * @throws IOException
         * @throws EmailException 
         */
        public static void mailObjednavka() throws IOException, EmailException{
            try{
        System.out.println("Zadajte ID objednavky:");
        int ID = sc.nextInt();
        String thisisdumb = sc.nextLine();
        Order objednavka = new Order(ID);
        Order.loadOrder(new File(dataDirectory, String.valueOf(ID)+".txt"));
        SendMail.sendOrderMail(objednavka);
        } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadaj znova");
        }
    }
        /**
         * vymaze polozku z objednavky podla id a mena karty
         * @throws IOException 
         */
        public static void vymazPolozku() throws IOException{
            try {
            System.out.println("Zadajte ID objednavky:");
            int ID = sc.nextInt();
            String thisisdumb = sc.nextLine();
            Order objednavka = new Order(ID);
            File obj = new File(dataDirectory, String.valueOf(ID)+".txt");
            Order.loadOrder(obj);
            System.out.println("Zadajte nazov karty ktoru chcete odstranit:");
            String karta = sc.next();
            thisisdumb = sc.nextLine();
            objednavka.removeCard(karta);
            Order.deleteFromTxt(obj, karta, ID);
            System.out.println(objednavka);
            System.out.println("Karta bola odstranena");
             } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadaj znova");
        }
        }
        /**
         * zisti cenu objednavky podla id
         * @throws IOException 
         */
        public static void zistiCenu() throws IOException{
            try{
            System.out.println("Zadajte ID objednavky:");
            int ID = sc.nextInt();
            String thisisdumb = sc.nextLine();
            Order objednavka = new Order(ID);
            Order.loadOrder(new File(dataDirectory, String.valueOf(ID)+".txt"));
            System.out.println("Cena objednavky je: " + Order.getPriceOfOrder(objednavka));
            } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadaj znova");
        }
        }
        
        
}
