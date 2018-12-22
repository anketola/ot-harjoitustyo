# Käyttö- ja asennusohje

## Ohjelman käynnistäminen

Ohjelman voi käynnistää tällä hetkellä kokonaisena versiona ainoastaan kehitysympäristöstä. Ohjelman toimiminen edellyttää, että sitä varten oleva tietokanta [scores.db](https://github.com/anketola/ot-harjoitustyo/blob/master/Columnspeli/scores.db) on projektin juuressa. Tietokanta on saatavilla seuraavan linkin takaa.

Käytössä olevan SQLite tietokannan voi halutessaan luoda myös yksinkertaisella lauseella SQLite3:ssa:

```
CREATE TABLE Scores (name varchar(100), score integer);
```

Tietokanta jätettiin ratkaisuksi sovellukseen, koska se vaikutti tiedostoa paremmalta ratkaisulta pidemmällä aikavälillä, koska siihen saisi helposti tehtyä kyselyitä eri vaikeustasoasetusten perusteella, jos ohjelmaa lajentaisi. Tietokanta toimii sekä tiedon haussa että tallennuksessa muuten jo nyt siitä huolimatta, että jar-tiedoston kanssa ilmeni ongelma toisensa jälkeen (kyse osaamattomuudesta).

## Päävalikko
Sovelluksessa on päävalikossa kolme nappia ja mahdollisuus vaihtaa pelin nopeutta. Pelin nopeus säätää vaikeutta. Voit painaa nappeja ja säätää nopeutta joko hiirellä tai näppäimistöllä.

![Alkunäyttö](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/menu_view.png)

1. Käynnistä peli
- Napin painaminen käynnistää varsinaisen pelin valitulla nopeustasolla.
2. Ennätyspisteet
- Napin painaminen siirtää käyttäjän tarkastelemaan ennätyspisteitä.
3. Lopeta peli
- Napin painaminen sulkee sovelluksen.

Alla olevasta säädöstä voi valita itselleen sopivan pelinopeuden. Mitä suurempi nopeus, sitä vaikeampi ja nopeampi peli on.

## Pelin käynnistyttyä
### Pelinäyttö

Pelin käynnistyttä käyttäjälle aukeaa seuraava näkymä, jossa hän ohjaa ylhäältä tippuvia palikoita.

![Pelinäyttö](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/game_view.png)

### Ohjaus

Pelin käynnistyttyä käyttäjällä vat käytössään nuolinäppäimet ylös, alas, vasemmalle ja oikealle.

![Napit](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/napit.jpg)

* Vasen-nuoli: siirtää pelaajaa vasemmalle, jos siellä on tilaa.
* Oikea-nuoli: siirtää pelaajaa oikealle, jos siellä on tilaa.
* Ylös-nuoli: vaihtaa palikoiden järjestystä.
* Alas-nuoli: nopeuttaa palikan tippumista.

### Pelin tavoite

Pelin tavoitteena on saada mahdollisimman korkeat pisteet. Pisteitä saadaan seuraavasti:

1. Pelaaja saa vähintään 3 samanväristä palikkaa sivusuunnassa. Jos palikoita on enemmän, saa myös pisteitä enemmän.
2. Pelaaja saa vähintään 3 samanväristä palikkaa pystysuunnassa. Jos palikoita on enemmän, saa myös pisteitä enemmän.
3. Pelaaja saa vähintään 3 samanväristä palikkaa pystysuunnassa. Jos palikoita on enemmän, saa myös pisteitä enemmän.

Siinä tapauksessa, että useampi yllä mainituista ehdoista tapahtuu samanaikaisesti, saa käyttäjä vielä enemmän pisteitä. Nopeudella ei ole muuten vaikutusta pisteisiin, mutta pelialue **pelialue pienenee jatkuvasti**, pidä siis kiirettä. Alhaalta kasvavat harmaat palikat ovat tuhoutumattomia ja näyttävät seuraavalta.

![Harmaat](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/gray_blocks.png)

### Muita tietoja ja nappeja pelinäkymässä

Pelaajan pisteet näkyvät oikeassa ylälaidassa.

Pisteiden alla näkyy pelin käynnistämisestä asti kulunut aika.

Painamalla jäädytä/jatka peliä -nappia voit keskeyttää pelaamisen. Tämä ei vaikuta ajan kulumiseen.

### Pelin päättyminen

Peli päättyy, kun pelaajan palikka ei mahdu enää pelialueelle. Jos pisteesi riittävät ennätyslistalle, saat seuraavan näkymän:

![Ennätys](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/player_high_score.png)

Kirjoita nimesi ja paina "Tallenna". Ennätyksesi tallennetaan silloin tietokantaan. Jos pisteesi eivät riitä listalle, saat seuraavan näkymän: 

![Peliloppu](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/game_over.png)

Painamalla nappia pääset takaisin alkuvalikkoon.

### Pistenäkymä

Valitsemalla päävalikossa "Ennätyspisteet" pääset seuraavaan näkymään. Pisteet päivittyvät nykyisessä versiossa näkymään vain ohjelman käynnistyksen yhteydessä.

![Pistenäkymä](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/score_view.png)


