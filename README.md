###  -2122ALG2--Spurny---ObchodSKartami-

# Motivácia: 
Máme obchod so zberatelskými kartičkami a potrebujeme systém na vybavovanie objednávok, interakciu so zákazníkmi a ďalšie veci. 
Potrebujeme niečo čo nám bude spravovať databázu.
Ideálne máme ešte niekde stránku s ktorou môžu zákazníci interagovať a ktorá im ponúka produkty. Z nej si vieme stiahnuť objednávky v textovej forme, naopak na ňu vieme uploadnuť stav skladu.

# Riešenie:
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

# CSV súbor ktorý obsahuje karty má tieto atribúty v konkrétnom poradí, oddelené ;
* Name;cmc;color;rarity;price;edition;stock

_**Objednávka**_
Identifikuje sa podľa ID (náhodné 5miestne číslo generované stránkou, je na začiatku .txt súboru a v mene)
* získanie atribútov objednávky (getID, getMail, getCards, getCard(jedna konkrétna karta))
* pridanie/odobranie kariet (addCard, removeCard)
* získanie celkovej ceny objednávky (getPriceOfOrder)
* usporiadanie objednávky poďla ceny/mena (sortByName, sortByPrice)
* načítanie konkrétnej objednávky (loadOrder)
* vymazanie z objednávky (deleteFromTxt)
* uloženie do binárneho súboru (saveToBinaryFile)

# TXT súbor má:
1. ID v názve
2. ID ako prvý riadok
3. meno karty na jeden riadok

_**Aplikácia**_
* pri spustení načita skladový súbor
1. vypíše stav skladu (stavSkladu)
2. zistí či je karta na sklade (kartaNaSklade)
3. odstráni karty zo skladu a pošle zákazníkovi mail o vybavení objednávky (mailObjednavka)
4. vymaž položku objednávky pomocou mena položky (vymazPolozku)
5. zistí celkovú cenu objednávky (zistiCenu)

## Class Diagram
![](https://github.com/Tach3/-2122ALG2--Spurny---ObchodSKartami-/blob/ed05411debda09bc4def2f2531772a2c789e0def/classDiagram.drawio.png)

_**Externá knižnica**_
Apache commons mail

funckne specifikacie, popisat vstupne subory, overhaul vizualny, externa kniznica, link png

