# Vaatimusmäärittely

## Sovelluksen kuvaus ja tarkoitus

Sovellus on reaaliaikainen Columns-peli. Kyseessä on Tetristä muistuttava peli, jossa ruudun ylälaidasta putoaa kolmesta erivärisestä neliöstä koostuvia pystysuoria palikoita. Käyttäjä (pelaaja) pystyy siirtämään tippuvaa palikkaa sivuttain sekä vaihtamaan värillisten neliöiden järjestystä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä toisiinsa horisontaalisesti, viistosti tai pystysuoraan. Yhdistetyt neliöt poistetaan, ja tyhjien ruutujen tilalle tiputetaan niiden päällä olevat neliöt. Pelaaja saa pisteitä tarkemmin määritellyillä perusteilla. Peli nopeutuu hiljalleen ja päättyy, kun pelaajan asettama palikka ei mahdu enää pelialueelle.

## Käyttöliitymäluonnos

Sovellus sisältää kolme erilaista näkymää. Näistä ensimmäinen on päävalikko, joka aukeaa sovelluksen käynnistyessä. Päävalikko sisältää keskeisimmät toiminnot, kuten uuden pelin aloittamisen, ennätyspisteiden katsomisen, asetusten säätämisen ja sovelluksen lopettamisen. Valikkoja täydennetään sovelluksen toiminnallisuuksien edellyttämällä tavalla.

![Päävalikko](/dokumentaatio/kuvat/kayttisluonnos_paavalikko.jpg)

Toinen näkymä on varsinainen pelinäkymä. 

![Käyttöliitymäluonnos](/dokumentaatio/kuvat/kayttisluonnos.jpg)

Kolmas on ennätyspisteitä koskeva näkymä. Jos pelaaja pääsee ennätyslistalle, avautuu hänelle tässä mahdollisuus nimen syöttämiseen.

![Pisteet](/dokumentaatio/kuvat/kayttisluonnos_pisteet.jpg)

## Käyttäjät

Sovelluksessa on vain yksi käyttäjätyyppi - normaali pelaaja. Muunlaisille käyttäjärooleille ei ole sovelluksessa tarvetta. Jatkokehtysideana käytäjiä voisi olla samanaikaisesti kaksi kappaletta (jaettu ruutu). Sovellus on perusominaisuuksiltaan kuitenkin vain yhden käyttäjän kerralla käytettävissä.

## Perusversion toiminnallisuudet


* Käyttäjä voi lisätä omat ennätyspisteet, jos ne oikeuttavat listalle
* Ennätyspisteet tallennetaan tiedostoon
* Käyttäjä voi katsoa peliä koskevat ennätyspisteet


## Jatkokehitysideoita


