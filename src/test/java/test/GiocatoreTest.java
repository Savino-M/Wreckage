package test;

import general.Giocatore;
import general.Mappa;
import items.Arma;
import items.Oggetto;
import items.Utilita;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GiocatoreTest
{

    @Test
    public void testEsamina()
    {

        String oggetto = "luogo";

        Giocatore giocatore = new Giocatore();
        Mappa mappa = new Mappa();

        giocatore.setLuogoAttuale(mappa.getSeminterratoCasa());

        giocatore.esamina(oggetto);

        assertTrue(giocatore.getLuogoAttuale().getTestoEsamina().equals(mappa.getSeminterratoCasa().getTestoEsamina()));
    }

    @Test
    public void testRaccogli()
    {

        Arma coltello = new Arma("coltello", "\n>Ideale per fare a fettine i nemici.", true, 50);

        Giocatore giocatore = new Giocatore();
        Mappa mappa = new Mappa();
        giocatore.setLuogoAttuale(mappa.getSeminterratoCasa());
        giocatore.raccogli(coltello.getNome());

        assertTrue(giocatore.getInventario().get(0).getNome().equals(coltello.getNome()));
    }

    @Test
    public void testLascia()
    {

        List<Oggetto> inventario = new ArrayList<Oggetto>();
        Giocatore giocatore = new Giocatore();
        Mappa mappa = new Mappa();
        Arma coltello = new Arma("coltello", "\n>Ideale per fare a fettine i nemici.", true, 50);

        giocatore.setLuogoAttuale(mappa.getSeminterratoCasa());
        giocatore.getInventario().add(coltello);
        giocatore.lascia(coltello.getNome());

        assertArrayEquals(inventario.toArray(), giocatore.getInventario().toArray());
    }

    @Test
    public void testMove()
    {

        Giocatore giocatore = new Giocatore();
        Mappa mappa = new Mappa();
        Utilita chiavi = new Utilita("chiavi", "\n>Aprono le porte per uscire di casa.", false);

        giocatore.setLuogoAttuale(mappa.getSeminterratoCasa());
        giocatore.getInventario().add(chiavi);
        giocatore.move("esterno casa");

        assertTrue(giocatore.getLuogoAttuale().getNomeLuogo().equals("esterno casa"));
    }

    @Test
    public void testUsa()
    {

        Giocatore giocatore = new Giocatore();
        Mappa mappa = new Mappa();
        Arma coltello = new Arma("coltello", "\n>Ideale per fare a fettine i nemici.", true, 50);

        giocatore.setLuogoAttuale(mappa.getSeminterratoCasa());
        giocatore.getInventario().add(coltello);
        giocatore.usa(coltello.getNome());

        assertTrue(giocatore.getArmaInUso().getNome().equals(coltello.getNome()));
    }

    @Test
    public void testAttacca()
    {

        Giocatore giocatore = new Giocatore();
        Mappa mappa = new Mappa();

        Arma coltello = new Arma("coltello", "\n>Ideale per fare a fettine i nemici.", true, 50);

        giocatore.setLuogoAttuale(mappa.getKm167());
        giocatore.equipaggiaArmaInUso(coltello);

        while (giocatore.getLuogoAttuale().getNemico() != null && giocatore.getLuogoAttuale().getNemico().getVita() > 0)
        {
            giocatore.attacca(giocatore.getLuogoAttuale().getNemico());
        }

        assertNull(giocatore.getLuogoAttuale().getNemico());
    }

    @Test
    public void testHaOggetto()
    {

        Giocatore giocatore = new Giocatore();
        Arma coltello = new Arma("coltello", "\n>Ideale per fare a fettine i nemici.", true, 50);

        giocatore.getInventario().add(coltello);

        assertTrue(giocatore.haOggetto(coltello.getNome()));
    }
}
