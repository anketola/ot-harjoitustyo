package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    
    @Test
    public void luodunKassapaatteenRahatOikein () {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void luodunKassapaatteenEdullisetMyydytOikein () {
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void luodunKassapaatteenMaukkaatMyydytOikein () {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdullisestiMaksuRiittavaRahamaaraKasvaa () {
        kassapaate.syoEdullisesti(240);
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEdullisestiMaksuRiittavaVaihtorahaOikein () {
        assertEquals(20, kassapaate.syoEdullisesti(260));
    }
    
    @Test
    public void kateisostoEdullisestiMaksuRiittavaMyydytKasvaa () {
        kassapaate.syoEdullisesti(240);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukkaastiMaksuRiittavaRahamaaraKasvaa () {
        kassapaate.syoMaukkaasti(400);
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoMaukkaastiMaksuRiittavaVaihtorahaOikein () {
        assertEquals(20, kassapaate.syoMaukkaasti(420));
    }
    
    @Test
    public void kateisostoMaukkaastiMaksuRiittavaMyydytKasvaa () {
        kassapaate.syoMaukkaasti(400);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdullisestiMaksuEiRiittavaRahamaaraMuuttumaton () {
        kassapaate.syoEdullisesti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoEdullisestiMaksuEiRiittavaVaihtorahaOikein () {
        assertEquals(150, kassapaate.syoEdullisesti(150));
    }
    
    @Test
    public void kateisostoEdullisestiMaksuEiRiittavaMyydytMuuttumaton () {
        kassapaate.syoEdullisesti(150);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukkaastiMaksuEiRiittavaRahamaaraMuuttumaton () {
        kassapaate.syoMaukkaasti(200);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kateisostoMaukkaastiMaksuEiRiittavaVaihtorahaOikein () {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
    }
    
    @Test
    public void kateisostoMaukkaastiMaksuEiRiittavaMyydytMuuttumaton () {
        kassapaate.syoMaukkaasti(100);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksukorttiEdullisestiRiittavastiRahaaPalauttaaTrue () {
        Maksukortti maksukortti = new Maksukortti(240);
        assertTrue(kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void maksukorttiEdullisestiRiittavastiRahaaTehdaanVeloitus () {
        Maksukortti maksukortti = new Maksukortti(240);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(0, maksukortti.saldo());
    }
    
    @Test
    public void maksukorttiEdullisestiRiittavastiRahaaMyydytKasvaa () {
        Maksukortti maksukortti = new Maksukortti(240);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksukorttiEdullisestiEiRiittavastiRahaaSaldoMuuttumaton () {
        Maksukortti maksukortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(200, maksukortti.saldo());
    }
    
    @Test
    public void maksukorttiEdullisestiEiRiittavastiRahaaMyydytMuuttumaton () {
        Maksukortti maksukortti = new Maksukortti(200);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maksukorttiEdullisestiEiRiittavastiRahaaPalauttaaFalse () {
        Maksukortti maksukortti = new Maksukortti(200);
        assertTrue(!kassapaate.syoEdullisesti(maksukortti));
    }
    
    @Test
    public void maksukorttiMaukkaastiRiittavastiRahaaPalauttaaTrue () {
        Maksukortti maksukortti = new Maksukortti(450);
        assertTrue(kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void maksukorttiMaukkaastiRiittavastiRahaaTehdaanVeloitus () {
        Maksukortti maksukortti = new Maksukortti(400);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(0, maksukortti.saldo());
    }
    
    @Test
    public void maksukorttiMaukkaastiRiittavastiRahaaMyydytKasvaa () {
        Maksukortti maksukortti = new Maksukortti(450);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksukorttiMaukkaastiEiRiittavastiRahaaSaldoMuuttumaton () {
        Maksukortti maksukortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(200, maksukortti.saldo());
    }
    
    @Test
    public void maksukorttiMaukkaastiEiRiittavastiRahaaMyydytMuuttumaton () {
        Maksukortti maksukortti = new Maksukortti(200);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maksukorttiMaukkaastiEiRiittavastiRahaaPalauttaaFalse () {
        Maksukortti maksukortti = new Maksukortti(200);
        assertTrue(!kassapaate.syoMaukkaasti(maksukortti));
    }
    
    @Test
    public void kassanRahamaaraMuuttumatonKorttiostoksessaEdullisesti () {
        Maksukortti maksukortti = new Maksukortti(500);
        kassapaate.syoEdullisesti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kassanRahamaaraMuuttumatonKorttiostoksessaMaukkaasti () {
        Maksukortti maksukortti = new Maksukortti(500);
        kassapaate.syoMaukkaasti(maksukortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttilatausKasvattaaKortinSaldoa () {
        Maksukortti maksukortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(maksukortti, 50);
        assertEquals(550, maksukortti.saldo());
    }
    
    @Test
    public void korttilatausKasvattaaKassanRahamaaraa () {
        Maksukortti maksukortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(maksukortti, 50);
        assertEquals(100050, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void korttilatausNegatiivisellaEiMuutaKortinSaldoa () {
        Maksukortti maksukortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(maksukortti, -10);
        assertEquals(500, maksukortti.saldo());
    }
    
    @Test
    public void korttilatausNegatiivisellaEiMuutaKassanRahamaaraa () {
        Maksukortti maksukortti = new Maksukortti(500);
        kassapaate.lataaRahaaKortille(maksukortti, -10);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
}
