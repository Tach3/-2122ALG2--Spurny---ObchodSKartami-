# Obchod s MTG kartami

## Motivácia: 
Máme obchod so zberatelskými kartičkami a potrebujeme systém na vybavovanie objednávok, interakciu so zákazníkmi a ďalšie veci. 
Potrebujeme niečo čo nám bude spravovať databázu.
Ideálne máme ešte niekde stránku s ktorou môžu zákazníci interagovať a ktorá im ponúka produkty. Z nej si vieme stiahnuť objednávky v textovej forme, naopak na ňu vieme uploadnuť stav skladu.



## Riešenie:
Moja aplikácia dokáže spravovať samostatne jednotlivé karty, sklad a objednávky




_**Karta**_
* získanie atribútov karty (getName, getCMC, getRarity, getPrice, getStock, getColor, getEdition)
* zmena atribútov karty (setName, setCMC, setRarity, setPrice, setStock, setCOlor, setEdition)
* zníženie/zvýšenie počtu kariet na sklade (setStock, increaseStock, decreaseStock)
* overenie rarity, mena a farby (checkName, checkColor, checkRarity)
* porovnanie podla ceny (compareTo)
* boolean je karta na sklade? (isInStock)



_**Sklad**_
* pridanie karty do skladu (addItem)
* získanie celého skladu ako text (getStock)
* odstránenie karty podľa mena (removeCardByName)
* zmena ceny konkrétnej karty (updateCardPrice)
* zmena počtu kariet na sklade (updateCardStock, decreaseCardStock, increaseCardStock)
* načítanie skladu z .csv súboru (loadInventory)



###  CSV súbor ktorý obsahuje karty má tieto atribúty v konkrétnom poradí, oddelené ;

* Name;cmc;color;rarity;price;edition;stock - formát v ktorom sú údaje
* príklad súboru:
```
Name;cmc;color;rarity;price;edition;stock
Ancestor's Chosen;7;W;uncommon;9126;10E;9
Ancestor's Chosen;7;W;uncommon;11513;10E;24
Angel of Mercy;5;W;uncommon;1661;10E;6
Angel of Mercy;5;W;uncommon;1265;10E;12
Angelic Blessing;3;W;common;385;10E;4
Angelic Blessing;3;W;common;5118;10E;9
Angelic Chorus;5;W;rare;6980;10E;13
Angelic Wall;2;W;common;8039;10E;24
```


_**Objednávka**_
Identifikuje sa podľa ID (náhodné 5miestne číslo generované stránkou, je na začiatku .txt súboru a v mene)
* získanie atribútov objednávky (getID, getMail, getCards, getCard(jedna konkrétna karta))
* pridanie/odobranie kariet (addCard, removeCard)
* získanie celkovej ceny objednávky (getPriceOfOrder)
* usporiadanie objednávky poďla ceny/mena (sortByName, sortByPrice)
* načítanie konkrétnej objednávky (loadOrder)
* vymazanie z objednávky (deleteFromTxt)
* uloženie do binárneho súboru (saveToBinaryFile)



## TXT súbor má:
1. ID v názve
2. ID ako prvý riadok
3. meno karty na jeden riadok


príklad .txt súboru
```
69420
Plains
Tinybones, Trinket Thief
Field Marshal
```


_**Aplikácia**_
* pri spustení načita skladový súbor
1. vypíše stav skladu (stavSkladu)
2. zistí či je karta na sklade (kartaNaSklade)
3. odstráni karty zo skladu a pošle zákazníkovi mail o vybavení objednávky (mailObjednavka)
4. vymaž položku objednávky pomocou mena položky (vymazPolozku)
5. zistí celkovú cenu objednávky (zistiCenu)



## Class Diagram
![](https://github.com/Tach3/-2122ALG2--Spurny---ObchodSKartami-/blob/665871acc05eb95c6dc44a9202f0b112aa250d0a/classDiagram.jpg)



_**Externá knižnica**_
* Apache commons mailing API
* dokáže poslať mail (sendOrderMail)
```
public static void sendOrderMail(Order order) throws EmailException{
        Email sendemail = new SimpleEmail();        
        sendemail.setSmtpPort(587); // určenie SMTP portu
        sendemail.setAuthenticator(new DefaultAuthenticator("yourMail","yourPassword"));//mail a heslo, gmail vyźaduje autentikáciu
        sendemail.setDebug(false);
        sendemail.setHostName("smtp.gmail.com"); // mail server ktorý používame
        sendemail.setFrom("senderMail"); odosielateľ mailu - adresa
        sendemail.setSubject("Order Information"); // subjekt mailu
        sendemail.setMsg("yourMessage"); // správa ktorú do mailu píšeme
        sendemail.addTo("recepientMail"); // adresa recepienta mailu
        sendemail.setTLS(true); // šifrovanie
        sendemail.send(); //poslanie mailu
        System.out.println("You have sent the email using Apache Commons Mailing API"); // potvrdenie že mail sa odoslal
    }
```


_**Testovanie**_

1. Zadaný nesprávny názov vstupného súboru, output je výzva na opakovanie
2. Zadaný nesprávny pokyn, output je výzva na opakovanie
3. Input 1, output je celý sklad správne sformátovaný
4. Input 2, zadaný nesprávny názov karty, output je výzva na opakovanie
5. Input 2, zadaný správny názov karty, output je zoznam kariet aj s ich edíciami a stavom na sklade
6. Input 3, zadané nesprávne ID objednávky, output je výzva na opakovanie
7. Input 3, zadané správne ID, príde mail s potvrdením
8. Input 4, zadané správne ID a meno položky, output je potvrdenie o zmazaní a zmena .txt súboru
9. Input 4, zadané nesprávne ID, output je výzva na opakovanie
10. Input 5, zadané správne ID, poutput je cena objednávky
11. Input 6, úspešné ukončenie aplikácie
