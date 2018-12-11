# Alustava käyttöohje

## Ohjelman käynnistäminen

Ohjelman voi käynnistää joko kehitysympäristöstä tai komennolla


```
java -jar ColumnspeliRelease2.jar
```

## Alkunäyttö
Sovelluksessa on alkunäytössä kolme valintaa.

1. Käynnistä peli
- tämä aloittaa pelin
2. Ennätyspisteet
- tämä siirtää käyttäjän tarkastelemaan ennätyspisteitä
- näkymästä pääsee pois alhaalla olevaa nappia painettaessa
3. Lopeta peli
- sulkee sovelluksen

Alla olevasta säädöstä voi valita itselleen sopivan pelinopeuden.

## Pelin käynnistyttyä
### Ohjaus

Käyttäjällä ovat käytössään nuolinäppäimet ylös, alas, vasemmalle ja oikealle.

Vasen-nuoli: siirtää pelaajaa vasemmalle, jos siellä on tilaa.
Oikea-nuoli: siirtää pelaajaa oikealle, jos siellä on tilaa.
Ylös-nuoli: vaihtaa palikoiden järjejestystä
Alas-nuoli: palikka tippuu nopeammin.
### Muuta tärkeää

Pisteet näkyvät oikeassa sivussa. Samoin peliin kuluva aika. Pelin - mutta ei ajan - voi keskeyttää halutessaan.

## Pelin tavoite

Pelin tavoitteena on saada mahdollisimman korkeat pisteet. Pisteitä saadaan seuraavasti:

1. Pelaaja saa vähintään 3 samanväristä palikkaa sivusuunnassa. Jos palikoita on enemmän, saa myös pisteitä enemmän.
2. Pelaaja saa vähintään 3 samanväristä palikkaa pystysuunnassa. Jos palikoita on enemmän, saa myös pisteitä enemmän.
3. Pelaaja saa vähintään 3 samanväristä palikkaa pystysuunnassa. Jos palikoita on enemmän, saa myös pisteitä enemmän.

Siinä tapauksessa, että useampi yllä mainituista ehdoista tapahtuu samanaikaisesti, saa käyttäjä vielä enemmän pisteitä!

Tärkeää: **pelialue pienenee jatkuvasti** pidä siis kiirettä!

## Onnistuiko peli hyvin?

Voit päästä hyvillä pisteillä ennätyslistalle. 
(lisääminen vielä rakenteilla)




