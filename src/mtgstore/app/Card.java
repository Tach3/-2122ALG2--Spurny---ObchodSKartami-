
package mtgstore.app;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Card implements Comparable<Card>{
    private String name;
    private int cmc;
    private String color;
    private Rarity rarity;
    private double price;
    private String edition;
    private int stock;
    /**
     * vytvori kartu, metoda pod tym robi to iste, sluzi na pripady kedy chceme mat kartu v ponuke ale este nevieme kolko ich bude na sklade
     * @param name
     * @param cmc
     * @param color
     * @param rarity
     * @param price
     * @param edition
     * @param stock 
     */
    public Card(String name, int cmc, String color, Rarity rarity, double price, String edition, int stock){
        this.name = name;
        this.cmc = cmc;
        this.color = color;
        this.rarity = rarity;
        this.price = price;
        this.edition = edition;
        this.stock = stock;
    }
    
    public Card(String name, int cmc, String color, Rarity rarity, double price, String edition){
        this.name = name;
        this.cmc = cmc;
        this.color = color;
        this.rarity = rarity;
        this.price = price;
        this.edition = edition;
        this.stock = 0;
    }
    /**
     * defenzivna kopia karty
     * @param c 
     */
    public Card(Card c){
        this.name = c.name;
        this.cmc = c.cmc;
        this.color = c.color;
        this.rarity = c.rarity;
        this.price = c.price;
        this.edition = c.edition;
        this.stock = c.stock;
    }
    /**
     * vrati instanciu karty
     * @param name
     * @param cmc
     * @param color
     * @param rarity
     * @param price
     * @param edition
     * @param stock
     * @return objekt karta
     */
    public static Card getInstance(String name, int cmc, String color, Rarity rarity, double price, String edition, int stock){
        return new Card(name, cmc, color, rarity, price, edition, stock);
    }
    /**
     * gettre na atributy kariet
     * @return 
     */
    public String getName() {
        return name;
    }

    public int getCmc() {
        return cmc;
    }

    public String getColor() {
        return color;
    }

    public double getPrice() {
        return price;
    }

    public String getEdition() {
        return edition;
    }
    
    public Rarity getRarity(){
        return rarity;
    }
    
    public int getStock(){
        return stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCmc(int cmc) {
        this.cmc = cmc;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
    /**
     * keby sme nahodou chceli upravit pocet kariet na sklade
     * @param stock 
     */
    public void setStock(int stock){
        if (stock >= 0){
            this.stock = stock;
        } else{
            throw new IllegalArgumentException("Nemozeme mat menej ako 0 kariet na sklade");
        }
    }
    /**
     * zvysi pocet kariet na sklade o 1
     */
    public void increaseStock(){
        this.stock += 1;
    }
    /**
     *  znizi pocet kariet na sklade o 1
     */
    public void decreaseStock(){
        this.stock -= 1;
    }
    /**
     * urci raritu, musi byt jedna z enum typov
     * @param rarity 
     */
    public void setRarity(String rarity){
        this.rarity = checkRarity(rarity);
    }
    /**
     * zisti ci rarita nalezi enum typu
     * @param rarity
     * @return 
     */
    private Rarity checkRarity(String rarity){
        switch(rarity.toUpperCase()){
            case "COMMON" -> {
                return Rarity.COMMON;
            }
            case "UNCOMMON" -> {
                return Rarity.UNCOMMON;
            }
            case "RARE" -> {
                return Rarity.RARE;
            }
            case "MYTHIC" -> {
                return Rarity.MYTHIC;
            }
            default -> throw new IllegalArgumentException("Nevalidna rarita");
        }
    }
    /**
     * vrati string karyt keby ho nahodou bolo treba
     * @return 
     */
    @Override
    public String toString() {
        return String.format("%s %2d %s %s %.2f %s %d", this.name, this.cmc, this.color, this.rarity, this.price, this.edition, this.stock);
        //return "Card: " + "name=" + name + ", cmc=" + cmc + ", color=" + color + ", rarity=" + rarity + ", price=" + price + ", edition=" + edition + ", stock=" + stock;
    }
    /**
     * porovna karty podla ceny
     * @param c
     * @return 
     */
    @Override
    public int compareTo(Card c){
        return (int) (this.getPrice() - c.getPrice());
    }
    /**
     * zisti ci je karta na sklade
     * @param c
     * @return boolean
     */
    public static boolean isInStock(Card c){
        if (c.getStock() > 0){
            return true;
        }else{
            return false;
        }
    }
    
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String r = "Mythic";
        Card c = new Card("Tinybones", 2, "Black", Rarity.valueOf(r.toUpperCase()), 499, "Jumpstart", 4);
        System.out.println(c);
        c.setRarity("Mythic");
        c.setStock(5);
        System.out.println(c);
        Card d = Card.getInstance(c.name, c.cmc, c.color, c.rarity, c.price, c.edition, 0);
        System.out.println(d);
        System.out.println(isInStock(d));
        System.out.println(c);
        
        
    }
    
    
    
    
}
