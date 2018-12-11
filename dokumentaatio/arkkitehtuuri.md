# Arkkitehtuurikuvaus

## Alustava luokkakaavio

![Luokkakaavio](/dokumentaatio/kuvat/v4_a.png)

## Käyttöliittymä

Käyttöliittymä on rakennettu JavaFX:llä, ja koostuu seuraavista näkymistä.

- Valikkonäkymä
- Varsinainen pelialue
- Piste-ennätykset
- "Peli loppui" -ruutu

Käyttöliittymä toimii erillään sovelluslogiikasta, mutta käyttöliittymän laajuuden vuoksi sitä tullaan jakamaan helpommin hallittaviin osiin kehittäjän näkökulmasta.

## Sovelluslogiikka

Sovelluslogiikka on ehkä hieman monimutkaisempi, koska sen täytyy hallita useita asioita. Tämän mahdollistamiseksi sovellus käyttää kokoelmaa metodeita, joilla etsitään pelin sääntöjen mukaisia neliöitä. Metodit ovat selväkielisinä seuraavanlaisia:

- Sivusuuntaisia suoria etsivä algoritmi
- Pystysuuntaisia suoria etsivä algoritmi
- Kaksi viistosuuntaisia suoria etsiviä algoritmeja

Pelaajan liikkumiseen liittyvät etenkin metodit, joilla tarkkaillaan kollisioita.

Nämä käyvät GameArea:n läpi joka kerta, kun pelaajan ohjaamat palikat vapautetaan. Etsimisalgoritmeja tukevat siivousalgoritmit. Näitä ovat seuraavat:

- Suorat tuohoavat algoritmit
- Pisteitä laskevat algormit
- Tyhjien nelilöiden etismisalgoritmi
- Tyhjien neliöiden yläpuolella olevia neliöitä alas tiputtava algoritmi

 
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

