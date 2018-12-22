# Testausdokumentti

## Yleistä testauksesta

Sovelluksen testit tehtiin sekä JUnitin testein että manuaalisesti. JUnitin testien lisäksi sovellusta joutui testaamaan huomattavasti manuaalisesti, jotta sen käytettävyys pysyisi riittävällä tasolla. Värien / nopeuden / alueen koon / yms. tultiin lopulta manuaalisen testauksen jälkeen sovelluksessa oleviin hienosäätöihin. Sovellus oli reaaliaikaisena suhteellisen "herkkä" muutoksille. Tehtyjen säätöjen perusteella sovellusta saisi nyt suhteellisen helposti laajennettua esim. antamalla mahdollisuuksia säätää pelialueen kokoa, värien määrää ja erikoispalikoita.

Testaukseen vaikutti merkittävästi tietokannan käyttöön liittyvät ongelmat. Ongelma oli alunperin vain toimivan jar:in generoinnissa, mutta levisi sen jälkeen myös testauksen puolelle. Lopputuloksena tietokantaa koskevat osuudet testattiin toiminnallisuudelta manuaalisesti. Sekä luku että tallennus toimii käsittääkseni ongelmitta, kun scores.db -tietokanta Scores-taululla on vain olemassa. Kyseessä oli lähinnä osaamattomuus javan ja tietokantojen kanssa, joka täytyy korjata myöhemmin.

## Yksikkö- ja integraatiotestaus

### Testikattavuus

Testauskattavuuteen vaikutti merkittävästi ongelmat SQL-tietokannan kanssa, joka tiputti kattavuutta. Lopputuloksena oli kuitenkin 91 % rivikattavuus. Haaraumakattavuus oli puolestaan 88 %. Kattavuutta laski melkoisesti domain pakkauksen ScoreBoardHandler (joka olisi tarvinnut siis testitietokannan jne.). Lisäksi kattavuutta laski tietokantantaongelmiin liittyen sekä dao-pakkauksen sisältämät sekä ScoreEntryDao että Database. 

Testeissä on yritetty kiinnittää huomiota siihen, että nissä muodostetaan mahdollisimman monipuolisesti yhteyksiä eri luokkien välillä. 

Ainoastaan käyttöliittymän luovat osat on suljettu testauksen ulkopuolelle.

![Testikattavuus](https://github.com/anketola/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/testauskattavuus.png)

## Muu testaus

Sovelluksen käyttöliittymää on testattu manuaalisesti.

Sovelluksessa on käyty checklistinä kaikki määrittelydokumentin toiminnallisuudet läpi. Siinä määrin kuin sovellus oli kasvanut tai dokumentaatio tuolta osin vaati täsmennystä, dokumentti on päivitetty. Uusien toiminnallisuuksien syntyyn vaikutti paljolti manuaalinen testaus käyttökokemuksesta.

# Kehityskohteita

Sovellukseen jää nyt dao-pakkauksen ja tietokannan kattava aukko JUnitin osalta. Osaamisen ulkopuolella oli toistaiseksi testitietokantojen käyttäminen. 

