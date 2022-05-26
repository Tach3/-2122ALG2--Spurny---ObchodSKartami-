
package mtgstore.app;

import mtgstore.app.Rarity;
import mtgstore.app.Card;
import mtgstore.utils.ErrorLinesException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class StockList {
    public static ArrayList<Card> stock = new ArrayList<>();
    /**
     * prida kartu na sklad
     * @param card 
     */
    public static void addItem(Card card){
        stock.add(card);
    }
    
    /**
     * vrati string zoznam poloziek na sklade
     * @return 
     */
    public static String getStock() {
        StringBuilder sb = new StringBuilder();
        for (Card card : stock) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
    /**
     * odstarni polozku zo skladu podla mena
     * @param string name 
     */
   public void removeCardByName(String name) {
   Iterator<Card> itr = stock.listIterator();
   while(itr.hasNext()) {
       Card card = itr.next();
       if(card.getName().equals(name)) {
           itr.remove();
       break;
       }
   }
   }
   /**
    * zmeni cenu karty na sklade
    * @param string name
    * @param double price 
    */
   public void updateCardPrice(String name, double price) {
    ListIterator itr = stock.listIterator();
    while(itr.hasNext())
    {
        Card card = (Card)itr.next();
        if(card.getName().equals(name))
        {
            card.setPrice(price);
            break;
        }
    }

    }
   /**
    * zmeni mnozstvo kariet na sklade
    * @param string name
    * @param int quantity 
    */
   public static void updateCardStock(String name, int quantity) {
    ListIterator itr = stock.listIterator();
    while(itr.hasNext()){
        Card card = (Card)itr.next();
        if(card.getName().equals(name)){
            card.setStock(quantity);
            break;
            }
        }
    }
   /**
    * znizi pocet kariet na sklade o 1
    * @param name 
    */
    public static void decreaseCardStock(String name){
        ListIterator itr = stock.listIterator();
        while(itr.hasNext()){
            Card card = (Card)itr.next();
            if(card.getName().equals(name)){
                card.decreaseStock();
                break;
            }
        }
    }
    /**
     * zvysi pocet kariet na sklade o 1
     * @param name 
     */
    public static void increaseCardStock(String name){
        ListIterator itr = stock.listIterator();
        while(itr.hasNext()){
            Card card = (Card)itr.next();
            if(card.getName().equals(name)){
                card.increaseStock();
                break;
            }
        }
    }
    /**
     * tostring pre sklad, nieco cez 65000 poloziek
     * @return 
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : stock) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
    /**
     * nacita data z csv suboru do skladu
     * @param startFile
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public static void loadInventory(File startFile) throws FileNotFoundException, IOException {
        List<Integer> errorLines = new ArrayList<>();
        try ( BufferedReader br = new BufferedReader(new FileReader(startFile))) {
            String line;
            String[] card;
            String splitBy = ";";
            Card c = null;
            br.readLine(); //preskoceni hlavicky
            while ((line = br.readLine()) != null) {
                card = line.split(splitBy);
                c = new Card(card[0], Integer.parseInt(card[1]), card[2], Rarity.valueOf(card[3].toUpperCase()), Double.parseDouble(card[4]), card[5], Integer.parseInt(card[6]));
                StockList.addItem(c);               
            }
        }
        if(!errorLines.isEmpty()){
            throw new ErrorLinesException("Nevalidna karta na riadkoch " + errorLines.toString());    
        }
    }
    
    
    
    public static void main(String[] args){
       StockList stock = new StockList();
        /*
        Card c = new Card("Tinybones", 2, "Black", Rarity.valueOf("MYTHIC"), 499, "Jumpstart", 4);
        Card d = new Card("Mana Leak", 2, "Blue", Rarity.valueOf("MYTHIC"), 1499, "Masters-25", 2);
        Card e = new Card("Faithless Looting", 1, "Red", Rarity.valueOf("COMMON"), 25, "Dark Ascension", 12);
        stock.addItem(c);
        stock.addItem(d);
        stock.addItem(e);
        System.out.println(stock);
        increaseCardStock("Tinybones");
        decreaseCardStock("Mana Leak");
        updateCardStock("Faithless Looting", 1);
        System.out.println(stock);*/
       
        String line = "";
        String splitBy = ";";
        Card c = null;
        try {
            //parsing a CSV file into BufferedReader class constructor  
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\peter\\Documents\\NetBeansProjects\\MTGStore\\cards2.csv"));
            while ((line = br.readLine()) != null)
            //returns a Boolean value  
            {
                String[] card = line.split(splitBy);
                c = new Card(card[0], Integer.parseInt(card[1]), card[2], Rarity.valueOf(card[3].toUpperCase()), Double.parseDouble(card[4]), card[5], Integer.parseInt(card[6]));
                //use comma as separator  
                //System.out.println("Name= " + card[0] + "  CMC= " + card[1] + "  Color= " + card[2] + "  Rarity= " + card[3] + "  Price= " + card[4] + "  Edition= " + card[5] + "  Stock= " + card[6]);
                StockList.addItem(c);
            }
        }
        catch(IOException e) {
            //e.printStackTrace();
            
        }
        
        System.out.println(stock);
    }
}


