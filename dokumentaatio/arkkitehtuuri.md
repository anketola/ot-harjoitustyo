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

Sovelluslogiikka on ehkä hieman monimutkaisempi pelialueen palikoiden hallitsemiseksi. UI:n luoma GameController-olio yhdistää sovelluksen muut luokat toimivaksi kokonaisuudeksi. Pelin aktiivisesti toimivaa logiikkaa koskevat metodit (skannausalgoritmit yms.) on jaettu kolmeen logic-pakkauksen luokkaan.

* ScanLogic-luokan vastuulla on huolehtia pelialueen tarkkailusta jokaisen pelipalikan pysäytymisen jälkeen.
* PlayerCollisionLogic-luokan vastuulla on tarkastella pelaajan ohjaaman palikan kollisioita suhteessa ympäristöön.
* AreaModifyLogic-luokan vastuulla on muokata pelialueetta (siirtää palikoita, vapauttaa pelaajan ohjaamat palikat ympäristöön jne).

Toiminnan kannalta keskeinen on ScanLogic-luokan kokoelma metodeita, joilla etsitään pelin sääntöjen mukaisia neliöitä. Metodit ovat selväkielisinä seuraavanlaisia:

- Sivusuuntaisia suoria etsivä algoritmi
- Pystysuuntaisia suoria etsivä algoritmi
- Kaksi viistosuuntaisia suoria etsiviä algoritmeja

## Tietojen pysyväistallennus

Tietojen pysyväistallennukseen käytetään SQLite tietokantaa. Tämä on toteutettu DAO-mallilla. Tietokanta sisältää tässä vaiheessa tiedot käyttäjän nimestä/nimimerkistä ja piste-ennätyksenä. Pysyväistallennukseen valittavia tietoja auttamaan valitsemaan on omia luokkia.

## Sekvenssikaavioita toiminnallisuuksista

### Käyttäjä vaihtaa ohjaamiensa neliöiden järjestystä

Pelissä käyttäjä voi halutessaan ylös -nappia painamalla vaihtaa neliöiden järjestyksen haluamakseen. Tätä varten käyttöliittymä tarkkailee näppäinsyötteitä. Sovellus tarkistaa ensin GameArea-oliolta, onko peli asetettu taukotilaan. Jos peli ei ole taukotilassa, kutsutaan PlayerBlock-olion shuffleBlocks-metodia. Metodi siirtää ylimmän palikan alimmaksi ja muut alemmat yhden ylöspäin.

![Palikoiden_sekoitus](/dokumentaatio/kuvat/player_shuffle.jpg)

### Käyttäjä liikkuu sivusuunnassa

Toisena toiminnallisuutena on kuvattu käyttäjän ohjaaman palikan siirtymistä sivusuunnassa. Kaaviossa tapahtuu siirtyminen oikealle. Pelaaja painaa näppäimistöllä oikeaa nuolta. Sovellus tarkistaa taas, onko GameArea asetettu taukotilaan. Esimerkissä taukotila ei ole päällä. Tämän jälkeen käyttöjärjestelmä kutsuu GameArea-olion metodia movePlayer parametrillä "right". Tämän jälkeen isCollisionRight-metodi tarkistaa, onko oikealle mahdollista siirtyä. Oikealle on mahdollista siirtyä ja GameArea kutsuu PlayerBlock-olion moveX-metodia parametrillä "right". Seurauksena pelaajan ohjaama palikka siirtyy askeleen oikealle.

![Pelaajan_sivusuunta](/dokumentaatio/kuvat/player_move.jpg)

## Heikkouksia ohjelman rakenteessa

Sairastelun vuoksi tällä viikolla jäi suunniteltu rakenteen muokkaus kokonaan tekemättä. Paisuneet luokat tulee poistaa (myös UI:stä), ja GameArea:n sisältämä logiikka tulee siirtää pois siitä omaan uuteen GameLogic luokkaansa (ja pakkaukseen). Kahdessa algoritmissa on yhä pieni katvealue, se on korvattavissa melko helposti erillään-

