
package mtgstore.app;

import mtgstore.app.Card;
import mtgstore.utils.CompareCardByName;
import mtgstore.utils.ErrorLinesException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.Scanner;
import org.apache.commons.mail.EmailException;
/**
 * interface na objednavku, bolo ho treba tak som ho sem dal aj ked je imho zbytocny
 * @author peter
 */
interface canOrder{
    List getCards();
    int getID();   
}

public class Order implements canOrder{
    private static int ID;
    private static ArrayList<Card> cards;
    private static String mail;
    /**
     * konstruktor na objednavku
     * @param ID 
     */
    public Order(int ID){
        this.ID = ID;
        this.cards = new ArrayList<>();
        this.mail = "peter.spurny@outlook.com";
    }
    /**
     * seter na id
     * @param int ID 
     */
    public static void setID(int ID) {
        Order.ID = ID;
    }
    /**
     * geter na mail
     * @return string
     */
    public String getMail(){
        return mail;
    }
    /**
     * getter na id
     * @return int
     */
    public int getID(){
        return ID;
    }
    /**
     * getter na list kariet, vrati kopiu
     * @return arraylist
     */
    public ArrayList<Card> getCards() {
        ArrayList<Card> copy = new ArrayList<>();
        for (Card card : cards) {
            copy.add(new Card(card));
        }
        return copy;
    }
    /**
     * getter na jednu kartu, vrati kopiu
     * @param card
     * @return karta
     */
    public static Card getCard(Card card){
        Card copy = Card.getInstance(card.getName(), card.getCmc(), card.getColor(), card.getRarity(), card.getPrice(), card.getEdition(), 1);        
        return copy;
    }
    /**
     * prida kartu do objednavky a znizi pocet kusov na sklade
     * @param string name 
     */
    public static void addCard(String name){//, int cmc, String color, Rarity rarity, double price, String edition, int stock, int number){         
        ListIterator itr = StockList.stock.listIterator();
        while(itr.hasNext()){
            Card card = (Card)itr.next();
            if(card.getName().equals(name)){
                StockList.decreaseCardStock(name);
                cards.add(getCard(card));
                break;
            }
        }
    
    }
    /**
     * odstrani kartu z objednavky a zvysi pocet kusov na sklade
     * @param string name 
     */
    public void removeCard(String name){//, int cmc, String color, Rarity rarity, double price, String edition, int stock, int number){         
        ListIterator itr = cards.listIterator();
        while(itr.hasNext()){
            Card card = (Card)itr.next();
            if(card.getName().toUpperCase().equals(name.toUpperCase())){
                StockList.increaseCardStock(name);
                cards.remove(card);
                break;
            }
        }
    }
    /**
     * zisti cenu objednavky
     * @param order
     * @return double
     */
    public static double getPriceOfOrder(Order order){
        double price = 0;
        for (Card card : order.cards){
            price += card.getPrice();
        }
        return price;
    }
    
    /**
     * zrovna objednavku podla ceny
     */
    private void sortByPrice() {
        Collections.sort(cards);
    }
    /**
     * zrovna objednavku podla mena
     */
    private void sortByName() {
        Comparator cbp = new CompareCardByName();
        Collections.sort(cards, cbp);
    }
    /**
     * to string pre order
     * @return string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(ID).append("\n");
        for (Card card : cards) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
    /**
     * nahadze karty z txt do objektu order
     * @param startFile
     * @throws FileNotFoundException
     * @throws IOException 
     */
     public static void loadOrder(File startFile) throws FileNotFoundException, IOException {
        List<Integer> errorLines = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String card;
            int ID = Integer.parseInt(br.readLine()); //preskoceni hlavicky
            //System.out.println(ID);
            Order order = new Order(ID);
            while ((card = br.readLine()) != null) {
               order.addCard(card);
            }
        }
        if(!errorLines.isEmpty()){
            throw new ErrorLinesException("Nevalidna karta na riadkoch " + errorLines.toString());    
        }

     }
     /**
      * ulozi do binarneho suboru, pls neskusat
      * @param results
      * @throws FileNotFoundException
      * @throws IOException 
      */
     public void saveToBinaryFile(File results) throws FileNotFoundException, IOException {
        try ( DataOutputStream out = new DataOutputStream(new FileOutputStream(results, true))) {
            int nLetters;
            out.writeInt(ID);
            for (Card card : cards) {
                //out.writeInt(competitor.getRegistracniCislo());
                nLetters = card.getName().length();
                out.writeInt(nLetters);
                for (int i = 0; i < nLetters; i++) {
                    out.writeChar(card.getName().charAt(i));
                }
                out.writeDouble(card.getPrice());
                out.writeInt(card.getStock()); 
            }
        }
    }
     
    
    
    public static void main(String[] args) throws IOException, EmailException{
        Scanner sc = new Scanner(System.in);
        Order pokus = new Order(69420);
        Order funguj = new Order (42069);
        /*
        Card c = new Card("Tinybones", 2, "Black", Rarity.valueOf("MYTHIC"), 499, "Jumpstart", 4);
        Card d = new Card("Mana Leak", 2, "Blue", Rarity.valueOf("MYTHIC"), 1499, "Masters-25", 2);
        Card e = new Card("Faithless Looting", 1, "Red", Rarity.valueOf("COMMON"), 25, "Dark Ascension", 12);
        StockList.addItem(c);
        StockList.addItem(d);
        pokus.addCard("Tinybones");
        pokus.addCard("Mana Leak");
        pokus.sortByName();
        pokus.sortByPrice();
        System.out.println(pokus);
        System.out.println(getPriceOfOrder(pokus));
        System.out.println("");
        pokus.removeCard("Tinybones");
        System.out.println(pokus);
        System.out.println(getPriceOfOrder(pokus));
        System.out.println(StockList.stock);*/
        String parent = System.getProperty("user.dir")+ File.separator +"data";
        File dataDirectory = new File(parent);
        while (true) {
                try {
                    System.out.println("Zadej nazev vstupniho souboru");
                    //StockList.loadInventory(new File(dataDirectory, sc.next()));
                    StockList.loadInventory(new File(dataDirectory, sc.next()));
                    //pokus.addCard("Cho-Manno, Revolutionary");
                    loadOrder(new File(dataDirectory, sc.next()));
                    //loadOrder(new File(dataDirectory, sc.next()));
                    System.out.println(pokus);
                    //SendMail.sendOrderMail(pokus);
                    //pokus.removeCard("Demystify");
                    //System.out.println(funguj);
                    //System.out.println(cards);
                    //System.out.println(StockList.getStock());
                    break;
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Zadaj znova");
                }catch(RuntimeException e){
                    //pokus = new Order(69420);
                    System.out.println(e.getMessage());
                    System.out.println("Udaj v subore je nespravne/nazov je nespravny. Oprav to.");
                }
            }

    }
}
