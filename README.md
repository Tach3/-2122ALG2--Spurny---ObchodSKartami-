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
