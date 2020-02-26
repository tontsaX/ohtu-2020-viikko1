package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastoMiinusTilavuus() {
    	varasto = new Varasto(-1);
    	assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    
    @Test
    public void konstruktoriLuoTäydenVarastonYlimaaraHukkaan() {
    	varasto = new Varasto(1, 15); // tilavuus, alkuSaldo
    	assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldoPienempiKuinTilavuus() {
    	varasto = new Varasto(10, 5); // tilavuus, alkuSaldo
    	assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoNollatunVaraston() {
    	varasto = new Varasto(-1, -1); // tilavuus, alkuSaldo
    	assertEquals(0, varasto.getTilavuus(), vertailuTarkkuus);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonOikeaSaldoYlimaaraPois() {
    	varasto.lisaaVarastoon(15);
    	assertEquals(varasto.getTilavuus(), varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonNegatiivinenSaldo() {
    	double orgSaldo = varasto.getSaldo();
    	varasto.lisaaVarastoon(-5);
    	assertEquals(orgSaldo, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaKaikkiMitaVoidaan() {
    	varasto.lisaaVarastoon(10);
    	
    	assertEquals(10, varasto.otaVarastosta(15), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenVarastostaOtto() {
    	assertEquals(0, varasto.otaVarastosta(-5), vertailuTarkkuus);
    }
    
    @Test
    public void toStringPalauttaaOikeanJonon() {
    	assertThat(varasto.toString(), is("saldo = 0.0, vielä tilaa 10.0"));
    }

}