# Columns-peli

Tämä sovellus on reaaliaikainen Columns-peli. Sovellus on Ohelmistotekniikan menetelmät -kurssin harjoitustyö. Kyseessä on variaatio Tetriksestä, jossa palikoiden pyörittämisen ja pelilaudan täyttämisen sijaan huomio on värien yhdistämisessä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä. Yhdistäminen voi tapahtua horisontaalisesti, viistosti tai pystysuoraan. 

Sovelluksen tämänhetkisistä toiminnoista ja lyhyt ohje:

Sovelluksessa on toteutettu nyt alustava runko. Palikat tippuvat pelialueella. Pelaaja voi käyttää nuolinäppäimiä palikoiden ohjaamiseen. Neliöiden järjestystä vaihdetaan painamalla ylös-nappia. Putoamista voi nopeuttaa painamalla alas-nuolta. Kollisionhavaintaa yms. ei ole vielä toteutettu.

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)


### Komentorivitoimintoja

Testien suorittaminen

```
mvn test
```

Raportti testikattavuudesta

```
mvn jacoco:report
```
