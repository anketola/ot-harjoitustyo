# Columns-peli

Tämä sovellus on reaaliaikainen Columns-peli. Sovellus on Ohelmistotekniikan menetelmät -kurssin harjoitustyö. Kyseessä on variaatio Tetriksestä, jossa palikoiden pyörittämisen ja pelilaudan täyttämisen sijaan huomio on värien yhdistämisessä. Tavoitteena on yhdistää vähintää kolme samanväristä neliötä. Yhdistäminen voi tapahtua horisontaalisesti, viistosti tai pystysuoraan. 

*Sovelluksen tämänhetkisistä toiminnoista ja lyhyt ohje:*

Ohje: Palikat tippuvat pelialueella. Pelaaja voi käyttää nuolinäppäimiä palikoiden ohjaamiseen. Neliöiden järjestystä vaihdetaan painamalla ylös-nappia. Putoamista voi nopeuttaa painamalla alas-nuolta. 

**Päivitysinfoa 4.12:** Sovellukseen on nyt tehty uusi algoritmi ja vanha on korvattu sillä. Sillä ei ole nyt pituusrajoituksia (eli voi olla esim. 5 pitkä värisuora). Samalla voi olla samanaikaisesti esim. pystysuorassa ja vinottain suora ja tämä tunnistuu oikein. Poistettavat palikat menevät tavallaan "koriin", ja ne deletoidaan. Vaikeustason voi nyt säätää alkuruudusta - eli pelin nopeutta voi säätää. Peliin on lisätty 2 uutta väriä (nyt 5 yhteensä). Lisätty ajastin. Toisteista koodia, käyttämättömiä metodeita ja muuttujia poistettu. Värit toimivat nyt järkevämmin. Korjattavaa yms: Algoritmin jäljiltä jäi pieni bugi (huomaa jos yrittää hävitä peliä, en ehtinyt korjata) ja katvealue. GameArea-luokka on turhan paisunut. DAO pakkaus tulossa vielä, sitä ei ehdity vielä koodata (piste-ennätyksiä varten). Korjailu ja lisäily jatkuu kohta..

### Dokumentaatio

[Vaatimusmäärittely](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Arkkitehtuurikuvaus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Työaikakirjanpito](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

### Releaset

[Viikko 5](https://github.com/anketola/ot-harjoitustyo/releases/tag/viikko5)

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
