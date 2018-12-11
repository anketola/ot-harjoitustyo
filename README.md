# Columns-peli

Tämä sovellus on reaaliaikainen Columns-peli. Sovellus on Ohelmistotekniikan menetelmät -kurssin harjoitustyö. Kyseessä on variaatio Tetriksestä, jossa palikoiden pyörittämisen ja pelilaudan täyttämisen sijaan huomio on värien yhdistämisessä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä. Yhdistäminen voi tapahtua horisontaalisesti, viistosti tai pystysuoraan. 

*Sovelluksen tämänhetkisistä toiminnoista ja lyhyt ohje:*

Ohje: Palikat tippuvat pelialueella. Pelaaja voi käyttää nuolinäppäimiä palikoiden ohjaamiseen. Neliöiden järjestystä vaihdetaan painamalla ylös-nappia. Putoamista voi nopeuttaa painamalla alas-nuolta. 

**Päivitysinfoa** Sovelluksen näpyttäjä on ollut sairaana, mutta päätti ottaa lähes eeppisen loppuspurtin deadlinen vuoksi. Ohjelmaan on nyt lisätty pisteiden näyttöä ja tallentamista varten oleva DAO-pakkaus sisälöineen. Tämä toi mukanaan jotain uusia luokkia tätä tukemaan. Uuden piste-ennätysen.. vaikka se on tunnistettavissa.. ei ole juuri nyt vielä lisättävissä. Katson mitä parametrejä laitan siihen - pitäisikö pisteitä tarkastella vain tietyn nopeuden sisällä. Värien määrään tulee vielä se säätö jne, joten kaikki on siis tietokannassa. Pidin sitä kätevämpänä vaihtoehtona. Hakee sieltä tietokannasta samoilla spekseillä pelattuja pelejä jne. Nyt on edessä se, mihin en sairaana pystynyt - eli refaktoroida koko homma. Eriyittää UI moneksi luokakksi, ja hajoittaa palasiksi GameArea, etenkin siellä oleva logiikka.

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Käyttöohje](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Arkkitehtuurikuvaus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

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
