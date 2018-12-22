# Vaatimusmäärittely

## Sovelluksen kuvaus ja tarkoitus

Sovellus on reaaliaikainen Columns-peli. Kyseessä on Tetristä muistuttava peli, jossa ruudun ylälaidasta putoaa kolmesta erivärisestä neliöstä koostuvia pystysuoria palikoita. Käyttäjä (pelaaja) pystyy siirtämään tippuvaa palikkaa sivuttain sekä vaihtamaan värillisten neliöiden järjestystä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä toisiinsa horisontaalisesti, viistosti tai pystysuoraan. Yhdistetyt neliöt poistetaan, ja tyhjien ruutujen tilalle tiputetaan niiden päällä olevat neliöt. Pelaaja saa pisteitä kerralla poistettujen neliöiden määrän perusteella. Peli nopeutuu hiljalleen ja päättyy, kun pelaajan asettama palikka ei mahdu enää pelialueelle. Pelin vaikeutuu hiljalleen myös alhaalta ruudun täyttävä tuhoutumaton harmaa alue.

## Käyttöliitymäluonnos

Sovellus sisältää kolme erilaista näkymää. Näistä ensimmäinen on päävalikko, joka aukeaa sovelluksen käynnistyessä. Päävalikko sisältää keskeisimmät toiminnot, kuten uuden pelin aloittamisen, ennätyspisteiden katsomisen, asetusten säätämisen ja sovelluksen lopettamisen. Valikkoja täydennetään sovelluksen toiminnallisuuksien edellyttämällä tavalla.

![Päävalikko](/dokumentaatio/kuvat/kayttisluonnos_paavalikko.jpg)

Toinen näkymä on varsinainen pelinäkymä. 

![Käyttöliitymäluonnos](/dokumentaatio/kuvat/kayttisluonnos.jpg)

Kolmas on ennätyspisteitä koskeva näkymä, josta on kaksi variaatiota. Jos pelaaja pääsee ennätyslistalle, avautuu hänelle tässä mahdollisuus nimen syöttämiseen. Muuten käyttäjälle kerrotaan pelin päättymisestä. Molemmissa tapauksissa käyttäjä palaa takaisin alkuvalikkoon.

![Pisteet](/dokumentaatio/kuvat/kayttisluonnos_pisteet.jpg)

## Käyttäjät

Sovelluksessa on vain yksi käyttäjätyyppi - normaali pelaaja. Muunlaisille käyttäjärooleille ei ole sovelluksessa tarvetta. Jatkokehtysideana käytäjiä voisi olla periaatteisisti samanaikaisesti kaksi kappaletta (jaettu ruutu). Sovellus on perusominaisuuksiltaan kuitenkin vain yhden käyttäjän kerralla käytettävissä.

## Perusversion toiminnallisuudet

* Käyttäjä voi käynnistää ja pelata peliä
  * Sovellus lukee käyttäjän syötettä näppäimistöltä
  * Käyttäjä voi liikuttaa tippuvaa palikkaa sivusuunnassa
  * Käyttäjä voi "selata" läpi tippuvan palikan neliöiden järjestystä
  * Käyttäjä voi nopeuttaa palikan tippumista halutessaan
  * Käyttäjälle näytetään tietoja pisteistä ja pelin aloituksesta kuluneesta ajasta
  * Sovellus käsittelee asianmukaisesti pysty-, viisto- ja vinosuuntaiset palikoiden poistot

* Pelaaja voi halutessaan väliaikaisesti keskeyttää pelin

* Yksinkertainen vaikeustason säätäminen
  * Päävalikko sisältää mahdollisuuden käyttäjälle valita nopeuden välillä 1 - 10.

* Pelin vaikeus kasvaa ajan myötä alhaalta kasvavan tuhoutumattoman alueen vuoksi. Pelialue siis kutistuu.

* Pisteiden seuranta
  * Käyttäjä voi lisätä omat ennätyspisteet, jos ne oikeuttavat listalle
  * Ennätyspisteet tallennetaan tietokantaan
  * Käyttäjä voi katsoa peliä koskevat ennätyspisteet
  * Pisteet päivitetään listaan vain ohjelman uudelleenkäynnistyksen yhteydessä

* Sovellus rakennetaan mahdollisimman avoimeksi jatkokehitystä varten

* Toimintaympäristön rajoitteista
  * Perusversiossa pisteiden tallentaminen tapahtuu tietokoneen paikalliseen tietokantaan

## Jatkokehitysideoita

Näitä toteutetaan siinä määrin kuin ylimääräistä aikaa jää.

* Vaikeustason monipuoleisempi muuntaminen
  * Palikoiden värien määrä
  * Erityisominaisuuksien lisääntyminen (ks alla "sovelluslogiikan monimutkaistaminen")
  * Pelialueen koko

* Sovellus eriyttää erilaisia pelimoodeja, esimerkiksi
  * Tasomoodi, jossa taso 1, taso 2 jne.
  * Arcade-moodi, jossa peli kiihtyy jatkuvasti ja sisältää sattumanvaraisia tapahtumia

* Sovelluslogiikan monimutkaistaminen
  * Erikoisneliöitä, esimerkiksi
    * "mikä tahansa väri" neliö
    * "pommi"-neliö
  * Sattumanvaraiset tapahtumat, esimerkiksi
    * yksi rivi neliötä tuhoutuu sattumanvaraisesti
    * yksi sarake tuhoutuu sattumanvaraisesti
    
* Sovellukseen lisätään äänieffektit / musiikki

* Graafista puolta siistitään sovelluksen käyttökokemuksen parantamiseksi
 * Käyttöliittymän koodia siistitään pienempiin osiin

* Käyttäjälle tarjottavia asetusmahdollisuuksia lisätään

* Moninpeli (jää mitä varmemmin toteuttamatta)

* Pisteiden mahdollinen tallentaminen verkkoon / verkossa sijaitseva erillinen high-score lista paikallisen pistelistan rinnalla
 * Tallentamiseen käytetyn koodin uudelleentarkastelu toimivuuden lisäämiseksi
 * Pisteiden päivittyminen ilman uudelleenkäynnistystä (tämä käyttöliittymäpuolella, ei tietokannassa)


