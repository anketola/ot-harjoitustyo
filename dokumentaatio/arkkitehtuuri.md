# Arkkitehtuurikuvaus

## Yleisrakenne

Sovellus on jaettu neljään pakkaukseen.

* UI
* domain
* logic
* dao

Sovelluksen luokkarakenne kävi läpi suhteellisen ison muutoksen, kun aivan liian suureksi kasvanut (entinen) GameArea-luokka purettiin toiminnallisuuksien mukaan erillisiin luokkiin.

## Käyttöliittymä

Käyttöliittymä ja grafiikka on rakennettu JavaFX:llä, ja koostuu seuraavista näkymistä.

- Päävalikko
- Varsinainen pelinäkymä
- Ennätyspisteet-näkymä
- "Peli loppui" -ruutu, josta on kaksi variaatiota

Käyttöliittymä toimii mahdollisiman erillään sovelluslogiikasta. Sovellukseen jää tarve mahdollisesti muokata käyttöliitymää koskevaa koodia sen jakamiseksi osiksi, vaikka se ei ole checkstyle:n alla.

## Sovelluslogiikka

Sovelluslogiikka on ehkä hieman monimutkaisempi pelialueen palikoiden hallitsemiseksi. UI:n luoma GameController-olio yhdistää sovelluksen muut luokat toimivaksi kokonaisuudeksi. Pelin aktiivisesti toimivaa logiikkaa koskevat metodit (skannaus- ja tiputusalgoritmit yms.) on jaettu kolmeen logic-pakkauksen luokkaan.

* ScanLogic-luokan vastuulla on huolehtia pelialueen tarkkailusta jokaisen pelipalikan pysäytymisen jälkeen.
* PlayerCollisionLogic-luokan vastuulla on tarkastella pelaajan ohjaaman palikan kollisioita suhteessa ympäristöön.
* AreaModifyLogic-luokan vastuulla on muokata pelialueetta (siirtää palikoita, vapauttaa pelaajan ohjaamat palikat ympäristöön jne).

Toiminnan kannalta keskeinen on ScanLogic-luokan kokoelma metodeita, joilla etsitään pelin sääntöjen mukaisia suoria. Yksinkertaisesti kuvaten GameController-luokka kutsuu tätä metodikokoelmaa, kun se saa PlayerCollisionLogic-luokan avulla tietää palikan olevan pysäytettynä. Tämä jälkeen GameController kutsuu AreaModifyLogic-luokan releaseBlocks metodia, joka asettaa ne pelialueelle. Metodit ovat selväkielisinä seuraavanlaisia:

- Sivusuuntaisia suoria etsivä algoritmi
- Pystysuuntaisia suoria etsivä algoritmi
- Kaksi viistosuuntaisia suoria etsiviä algoritmeja
- Poistettavat palikat keräävä metodi

ScanLogic-luokka palauttaa poistettavat palikat GameControllerin tietoon, joka mm. kutsuu edelleen AreaModifyLogic-luokan scanAndDrop-metodia, joka käsittelee neliömassan keskelle jääneet tyhjät kolot ja hyödyntää dropAbove metodia palikoiden tiputtamiseen alas. GameController välittää pisteet GameStatistic-luokalle. Muita vaiheita ovat mm. pelaajan ohjaaman PlayerBlockin vapaiden syntykohtien skannaaminen ja palikan synnyttäminen tähän kohdalle, tai vaihtoehtoisesti pelin loppuminen syntypaikkojen puutteen vuoksi.

## Tietojen pysyväistallennus

Tietojen pysyväistallennukseen käytetään SQLite tietokantaa. Tämä on toteutettu DAO-mallilla. Tietokanta sisältää tässä vaiheessa tiedot käyttäjän nimestä/nimimerkistä ja piste-ennätyksenä. Pysyväistallennukseen valittavia tietoja auttamaan valitsemaan on omia luokkia.

## Sekvenssikaavioita toiminnallisuuksista

### Käyttäjä vaihtaa ohjaamiensa neliöiden järjestystä

Pelissä käyttäjä voi halutessaan ylös -nappia painamalla vaihtaa neliöiden järjestyksen haluamakseen. Tätä varten käyttöliittymä tarkkailee näppäinsyötteitä. Sovellus tarkistaa ensin GameController-oliolta, onko peli asetettu taukotilaan. Jos peli ei ole taukotilassa, kutsutaan GameControllerin getPlayerBlock-metodia ja PlayerBlock-olion shuffleBlocks-metodia. Metodi siirtää ylimmän palikan alimmaksi ja muut alemmat yhden ylöspäin.

![Palikoiden_sekoitus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssi_shuffle.jpg)

### Käyttäjä liikkuu sivusuunnassa

Toisena toiminnallisuutena on kuvattu käyttäjän ohjaaman palikan siirtymistä sivusuunnassa. Kaaviossa tapahtuu siirtyminen oikealle. Pelaaja painaa näppäimistöllä oikeaa nuolta. Sovellus tarkistaa taas, onko GameController asetettu taukotilaan. Esimerkissä taukotila ei ole päällä. Tämän jälkeen käyttöjärjestelmä kutsuu GameController-olion metodia movePlayer parametrillä Direction.RIGHT. Tämän jälkeen GameController kutsuu konstruktorissa luomaansa PlayerCollisionLogic-oliota ja sen sisältämäää isCollisionRight-metodia. Metodi tarkistaa, onko oikealle mahdollista siirtyä. Oikealle on mahdollista siirtyä ja PlayerCollisionLogic palauttaa arvon false (koska kysely oli negatiivisessa muodossa). GameController jatkaa movePlayer metodin suorittamista ja kutsuu PlayerBlock-olion moveX-metodia parametrillä Directioin.RIGHT. Seurauksena pelaajan ohjaama palikka siirtyy askeleen oikealle.

![Pelaajan_sivusuunta](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/sekvenssi_siirto.jpg)


## Heikkouksia ohjelman rakenteessa

Sovelluksessa on kaksi selkeää ongelma-aluetta:
* Käyttöliittymän selkeyttäminen
* Tietokannan toiminnallisuuteen liittyvät ongelmat



