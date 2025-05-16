import general.Giocatore;
import general.Mappa;
import general.Salvataggio;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SalvataggioTest {

    @Test
    public void testSalvataggio() {

        Mappa mappa = new Mappa();
        Giocatore giocatore = new Giocatore();
        mappa.getKm167().setNomeLuogo("aa");
        giocatore.setPuntiVita(50);

        Salvataggio.salva(mappa, giocatore);

    }

    @Test
    public void testEsistePartita() {

        Mappa mappa = new Mappa();
        Giocatore giocatore = new Giocatore();

        giocatore.setPuntiVita(50);

        Salvataggio.salva(mappa, giocatore);

    }

}
