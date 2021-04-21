package test;

import general.Luogo;
import general.Mappa;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LuogoTest
{

    @Test
    public void testAdiacente()
    {

        Mappa mappa = new Mappa();
        Luogo esternoCasa = new Luogo();

        esternoCasa.setNomeLuogo("esterno casa");

        assertTrue(mappa.getSeminterratoCasa().adiacenteA(esternoCasa.getNomeLuogo()).getNomeLuogo().equals(esternoCasa.getNomeLuogo()));
    }

}
