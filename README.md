# -2122ALG2--Spurny---ObchodSKartami-

Motivácia: Máme obchod so zberatelskými kartičkami a potrebujeme systém na vybavovanie objednávok, interakciu so zákazníkmi a ďalšie veci. Potrebujeme niečo čo nám bude spravovať databázu.
Ideálne máme ešte niekde stránku s ktorou môžu zákazníci interagovať a ktorá im ponúka produkty. Z nej si vieme stiahnuť objednávky v textovej forme, naopak na ňu vieme uploadnuť stav skladu.

Riešenie:
Moja aplikácia dokáže spravovať samostatne jednotlivé karty, sklad a objednávky. Dokáže posielať emaily, meniť objednávky atď...

Program má niekoľko levelov:
1: Karta
Dokážeme karty pridávať, odoberať, meniť ich cenu aj množstvo na sklade.

2: Sklad
Na začiatku programu sa sklad naplní kartami z .csv súboru. Dokážeme v ňom meniť ceny kariet, meniť ich počty a odstraňovať ich úplne.

3: Objednávky
Jednotlivé objednávky sa nachádzajú v .txt súboroch v /data. Sú identifikované cez ID. Obsahujú názvy kariet ktoré si ľudia chcú kúpiť.
Vieme získať celkovú cenu objednávky, karty pridávať, odstraňovať a ukladať ich do binárnych súborov(myslím si, netestoval som to a ani nechcem)...

Aplikácia:
Na začiatku sa natiahne sklad a aplikácia ponúkne niekoľko možností. Spýta sa na meno skladového súboru(cards2.csv) a následne si začne pýtať príkazy.

Testy:
1: pri vypytani si mena vstupneho suboru zadajte nieco ine. Ocakava sa ze to chyti chybu a upozorni a spusti znovu

2:pokyn zadajte nespravne cislo. ocakava sa ze vas to upozorni a zopakuje otazku

3:mame kartu na sklade? zadajte nespravne meno. ocakava sa ze sa vas to spyta znovu

4:mame karut na sklade? zadajte spravny nazov, napriklad "duress". je mozne pouzit velke aj male pismena. vypise to mnozstva kariet na sklade

5:posli mail, spyta sa na id. zadajte nespravne id. spyta sa to znovu

6:posli mail. zadajte spravne id. posle mi to mail a mozem ho ukazat triede. prosim nespamovat

7:vymaz polozku, zadajte nespravne id, spyta sa znovu

8:zisti cenu objednavky, zadajte nespravne id, spyta sa znovu

9:zisti cenu objednavky, zadajte spravne id a vrati vam cenu

10:vymaz polozku, nechaj si poslat mail a skontroluj subor. bola skutocne vymazana

popis fungovania externej kniznice je v javadoc, je to easy, aj male dieta to pochopi. proste do toho nacapate udaje a apache sa postara o zvysok



funckne specifikacie, popisat vstupne subory, overhaul vizualny, externa kniznica, link png

