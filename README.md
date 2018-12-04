# Columns-peli

Tämä sovellus on reaaliaikainen Columns-peli. Sovellus on Ohelmistotekniikan menetelmät -kurssin harjoitustyö. Kyseessä on variaatio Tetriksestä, jossa palikoiden pyörittämisen ja pelilaudan täyttämisen sijaan huomio on värien yhdistämisessä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä. Yhdistäminen voi tapahtua horisontaalisesti, viistosti tai pystysuoraan. 

*Sovelluksen tämänhetkisistä toiminnoista ja lyhyt ohje:*

Sovelluksessa on toteutettu nyt alustava runko. Palikat tippuvat pelialueella. Pelaaja voi käyttää nuolinäppäimiä palikoiden ohjaamiseen. Neliöiden järjestystä vaihdetaan painamalla ylös-nappia. Putoamista voi nopeuttaa painamalla alas-nuolta. 

**Uutta 27.11.** palikat jäävät alueelle. Sovellus tarkastaa väliaikaisella (ei toimi oikein, korjataan myöhemmin, kun keksin järkevimmän algoritmin tähän) metodilla hyväksyttäviä rivejä. Peli pitää kirjaa pisteistä ja näyttää ne. Peli tunnistaa myös sen, mihin uusi pelaajapalikka voi syntyä ylälaidassa. Peli tunnistaa "game over" -tilan. Tämän seuraukset lisätään myöhemmin.

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

### Releaset

placeholder

### Komentorivitoimintoja

Jarin generointi (luo hakemistoon target suoritettavan jar tiedoston nimeltään Columnspeli-1.0-SNAPSHOT.jar)

```
mvn package
```

Testien suorittaminen

```
mvn test
```

Raportti testikattavuudesta

```
mvn jacoco:report
```

Checkstyle

```
mvn jxr:jxr checkstyle:checkstyle
```
