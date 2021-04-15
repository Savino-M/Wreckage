package test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import general.Giocatore;
import general.Mappa;
import general.Salvataggio;

public class SalvataggioTest {

    @Test
    public void testSalvataggio() {

	Mappa mappa = new Mappa();
	Giocatore giocatore = new Giocatore();
	mappa.getKm167().setNomeLuogo("aa");
	giocatore.setPuntiVita(50);

	Mappa mappa1;
	Giocatore giocatore1;

	Salvataggio.salva(mappa, giocatore);

	mappa1 = Salvataggio.caricaMappa();
	giocatore1 = Salvataggio.caricaGiocatore();
	assertTrue(mappa.getKm167().getNomeLuogo().equals(mappa1.getKm167().getNomeLuogo()));
	assertTrue(giocatore.getPuntiVita() == giocatore1.getPuntiVita());
    }

    @Test
    public void testEsistePartita() {

	Mappa mappa = new Mappa();
	Giocatore giocatore = new Giocatore();
	
	giocatore.setPuntiVita(50);
	
	Salvataggio.salva(mappa, giocatore);
	
	assertTrue(Salvataggio.esistePartita());
    }

}
