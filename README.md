# Columns-peli

Tämä sovellus on reaaliaikainen Columns-peli. Sovellus on Ohelmistotekniikan menetelmät -kurssin harjoitustyö. Kyseessä on variaatio Tetriksestä, jossa palikoiden pyörittämisen ja pelilaudan täyttämisen sijaan huomio on värien yhdistämisessä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä. Yhdistäminen voi tapahtua horisontaalisesti, viistosti tai pystysuoraan. 

Palikat tippuvat pelialueella. Pelaaja voi käyttää nuolinäppäimiä palikoiden ohjaamiseen. Neliöiden järjestystä vaihdetaan painamalla ylös-nappia. Putoamista voi nopeuttaa painamalla alas-nuolta. Tarkemmat ohjeet ovat löytyvät [käyttöohjeesta](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Testausdokumentti](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/testaus.md)

[Työaikakirjanpito](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

### Releaset

[Viikko 5](https://github.com/anketola/ot-harjoitustyo/releases/tag/viikko5)

[Viikko 6](https://github.com/anketola/ot-harjoitustyo/releases/tag/Viikko)

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
JavaDoc

```
mvn javadoc:javadoc
```
