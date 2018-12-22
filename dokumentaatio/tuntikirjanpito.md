# Työaikakirjanpito

| päivä | aika | mitä tein   |
| :----:|:-----| :-----|
|13.11.|2h | Sovelluksen vaatimusmäärittelyt |
|19.11.|3h | Alustavaa rakentelua, JavaFX:n testaamista |
|20.11.|2h | Lisätty muutama testi, tehty sovellukseen pieniä korjauksia | 
|26.11.|6h | Lisätty kollisionhallinta, palikat jäävät alueelle ja pelaaja saa uuden palikan, väliaikainen copy-paste -algoritmi rivien alkeelliselle tunnistamiselle, peli nyt alkeellisesti pelattavissa | 
|27.11.|5h | Lisätty pisteenlaskenta, laajennettu testejä, alustava menu lisätty, lisätty alustava pelin pausetus |
|28.11.|1h | Respawnia muutettu, nyt etsii avoimet paikat ja uusi pelaajapalikka syntyy sinne |
|30.11.|9h | Kirjoitettu algoritmi väliaikaisen sijalle, etsii nyt systemaattisesti pelialuetta läpi, hyväksyy yli 3-pitkät jonot, hyväksyy usean samanaikaisen jonon (esim pystyyn ja vinoon samanaikaisesti yhdellä palikan tiputuksella), palikat kerätään "koriin", jonka palikat deletoidaan kerralla. Pari puutetta (mm. bugi joka estää pelin loppumisen kunnolla ja katvealue algortimissa yläreunassa - korjataan kaikki myöhemmin), mutta kivempi ja perustoiminnallisuudelta parempi nyt. Luotu "Game over -scene", DAO high scorelle yms. luodaan siihenkohta (manittu bugi estää hetken tähän pääsyn). |
|4.12.|7h | Lisätty vaikeustasosäätö alkuvalikkoon (voi vaihtaa nopeuden). Lisätty värien määrä viiteen (näitten määrälle myöhemmin myös säätönnappi). Lisätty kello. Poistettu käyttämättömiä metodeita ja muuttujia, vähennetty koodin toistoa. Laajennettu testejä.
|7.12.|1h | Etsitty, löydetty ja korjattu ikävä bugi pudotusmetodissa |
|8.12.|1h | Sovellukseen lisätty pelialueen kutistuminen - peli oli liian helppo - nyt pääsee paremmin ennätyspisteitten implementointiin|
|11.12|9h | Suurin projekti - tietokannat otetiin tuskailun jälkeen käyttöön SQLitellä. Pistenäytöt ovat nyt myös käytössä, mutta kaikki toiminnallisuudet eivät ehdi tuohon vartin määräaikaan. Uutta high-scorea ei saa vielä tietokantaan, vaikka se tunnistuu, lisätään se. Kaikki pääominaisuudet ovat valmiit käsittääkseni? Nyt projekti pitäisi vain rankasti refaktoroida ja hioa. Tietokanta on mukana projektissa ja sen schemaa voi halutessaan käydä katsomassa. Ideana olisi, että siihen voi laittaa tietoa suorituksen vaikeustasoista ja filtteröidä sitten sen perusteella pistenäytössä. Muuten eivät oikein vertailukelpoisia. JavaDoc otettu käyttöön 5-6 luokalle. Testejä muutama lisää, aiemmat ovat monet realistisia, lisätään nämä vielä paremmat refaktoroinnin jälkeen. Projekti siis jatkuu...
|19.12| 9h | Yritetty löytää ratkaisua tietokantaa koskeville ongelmille. Purettu ratkaisut lisää ongelmia aiheuttavina, mutta pääetty jättää tietokantarakenne silti käyttöön.|
|21.12| 7h | Refaktoroitu GameArea käytännössä pois, paljon uusia luokkia, joitain uusia metodeita, säätämistä yms. |
|22.12| 12h | Refaktoroitu lisää, kirjoitettu JavaDoc uusiksi, uusia testejä, dokumentaation päivitykset, yritetty vielä ratkoa tietokantahärdelliä, pieniä säätöjä |
|yht | 74h | |
