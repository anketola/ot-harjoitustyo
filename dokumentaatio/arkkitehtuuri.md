# Arkkitehtuurikuvaus

## Alustava luokkakaavio

![Luokkakaavio](/dokumentaatio/kuvat/v4_a.png)

## Sekvenssikaavioita toiminnallisuuksista

### Käyttäjä vaihtaa ohjaamiensa neliöiden järjestystä

Pelissä käyttäjä voi halutessaan ylös -nappia painamalla vaihtaa neliöiden järjestyksen haluamakseen. Tätä varten käyttöliittymä tarkkailee näppäinsyötteitä. Sovellus tarkistaa ensin GameArea-oliolta, onko peli asetettu taukotilaan. Jos peli ei ole taukotilassa, kutsutaan PlayerBlock-olion shuffleBlocks-metodia. Metodi siirtää ylimmän palikan alimmaksi ja muut alemmat yhden ylöspäin.

![Palikoiden_sekoitus](/dokumentaatio/kuvat/player_shuffle.jpg)

### Käyttäjä liikkuu sivusuunnassa

Toisena toiminnallisuutena on kuvattu käyttäjän ohjaaman palikan siirtymistä sivusuunnassa. Kaaviossa tapahtuu siirtyminen oikealle. Pelaaja painaa näppäimistöllä oikeaa nuolta. Sovellus tarkistaa taas, onko GameArea asetettu taukotilaan. Esimerkissä taukotila ei ole päällä. Tämän jälkeen käyttöjärjestelmä kutsuu GameArea-olion metodia movePlayer parametrillä "right". Tämän jälkeen isCollisionRight-metodi tarkistaa, onko oikealle mahdollista siirtyä. Oikealle on mahdollista siirtyä ja GameArea kutsuu PlayerBlock-olion moveX-metodia parametrillä "right". Seurauksena pelaajan ohjaama palikka siirtyy askeleen oikealle.

![Pelaajan_sivusuunta](/dokumentaatio/kuvat/player_move.jpg)



